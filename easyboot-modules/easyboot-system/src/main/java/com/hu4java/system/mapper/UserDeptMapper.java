package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.entity.UserDept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Repository
public interface UserDeptMapper extends BaseMapper<UserDept> {

    /**
     * 根据用户ID删除
     * @param userId    用户ID
     * @return          影响行数
     */
    default int deleteByUserId(Long userId) {
        LambdaQueryWrapper<UserDept> queryWrapper = Wrappers.lambdaQuery(UserDept.class);
        queryWrapper.eq(UserDept::getUserId, userId);
        return delete(queryWrapper);
    }

    /**
     * 根据部门ID删除
     * @param deptId    部门ID
     * @return          影响行数
     */
    default int deleteByDeptId(Long deptId) {
        LambdaQueryWrapper<UserDept> queryWrapper = Wrappers.lambdaQuery(UserDept.class);
        queryWrapper.eq(UserDept::getDeptId, deptId);
        return delete(queryWrapper);
    }

    /**
     * 根据用户id查询
     * @param userId    用户id
     * @return          数据列表
     */
    default List<UserDept> selectByUserId(Long userId) {
        LambdaQueryWrapper<UserDept> queryWrapper = Wrappers.lambdaQuery(UserDept.class);
        queryWrapper.eq(UserDept::getUserId, userId);
        return selectList(queryWrapper);
    }

    /**
     * 根据部门id查询
     * @param deptId    部门id
     * @return          数据列表
     */
    default List<UserDept> selectByDeptId(Long deptId) {
        LambdaQueryWrapper<UserDept> queryWrapper = Wrappers.lambdaQuery(UserDept.class);
        queryWrapper.eq(UserDept::getDeptId, deptId);
        return selectList(queryWrapper);
    }
}
