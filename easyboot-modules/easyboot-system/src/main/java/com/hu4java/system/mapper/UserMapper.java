package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.entity.User;
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
}
