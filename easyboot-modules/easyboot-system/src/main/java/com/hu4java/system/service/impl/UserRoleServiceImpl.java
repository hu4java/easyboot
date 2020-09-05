package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.UserRole;
import com.hu4java.system.mapper.UserRoleMapper;
import com.hu4java.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hu4java
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
