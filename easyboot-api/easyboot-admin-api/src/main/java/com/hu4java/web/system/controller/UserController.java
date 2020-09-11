package com.hu4java.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.request.RemoveRequest;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.base.response.PageResponse;
import com.hu4java.common.constant.Constants;
import com.hu4java.common.result.Result;
import com.hu4java.system.condition.UserCondition;
import com.hu4java.system.entity.User;
import com.hu4java.system.entity.UserDept;
import com.hu4java.system.entity.UserRole;
import com.hu4java.system.service.UserDeptService;
import com.hu4java.system.service.UserRoleService;
import com.hu4java.system.service.UserService;
import com.hu4java.util.RandomUtils;
import com.hu4java.util.ShiroUtils;
import com.hu4java.web.system.request.UserListRequest;
import com.hu4java.web.system.request.UserResetPasswordRequest;
import com.hu4java.web.system.request.UserSaveRequest;
import com.hu4java.web.system.request.UserUpdateRequest;
import com.hu4java.web.system.response.UserListResponse;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统管理-用户
 * 用户管理
 * @author chenzhenhu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserDeptService userDeptService;

    /**
     * 用户列表
     * @param request   查询参数
     * @return          用户分页列表
     */
    @GetMapping("/list")
    public Result<PageResponse<UserListResponse>> list(UserListRequest request) {

        Page<User> page = request.toPage();
        UserCondition condition = mapperFacade.map(request, UserCondition.class);
        page = userService.listByPage(page, condition);

        Type<Page<User>> fromType = new TypeBuilder<Page<User>>(){}.build();
        Type<PageResponse<UserListResponse>> toType = new TypeBuilder<PageResponse<UserListResponse>>(){}.build();
        PageResponse<UserListResponse> response = mapperFacade.map(page, fromType, toType);
        return Result.success(response);
    }

    /**
     * 用户详细
     * @param request   参数
     * @return          表单数据
     */
    @GetMapping("/detail")
    public Result<UserUpdateRequest> detail(@Validated ViewRequest request) {
        User user = userService.getById(request.getId());
        if (null == user) {
            return Result.error("用户数据不存在");
        }
        List<UserRole> userRoleList = userRoleService.listByUserId(user.getId());
        List<UserDept> userDeptList = userDeptService.listByUserId(user.getId());
        UserUpdateRequest response = mapperFacade.map(user, UserUpdateRequest.class);

        List<Long> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Long> deptIds = userDeptList.stream().map(UserDept::getDeptId).collect(Collectors.toList());
        response.setRoleIds(roleIds);
        response.setDeptIds(deptIds);
        return Result.success(response);
    }

    /**
     * 保存用户
     * @param request   参数
     * @return
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody @Validated UserSaveRequest request) {
        User exist = userService.getByMobile(request.getMobile());
        if (null != exist) {
            return Result.error("手机号已存在");
        }
        exist = userService.getByEmail(request.getEmail());
        if (null != exist) {
            return Result.error("邮箱已存在");
        }
        User user = mapperFacade.map(request, User.class);
        userService.save(user, request.getRoleIds(), request.getDeptIds());
        return Result.success();
    }

    /**
     * 更新用户
     * @param request   参数
     * @return
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Validated UserUpdateRequest request) {
        User exist = userService.getByMobile(request.getMobile());
        if (null != exist && !Objects.equals(exist.getId(), request.getId())) {
            return Result.error("手机号已存在");
        }
        exist = userService.getByEmail(request.getEmail());
        if (null != exist && !Objects.equals(exist.getId(), request.getId())) {
            return Result.error("邮箱已存在");
        }
        User user = mapperFacade.map(request, User.class);
        userService.update(user, request.getRoleIds(), request.getDeptIds());
        return Result.success();
    }

    /**
     * 重置密码
     * @param request   参数
     * @return
     */
    @PostMapping("/resetPassword")
    public Result<Void> resetPassword(@RequestBody @Validated UserResetPasswordRequest request) {
        User exist = userService.getById(request.getId());
        if (null == exist) {
            return Result.error("用户数据不存在");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return Result.error("两次密码输入不一致");
        }

        String salt = RandomUtils.randomNumber(Constants.SALT_LENGTH);
        String password = ShiroUtils.encode(request.getPassword(), salt);
        User user = new User();
        user.setId(request.getId());
        user.setSalt(salt);
        user.setPassword(password);
        userService.update(user);
        return Result.success();
    }

    /**
     * 删除用户
     * @param request 参数
     * @return
     */
    @PostMapping("/remove")
    public Result<Void> remove(@RequestBody @Validated RemoveRequest request) {
        userService.removeById(request.getId());
        return Result.success();
    }
}
