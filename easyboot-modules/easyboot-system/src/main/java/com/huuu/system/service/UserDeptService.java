package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.entity.UserDept;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huuu
 */
public interface UserDeptService extends Service<UserDept> {

    /**
     * 根据用户id删除
     * @param userId    用户地
     */
    @Transactional(rollbackFor = Exception.class)
    void removeByUserId(Long userId);

    /**
     * 根据角色id删除
     * @param deptId    用户地
     */
    @Transactional(rollbackFor = Exception.class)
    void removeByDeptId(Long deptId);

    /**
     * 根据用户id查询
     * @param userId    用户id
     * @return          数据列表
     */
    List<UserDept> listByUserId(Long userId);

    /**
     * 根据部门id查询
     * @param deptId    部门id
     * @return          数据列表
     */
    List<UserDept> listByDeptId(Long deptId);
}
