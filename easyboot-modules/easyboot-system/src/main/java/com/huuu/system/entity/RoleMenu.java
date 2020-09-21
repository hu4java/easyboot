package com.huuu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huuu.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色菜单关联
 * @author huuu
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class RoleMenu extends BaseEntity {
    private static final long serialVersionUID = -7832631103950394843L;

    /** 角色id*/
    private Long roleId;
    /** 菜单id*/
    private Long menuId;
}
