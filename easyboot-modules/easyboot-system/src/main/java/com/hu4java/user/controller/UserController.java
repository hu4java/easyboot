package com.hu4java.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.enums.Gender;
import com.hu4java.base.request.PageRequest;
import com.hu4java.base.response.PageResponse;
import com.hu4java.common.result.Result;
import com.hu4java.user.condition.UserCondition;
import com.hu4java.user.entity.User;
import com.hu4java.user.service.UserService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public Result<PageResponse<User>> list(PageRequest pageRequest) {
        Page<User> page = mapperFacade.map(pageRequest, Page.class);
        UserCondition condition = new UserCondition();
        condition.setGender(Gender.UNKNOWN.getGender());
        page = userService.listByPage(page, condition.queryWrapper());
        Type<Page<User>> fromType = new TypeBuilder<Page<User>>(){}.build();
        Type<PageResponse<User>> toType = new TypeBuilder<PageResponse<User>>(){}.build();
        PageResponse<User> response = mapperFacade.map(page, fromType, toType);
        return Result.success(response);
    }
}
