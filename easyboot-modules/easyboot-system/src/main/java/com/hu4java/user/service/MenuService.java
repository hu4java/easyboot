package com.hu4java.user.service;

import com.hu4java.base.service.Service;
import com.hu4java.user.entity.Menu;

import java.util.List;

/**
 * @author hu4java
 */
public interface MenuService extends Service<Menu> {

    /**
     * 根据上级id查询
     * @param pid   上级id
     * @return      菜单列表
     */
    List<Menu> listByPid(Long pid);
}
