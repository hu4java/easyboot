package com.huuu.system.service.impl;

import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.system.entity.UserDept;
import com.huuu.system.mapper.UserDeptMapper;
import com.huuu.system.service.UserDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huuu
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
