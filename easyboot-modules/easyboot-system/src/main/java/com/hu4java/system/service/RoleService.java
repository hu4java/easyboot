package com.hu4java.system.service;

import com.hu4java.base.service.Service;
import com.hu4java.system.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hu4java
 */
public interface RoleService extends Service<Role> {

    /**
     * 保存
     * @param role      角色
     * @param menuIds   菜单ID列表
     */
    @Transactional(rollbackFor = Exception.class)
    void save(Role role, List<Long> menuIds);

    /**
     * 更新
     * @param role      角色
     * @param menuIds   菜单ID列表
     */
    @Transactional(rollbackFor = Exception.class)
    void update(Role role, List<Long> menuIds);

    /**
     * 根据角色代码查询
     * @param code  角色代码
     * @return      角色数据
     */
    Role getByCode(String code);
}
