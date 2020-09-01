package com.hu4java.system.controller;

import com.hu4java.base.request.RemoveRequest;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.common.result.Result;
import com.hu4java.system.entity.Menu;
import com.hu4java.system.request.MenuSaveRequest;
import com.hu4java.system.request.MenuUpdateRequest;
import com.hu4java.system.response.MenuTreeTableResponse;
import com.hu4java.system.service.MenuService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理-菜单
 * 菜单管理
 * @author hu4java
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MapperFacade mapperFacade;

    /**
     * 菜单列表
     * @return          菜单数据
     */
    @GetMapping("/list")
    public Result<List<MenuTreeTableResponse>> list() {

        List<Menu> menuList = menuService.listTreeByPid(0L);
        List<MenuTreeTableResponse> list = mapperFacade.mapAsList(menuList, MenuTreeTableResponse.class);
        return Result.success(list);
    }

    /**
     * 根据id查询
     * @param request   查询id
     * @return
     */
    @GetMapping("/getById")
    public Result<MenuUpdateRequest> getById(@Validated ViewRequest request) {
        Menu menu = menuService.getById(request.getId());
        MenuUpdateRequest response = mapperFacade.map(menu, MenuUpdateRequest.class);
        return Result.success(response);
    }

    /**
     * 菜单保存
     * @param request 保存数据
     * @return
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody @Validated MenuSaveRequest request) {
        Menu menu = mapperFacade.map(request, Menu.class);
        menuService.save(menu);
        return Result.success();
    }

    /**
     * 菜单更新
     * @param request 更新数据
     * @return
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Validated MenuUpdateRequest request) {
        Menu menu = mapperFacade.map(request, Menu.class);
        menuService.update(menu);
        return Result.success();
    }

    /**
     * 删除
     * @param request   数据id
     * @return
     */
    @PostMapping("/remove")
    public Result<Void> remove(@Validated RemoveRequest request) {
        menuService.removeById(request.getId());
        return Result.success();
    }

}
