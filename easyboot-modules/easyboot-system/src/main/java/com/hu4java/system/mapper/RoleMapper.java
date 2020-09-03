package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hu4java
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询
     * @param userId    用户id
     * @return          角色列表
     */
    List<Role> selectByUserId(Long userId);

    /**
     * 根据角色代码查询
     * @param code  角色代码
     * @return      角色
     */
    default Role selectByCode(String code) {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery(Role.class);
        queryWrapper.eq(Role::getCode, code);
        return selectOne(queryWrapper);
    }
}
