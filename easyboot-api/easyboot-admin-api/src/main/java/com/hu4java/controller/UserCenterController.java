package com.hu4java.controller;

import com.hu4java.base.enums.Status;
import com.hu4java.common.constant.Constants;
import com.hu4java.common.result.Result;
import com.hu4java.common.result.ResultCode;
import com.hu4java.request.UpdatePasswordRequest;
import com.hu4java.request.UpdateUserInfoRequest;
import com.hu4java.response.AntRouteResponse;
import com.hu4java.response.EleRouteResponse;
import com.hu4java.response.UserInfoResponse;
import com.hu4java.system.entity.Role;
import com.hu4java.system.entity.User;
import com.hu4java.system.service.UserService;
import com.hu4java.util.RandomUtils;
import com.hu4java.util.ShiroUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * Antdv 路由数据
     * @return
     */
    @GetMapping("/antRoute")
    public Result<AntRouteResponse> antRoute() {
        return Result.success();
    }

    /**
     * element ui 路由
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
    @PostMapping("/updateInfo")
    public Result<Void> updateInfo(@RequestBody @Validated UpdateUserInfoRequest request) {
        User currentUser = ShiroUtils.currentLogin();
        User user = mapperFacade.map(request, User.class);
        user.setId(currentUser.getId());
        userService.update(user);
        return Result.success();
    }
}
