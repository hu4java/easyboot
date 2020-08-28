package com.hu4java.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 部门
 * @author hu4java
 */
@Getter
@Setter
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 4749015705054054327L;

    /** 部门名称*/
    private String name;
    /** 部门编码*/
    private String code;
    /** 上级部门id*/
    private Long pid;
    /** 状态：0-正常 1-禁用*/
    private Integer status;

    /** 部门子列表*/
    @TableField(exist = false)
    private List<Dept> children;
}
