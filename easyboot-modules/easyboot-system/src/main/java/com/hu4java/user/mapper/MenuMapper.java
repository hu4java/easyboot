package com.hu4java.user.mapper;

import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.user.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单 Mapper
 * @author hu4java
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据上级id查询
     * @param pid   上级id
     * @return      菜单列表
     */
    List<Menu> selectByPid(Long pid);

    /**
     * 根据角色id查询
     * @param roleId    角色id
     * @return          菜单列表
     */
    List<Menu> selectByRoleId(Long roleId);

    /**
     * 根据用户id查询
     * @param userId    用户id
     * @return          菜单列表
     */
    List<Menu> selectByUserId(Long userId);
}
