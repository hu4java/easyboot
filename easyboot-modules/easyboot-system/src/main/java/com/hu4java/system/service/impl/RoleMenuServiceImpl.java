package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.RoleMenu;
import com.hu4java.system.mapper.RoleMenuMapper;
import com.hu4java.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Service
public class RoleMenuServiceImpl extends AbstractServiceImpl<RoleMenu, RoleMenuMapper> implements RoleMenuService {


    @Override
    public List<RoleMenu> listByRoleId(Long roleId) {
        return mapper.selectByRoleId(roleId);
    }

    @Override
    public void removeByRoleId(Long roleId) {
        mapper.deleteByRoleId(roleId);
    }

    @Override
    public void removeByMenuId(Long menuId) {
        mapper.deleteByMenuId(menuId);
    }
}
