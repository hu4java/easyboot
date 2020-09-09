package com.hu4java.web.common.controller;

import com.hu4java.base.enums.Status;
import com.hu4java.common.constant.Constants;
import com.hu4java.common.result.Result;
import com.hu4java.common.result.ResultCode;
import com.hu4java.system.entity.Menu;
import com.hu4java.system.entity.Role;
import com.hu4java.system.entity.User;
import com.hu4java.system.enums.MenuType;
import com.hu4java.system.service.MenuService;
import com.hu4java.system.service.UserService;
import com.hu4java.util.RandomUtils;
import com.hu4java.util.ShiroUtils;
import com.hu4java.web.common.request.UpdatePasswordRequest;
import com.hu4java.web.common.request.UpdateUserInfoRequest;
import com.hu4java.web.common.response.AntRouteResponse;
import com.hu4java.web.common.response.EleRouteResponse;
import com.hu4java.web.common.response.UserInfoResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 个人中心
 * @author hu4java
 */
@RestController
@RequestMapping("/user/center")
public class UserCenterController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MapperFacade mapperFacade;

    /**
     * 用户信息
     * @return
     */
    @GetMapping("/info")
    public Result<UserInfoResponse> info() {
        User current = ShiroUtils.currentLogin();
        UserInfoResponse response = mapperFacade.map(current, UserInfoResponse.class);
        List<String> roleList = current.getRoleList().stream().filter(role -> role.getStatus().equals(Status.ENABLE.getStatus()))
                .map(Role::getCode).collect(Collectors.toList());
        response.setRoles(roleList);
        return Result.success(response);
    }

    /**
     * AntdV 路由数据
     * @return
     */
    @GetMapping("/antRoute")
    public Result<List<AntRouteResponse>> antRoute() {
        User currentUser = ShiroUtils.currentLogin();
        List<Menu> menuList = menuService.listByUserId(currentUser.getId());
        List<Menu> treeList = getMenuChildren(Constants.TOP_PID, menuList);
        List<AntRouteResponse> list = mapperFacade.mapAsList(treeList, AntRouteResponse.class);
        return Result.success(list);
    }

    /**
     * Element UI 路由
     * @return
     */
    @GetMapping("/eleRoute")
    public Result<EleRouteResponse> eleRoute() {
        return Result.success();
    }

    /**
     * 修改密码
     * @param request   请求数据
     * @return
     */
    @PostMapping("/updatePassword")
    public Result<Void> updatePassword(@RequestBody @Validated UpdatePasswordRequest request) {
        User currentUser = ShiroUtils.currentLogin();
        if (!ShiroUtils.match(request.getOldPassword(), currentUser.getPassword(), currentUser.getSalt())) {
            return Result.error(ResultCode.OLD_PASSWORD_ERROR);
        }

        if (!Objects.equals(request.getNewPassword(), request.getConfirmPassword())) {
            return Result.error(ResultCode.THE_TWO_PASSWORD_INPUTS_ARE_INCONSISTENT);
        }

        User updateUser = new User();
        updateUser.setId(currentUser.getId());
        updateUser.setSalt(RandomUtils.randomString(Constants.SALT_LENGTH));
        String password = ShiroUtils.encode(request.getNewPassword(), updateUser.getSalt());
        updateUser.setPassword(password);
        userService.update(updateUser);
        ShiroUtils.logout();
        return Result.success();
    }

    /**
     * 更新用户信息
     * @param request   用户数据
     * @return
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Validated UpdateUserInfoRequest request) {
        User currentUser = ShiroUtils.currentLogin();
        User user = mapperFacade.map(request, User.class);
        user.setId(currentUser.getId());
        userService.update(user);
        return Result.success();
    }


    private List<Menu> getMenuChildren(Long pid, List<Menu> menuList) {
        List<Menu> list = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getType().equals(MenuType.BUTTON.getType())
                    || menu.getStatus().equals(Status.DISABLE.getStatus())) {
                continue;
            }
            if (menu.getPid().equals(pid)) {
                List<Menu> children = getMenuChildren(menu.getId(), menuList);
                // 目录下无节点不添加
                if (menu.getType().equals(MenuType.CATALOG.getType()) && children.size() == 0) {
                    continue;
                }
                menu.setChildren(children);
                list.add(menu);
            }
        }
        return list;
    }
}
