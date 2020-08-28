package com.hu4java.user.entity;

import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户部门关联
 * @author hu4java
 */
@Getter
@Setter
public class UserDept extends BaseEntity {
    private static final long serialVersionUID = -165163747105455582L;

    /** 用户id*/
    private Long userId;
    /** 部门id*/
    private Long deptId;
}
