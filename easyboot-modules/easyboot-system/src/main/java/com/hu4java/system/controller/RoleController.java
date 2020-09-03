package com.hu4java.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.request.RemoveRequest;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.common.result.Result;
import com.hu4java.system.entity.Role;
import com.hu4java.system.entity.RoleMenu;
import com.hu4java.system.request.RoleListRequest;
import com.hu4java.system.request.RoleRequest;
import com.hu4java.system.request.RoleUpdateRequest;
import com.hu4java.system.response.RoleUpdateResponse;
import com.hu4java.system.service.RoleMenuService;
import com.hu4java.system.service.RoleService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MapperFacade mapperFacade;

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

    @GetMapping("/detail")
    public Result<RoleUpdateResponse> detail(@Validated ViewRequest request) {
        Role role = roleService.getById(request.getId());
        List<RoleMenu> roleMenuList = roleMenuService.listByRoleId(role.getId());
        List<Long> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        RoleUpdateResponse response = mapperFacade.map(role, RoleUpdateResponse.class);
        response.setMenuIds(menuIds);
        return Result.success(response);
    }

    /**
     * 保存角色
     * @param request   角色数据
     * @return
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody @Validated RoleRequest request) {
        Role exist = roleService.getByCode(request.getCode());
        if (null != exist) {
            return Result.error("角色代码已存在");
        }
        Role role = mapperFacade.map(request, Role.class);
        roleService.save(role, request.getMenuIds());
        return Result.success();
    }

    /**
     * 更新角色
     * @param request   角色数据
     * @return
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Validated RoleUpdateRequest request) {
        Role exist = roleService.getById(request.getId());
        if (null == exist) {
            return Result.error("角色数据不存在");
        }
        exist = roleService.getByCode(request.getCode());
        if (null != exist && !Objects.equals(exist.getId(), request.getId())) {
            return Result.error("角色代码已存在");
        }
        Role role = mapperFacade.map(request, Role.class);
        roleService.update(role, request.getMenuIds());
        return Result.success();
    }

    /**
     * 删除角色
     * @param request   角色ID
     * @return
     */
    @PostMapping("/remove")
    public Result<Void> remove(@RequestBody @Validated RemoveRequest request) {
        roleService.removeById(request.getId());
        return Result.success();
    }
}
