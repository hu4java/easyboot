package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.Role;
import com.hu4java.system.entity.RoleMenu;
import com.hu4java.system.mapper.RoleMapper;
import com.hu4java.system.service.RoleMenuService;
import com.hu4java.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hu4java
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleMapper> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public void save(Role role, List<Long> menuIds) {
        mapper.insert(role);
        saveRoleMenu(role.getId(), menuIds);
    }

    @Override
    public void update(Role role, List<Long> menuIds) {
        mapper.updateById(role);
        roleMenuService.removeByRoleId(role.getId());
        saveRoleMenu(role.getId(), menuIds);
    }

    @Override
    public boolean removeById(Long id) {
        roleMenuService.removeByRoleId(id);
        return super.removeById(id);
    }

    @Override
    public Role getByCode(String code) {
        return mapper.selectByCode(code);
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        return mapper.selectByUserId(userId);
    }


    /**
     * 保存角色菜单关联表
     * @param roleId    角色ID
     * @param menuIds   菜单ID列表
     */
    private void saveRoleMenu(Long roleId, List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        List<RoleMenu> roleMenuList = new ArrayList<>(menuIds.size());
        for (Long menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenuList.add(roleMenu);
        }
        roleMenuService.saveBatch(roleMenuList);
    }
}
