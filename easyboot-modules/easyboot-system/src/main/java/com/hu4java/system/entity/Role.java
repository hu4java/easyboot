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

    private String name;

    private String code;

    private Integer status;
}
