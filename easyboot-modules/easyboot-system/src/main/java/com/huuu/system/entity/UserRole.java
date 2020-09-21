package com.huuu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huuu.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联
 * @author huuu
 */
@Getter
@Setter
@TableName("sys_user_role")
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = 9010409361715158089L;

    /** 用户id*/
    private Long userId;
    /** 角色id*/
    private Long roleId;
}
