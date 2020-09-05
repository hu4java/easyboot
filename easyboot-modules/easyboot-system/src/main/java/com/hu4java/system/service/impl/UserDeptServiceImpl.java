package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.UserDept;
import com.hu4java.system.mapper.UserDeptMapper;
import com.hu4java.system.service.UserDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hu4java
 */
@Service
public class UserDeptServiceImpl extends AbstractServiceImpl<UserDept, UserDeptMapper> implements UserDeptService {

    @Override
    public void removeByUserId(Long userId) {
        mapper.deleteByUserId(userId);
    }

    @Override
    public void removeByDeptId(Long deptId) {
        mapper.selectByDeptId(deptId);
    }

    @Override
    public List<UserDept> listByUserId(Long userId) {
        return mapper.selectByUserId(userId);
    }

    @Override
    public List<UserDept> listByDeptId(Long deptId) {
        return mapper.selectByDeptId(deptId);
    }
}
