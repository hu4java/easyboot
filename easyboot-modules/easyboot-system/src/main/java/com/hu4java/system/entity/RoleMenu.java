package com.hu4java.system.entity;

import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色菜单关联
 * @author hu4java
 */
@Getter
@Setter
public class RoleMenu extends BaseEntity {
    private static final long serialVersionUID = -7832631103950394843L;

    /** 角色id*/
    private Long roleId;
    /** 菜单id*/
    private Long menuId;
}
