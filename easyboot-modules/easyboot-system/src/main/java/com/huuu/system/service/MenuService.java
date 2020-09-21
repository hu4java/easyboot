package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.condition.MenuCondition;
import com.huuu.system.entity.Menu;

import java.util.List;

/**
 * @author huuu
 */
public interface MenuService extends Service<Menu> {


    /**
     * 根据路由名查询
     * @param routeName 路由名
     * @return
     */
    Menu getByRouteName(String routeName);

    /**
     * 根据上级id查询树
     * @param pid
     * @return
     */
    List<Menu> listByPid(Long pid);

    /**
     * 根据上级id查询菜单树
     * @param pid   上级id
     * @return      菜单列表
     */
    List<Menu> listTreeByPid(Long pid);

    /**
     * 根据条件查询
     * @param condition 条件
     * @return
     */
    List<Menu> listTreeByCondition(MenuCondition condition);

    /**
     * 根据用户ID查询
     * @param userId    用户ID
     * @return
     */
    List<Menu> listByUserId(Long userId);
}
