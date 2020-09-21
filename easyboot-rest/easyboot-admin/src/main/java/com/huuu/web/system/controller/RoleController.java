package com.huuu.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.base.annotation.Log;
import com.huuu.base.enums.LogType;
import com.huuu.base.enums.Status;
import com.huuu.base.request.RemoveRequest;
import com.huuu.base.request.ViewRequest;
import com.huuu.base.result.Result;
import com.huuu.system.entity.Role;
import com.huuu.system.entity.RoleMenu;
import com.huuu.system.service.RoleMenuService;
import com.huuu.system.service.RoleService;
import com.huuu.web.system.request.RoleFormRequest;
import com.huuu.web.system.request.RoleListRequest;
import com.huuu.web.system.request.RoleUpdateRequest;
import com.huuu.web.system.response.RoleUpdateResponse;
import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统管理-角色
 * 角色管理
 * @author huuu
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
    @RequiresPermissions("sys:role:view")
    public Result<Page<Role>> list(RoleListRequest request) {
        Page<Role> page = roleService.listByPage(request.toPage(), request.queryWrapper());
        return Result.success(page);
    }

    /**
     * 角色详细
     * @param request   参数
     * @return
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:role:view")
    public Result<RoleUpdateResponse> detail(@Validated ViewRequest request) {
        Role role = roleService.getById(request.getId());
        if (null == role) {
            return Result.error("角色数据不存在");
        }
        List<RoleMenu> roleMenuList = roleMenuService.listByRoleId(role.getId());
        List<Long> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        RoleUpdateResponse response = mapperFacade.map(role, RoleUpdateResponse.class);
        response.setMenuIds(menuIds);
        return Result.success(response);
    }

    /**
     * 角色下拉选择列表
     * @return
     */
    @GetMapping("/selectList")
    public Result<List<Role>> selectList() {
        List<Role> roleList = roleService.listAll();
        List<Role> list = roleList.stream().filter(role -> role.getStatus().equals(Status.ENABLE.getStatus()))
                .collect(Collectors.toList());
        return Result.success(list);
    }

    /**
     * 保存角色
     * @param request   角色数据
     * @return
     */
    @PostMapping("/save")
    @Log(desc = "保存角色", type = LogType.SAVE)
    @RequiresPermissions("sys:role:save")
    public Result<Void> save(@RequestBody @Validated RoleFormRequest request) {
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
    @Log(desc = "更新角色", type = LogType.UPDATE)
    @RequiresPermissions("sys:role:update")
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
    @Log(desc = "删除角色", type = LogType.REMOVE)
    @RequiresPermissions("sys:role:remove")
    public Result<Role> remove(@RequestBody @Validated RemoveRequest request) {
        Role role = roleService.getById(request.getId());
        if (null != role) {
            roleService.removeById(request.getId());
        }

        return Result.success(role);
    }
}
