package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.common.constant.Constants;
import com.hu4java.system.condition.DeptCondition;
import com.hu4java.system.entity.Dept;
import com.hu4java.system.mapper.DeptMapper;
import com.hu4java.system.mapper.UserDeptMapper;
import com.hu4java.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hu4java
 */
@Service
public class DeptServiceImpl extends AbstractServiceImpl<Dept, DeptMapper> implements DeptService {

    @Autowired
    private UserDeptMapper userDeptMapper;

    @Override
    public boolean save(Dept entity) {
        if (entity.getPid().equals(Constants.TOP_PID)) {
            entity.setPids("0");
        } else {
            Dept parent = mapper.selectById(entity.getPid());
            entity.setPids(parent.getPids() + "," + parent.getId());
        }
        return super.save(entity);
    }

    @Override
    public boolean update(Dept entity) {
        Dept old = mapper.selectById(entity.getId());
        if (!old.getPid().equals(entity.getPid())) {
            Dept parent = mapper.selectById(entity.getPid());
            entity.setPids(parent.getPids() + "," + parent.getId());
        }
        return super.update(entity);
    }

    @Override
    public boolean removeById(Long id) {
        userDeptMapper.deleteByDeptId(id);
        return super.removeById(id);
    }

    @Override
    public List<Dept> listByPid(Long pid) {
        return mapper.selectByPid(pid);
    }

    @Override
    public List<Dept> listByCondition(DeptCondition condition) {
        return mapper.selectByCondition(condition);
    }
}
