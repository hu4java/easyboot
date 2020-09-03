package com.hu4java.system.service;

import com.hu4java.base.service.Service;
import com.hu4java.system.condition.DeptCondition;
import com.hu4java.system.entity.Dept;

import java.util.List;

/**
 * @author hu4java
 */
public interface DeptService extends Service<Dept> {

    /**
     * 根据上级id查询
     * @param pid   上级id
     * @return      部门列表
     */
    List<Dept> listByPid(Long pid);

    /**
     * 根据条件查询
     * @param condition 条件
     * @return
     */
    List<Dept> listByCondition(DeptCondition condition);
}
