package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.entity.UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huuu
 */
public interface UserRoleService extends Service<UserRole> {

    /**
     * 根据用户id删除
     * @param userId    用户地
     */
    @Transactional(rollbackFor = Exception.class)
    void removeByUserId(Long userId);

    /**
     * 根据角色id删除
     * @param roleId    用户地
     */
    @Transactional(rollbackFor = Exception.class)
    void removeByRoleId(Long roleId);

    /**
     * 根据用户id查询关联数据
     * @param userId        用户id
     * @return              数据列表
     */
    List<UserRole> listByUserId(Long userId);

    /**
     * 根据角色id查询关联数据
     * @param roleId        用户id
     * @return              数据列表
     */
    List<UserRole> listByRoleId(Long roleId);
}
