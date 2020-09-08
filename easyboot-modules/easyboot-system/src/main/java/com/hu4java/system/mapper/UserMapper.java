package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.condition.UserCondition;
import com.hu4java.system.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户mapper
 * @author chenzhenhu
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询
     * @param username  用户名
     * @return          用户对象
     */
    default User selectByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return selectOne(queryWrapper);
    }

    /**
     * 分页查询
     * @param page      分页
     * @param condition 条件
     * @return
     */
    Page<User> selectByPage(Page<User> page, @Param("condition") UserCondition condition);

    /**
     * 根据手机号查询
     * @param mobile    手机号
     * @return
     */
    default User selectByMobile(String mobile) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMobile, mobile);
        return selectOne(queryWrapper);
    }

    /**
     * 根据邮箱查询
     * @param email 邮箱
     * @return
     */
    default User selectByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return selectOne(queryWrapper);
    }
}
