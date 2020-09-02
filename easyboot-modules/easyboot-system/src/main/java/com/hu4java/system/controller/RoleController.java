package com.hu4java.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.common.result.Result;
import com.hu4java.system.entity.Role;
import com.hu4java.system.request.RoleListRequest;
import com.hu4java.system.request.RoleRequest;
import com.hu4java.system.request.RoleUpdateRequest;
import com.hu4java.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统管理-角色
 * 角色管理
 * @author hu4java
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     * @param request   分页请求
     * @return
     */
    @GetMapping("/list")
    public Result<Page<Role>> list(RoleListRequest request) {
        Page<Role> page = roleService.listByPage(request.toPage(), request.queryWrapper());
        return Result.success(page);
    }

    @PostMapping("/save")
    public Result<Void> save(@RequestBody @Validated RoleRequest request) {
        return Result.success();
    }

    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Validated RoleUpdateRequest request) {
        return Result.success();
    }
}
