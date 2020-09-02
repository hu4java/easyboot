package com.hu4java.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.common.result.Result;
import com.hu4java.system.entity.Role;
import com.hu4java.system.request.RoleListRequest;
import com.hu4java.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
