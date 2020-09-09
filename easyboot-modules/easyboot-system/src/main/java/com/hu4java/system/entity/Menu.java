package com.hu4java.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单
 * @author hu4java
 */
@Getter
@Setter
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 8697255062174437919L;
    /** 标题*/
    private String title;
    /** 权限码*/
    private String code;
    /** 上级id*/
    private Long pid;
    /** 上级id关联字符串*/
    private String pids;
    /** 路径*/
    private String path;
    /** 图标*/
    private String icon;
    /** 类型：1-目录 2-菜单 3-按钮*/
    private Integer type;
    /** 状态： 0-正常 1-禁用*/
    private Integer status;
    /** 是否隐藏*/
    private Boolean hidden;
    /** 路由名*/
    private String routeName;
    /** 重定向*/
    private String redirect;
    /** 组件*/
    private String component;
    /** 隐藏菜单*/
    private Boolean hideChildrenInMenu;
    /** 排序*/
    private Integer orderNum;

    @TableField(exist = false)
    private List<Menu> children;
}
