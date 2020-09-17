package com.hu4java.web.system.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单表单参数
 * @author hu4java
 */
@Getter
@Setter
public class MenuFormRequest extends BaseRequest {
    private static final long serialVersionUID = -4800540995326371779L;

    /** 标题*/
    @NotBlank(message = "标题不能为空")
    private String title;
    /** 权限码*/
    private String code;
    /** 上级id*/
    @NotNull(message = "上级id不能为空")
    private Long pid;
    /** 路径*/
    private String path;
    /** 图标*/
    private String icon;
    /** 类型：1-目录 2-菜单 3-按钮*/
    @NotNull(message = "类型不能为空")
    private Integer type;
    /** 状态： 0-正常 1-禁用*/
    @NotNull(message = "状态不能为空")
    private Integer status;
    /** 是否隐藏*/
    @NotNull(message = "是否隐藏不能为空")
    private Boolean hidden;
    /** 路由名*/
    private String routeName;
    /** 重定向*/
    private String redirect;
    /** 组件*/
    private String component;
    /** 隐藏菜单*/
    private boolean hideChildrenInMenu;
    /** 排序*/
    @NotNull(message = "排序不能为空")
    private Integer orderNum;
}
