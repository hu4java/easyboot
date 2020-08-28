package com.hu4java.user.mapper;

import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.user.entity.Role;
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
}
