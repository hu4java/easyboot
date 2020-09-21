package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.entity.RoleMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenzhenhu
 */
public interface RoleMenuService extends Service<RoleMenu> {

    /**
     * 根据角色ID查询
     * @param roleId    角色ID
     * @return
     */
    List<RoleMenu> listByRoleId(Long roleId);

    /**
     * 根据角色ID删除
     * @param roleId    角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    void removeByRoleId(Long roleId);

    /**
     * 根据菜单ID删除
     * @param menuId    菜单ID
     */
    @Transactional(rollbackFor = Exception.class)
    void removeByMenuId(Long menuId);
}
