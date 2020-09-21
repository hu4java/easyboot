package com.huuu.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.mapper.BaseMapper;
import com.huuu.system.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huuu
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据角色ID查询
     * @param roleId    角色ID
     * @return          数据列表
     */
    default List<RoleMenu> selectByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, roleId);
        return selectList(queryWrapper);
    }

    /**
     * 根据角色id删除
     * @param roleId    角色id
     * @return          影响行数
     */
    default int deleteByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, roleId);
        return delete(queryWrapper);
    }

    /**
     * 根据菜单id删除
     * @param menuId    菜单id
     * @return          影响行数
     */
    default int deleteByMenuId(Long menuId) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getMenuId, menuId);
        return delete(queryWrapper);
    }
}
