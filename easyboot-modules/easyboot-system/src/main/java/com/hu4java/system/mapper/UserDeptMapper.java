package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.entity.UserDept;
import org.springframework.stereotype.Repository;

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
}
