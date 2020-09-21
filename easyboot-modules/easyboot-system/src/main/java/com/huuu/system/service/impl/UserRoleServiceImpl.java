package com.huuu.system.service.impl;

import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.system.entity.UserRole;
import com.huuu.system.mapper.UserRoleMapper;
import com.huuu.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huuu
 */
@Service
public class UserRoleServiceImpl extends AbstractServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {


    @Override
    public void removeByUserId(Long userId) {
        mapper.deleteByUserId(userId);
    }

    @Override
    public void removeByRoleId(Long roleId) {
        mapper.deleteByRoleId(roleId);
    }

    @Override
    public List<UserRole> listByUserId(Long userId) {
        return mapper.selectByUserId(userId);
    }

    @Override
    public List<UserRole> listByRoleId(Long roleId) {
        return mapper.selectByRoleId(roleId);
    }
}
