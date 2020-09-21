package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.condition.DeptCondition;
import com.huuu.system.entity.Dept;

import java.util.List;

/**
 * @author huuu
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
