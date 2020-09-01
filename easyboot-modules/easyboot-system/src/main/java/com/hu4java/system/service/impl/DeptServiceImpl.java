package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.Dept;
import com.hu4java.system.mapper.DeptMapper;
import com.hu4java.system.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hu4java
 */
@Service
public class DeptServiceImpl extends AbstractServiceImpl<Dept, DeptMapper> implements DeptService {


    @Override
    public List<Dept> listByPid(Long pid) {
        return mapper.selectByPid(pid);
    }
}
