package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.condition.MenuCondition;
import com.hu4java.system.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单 Mapper
 * @author hu4java
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据路由名查询
     * @param routeName 路由名
     * @return
     */
    default Menu selectByRouteName(String routeName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getRouteName, routeName);
        return selectOne(queryWrapper);
    }

    /**
     * 根据上级id查询
     * @param pid   上级id
     * @return      菜单列表
     */
    default List<Menu> selectByPid(Long pid) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getPid, pid);
        queryWrapper.orderByAsc(Menu::getOrderNum);
        return selectList(queryWrapper);
    }

    /**
     * 根据上级id查询数据树
     * @param pid   上级id
     * @return      菜单列表
     */
    List<Menu> selectTreeByPid(Long pid);

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

    /**
     * 根据条件查询
     * @param condition 条件
     * @return
     */
    List<Menu> selectTreeByCondition(@Param("condition") MenuCondition condition);
}
