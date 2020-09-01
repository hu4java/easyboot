package com.hu4java.system.entity;

import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联
 * @author hu4java
 */
@Getter
@Setter
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = 9010409361715158089L;

    /** 用户id*/
    private Long userId;
    /** 角色id*/
    private Long roleId;
}
