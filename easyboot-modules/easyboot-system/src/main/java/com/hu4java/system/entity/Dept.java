package com.hu4java.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_dept")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 4749015705054054327L;

    /** 部门名称*/
    private String name;
    /** 部门编码*/
    private String code;
    /** 上级部门id*/
    private Long pid;
    /** 上级部门id集合*/
    private String pids;
    /** 联系电话*/
    private String phone;
    /** 邮箱*/
    private String email;
    /** 排序*/
    private Integer orderNum;
    /** 状态：0-正常 1-禁用*/
    private Integer status;
    /** 备注*/
    private String remark;

    /** 部门子列表*/
    @TableField(exist = false)
    private List<Dept> children;
}
