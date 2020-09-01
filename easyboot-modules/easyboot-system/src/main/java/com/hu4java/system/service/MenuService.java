package com.hu4java.system.service;

import com.hu4java.base.service.Service;
import com.hu4java.system.entity.Menu;

import java.util.List;

/**
 * @author hu4java
 */
public interface MenuService extends Service<Menu> {


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
}
