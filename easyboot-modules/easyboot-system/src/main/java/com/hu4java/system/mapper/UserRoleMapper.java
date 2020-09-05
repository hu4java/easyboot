package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hu4java
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id删除
     * @param userId    用户id
     * @return          影响行数
     */
    default int deleteByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        return delete(queryWrapper);
    }

    /**
     * 根据角色id删除
     * @param roleId    用户id
     * @return          影响行数
     */
    default int deleteByRoleId(Long roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId, roleId);
        return delete(queryWrapper);
    }

    /**
     * 根据用户id查询关联数据
     * @param userId        用户id
     * @return              数据列表
     */
    default List<UserRole> selectByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        return selectList(queryWrapper);
    }

    /**
     * 根据角色id查询关联数据
     * @param roleId        用户id
     * @return              数据列表
     */
    default List<UserRole> selectByRoleId(Long roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId, roleId);
        return selectList(queryWrapper);
    }
}
