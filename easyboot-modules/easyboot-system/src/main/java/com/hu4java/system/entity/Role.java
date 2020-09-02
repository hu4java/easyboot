package com.hu4java.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色
 * @author hu4java
 */
@Getter
@Setter
@TableName("sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = -1336733261483022857L;

    /** 角色名称*/
    private String name;
    /** 角色代码*/
    private String code;
    /** 状态： 0-正常 1-禁用*/
    private Integer status;
    /** 被追*/
    private String remark;
}
