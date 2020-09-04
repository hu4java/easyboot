package com.hu4java.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.base.response.PageResponse;
import com.hu4java.common.result.Result;
import com.hu4java.system.entity.User;
import com.hu4java.system.request.UserListRequest;
import com.hu4java.system.response.UserListResponse;
import com.hu4java.system.service.UserService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzhenhu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MapperFacade mapperFacade;

    /**
     * 用户列表
     * @param request   查询参数
     * @return
     */
    @GetMapping("/list")
    public Result<PageResponse<UserListResponse>> list(UserListRequest request) {

        Page<User> page = request.toPage();
        page = userService.listByPage(page, request.queryWrapper());

        Type<Page<User>> fromType = new TypeBuilder<Page<User>>(){}.build();
        Type<PageResponse<UserListResponse>> toType = new TypeBuilder<PageResponse<UserListResponse>>(){}.build();
        PageResponse<UserListResponse> response = mapperFacade.map(page, fromType, toType);
        return Result.success(response);
    }

    @GetMapping("/detail")
    public Result detail(@Validated ViewRequest request) {
        User user = userService.getById(request.getId());

        return Result.success();
    }
}
