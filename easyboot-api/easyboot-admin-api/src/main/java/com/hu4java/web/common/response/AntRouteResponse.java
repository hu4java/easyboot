package com.hu4java.web.common.response;

import com.hu4java.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Ant Design Vue 路由数据
 * @author hu4java
 */
@Getter
@Setter
public class AntRouteResponse extends BaseResponse {
    private static final long serialVersionUID = -5862498181575968519L;

    /** 标题*/
    private String title;
    /** 权限码*/
    private String code;
    /** 上级id*/
    private Long pid;
    /** 路径*/
    private String path;
    /** 图标*/
    private String icon;
    /** 类型：1-目录 2-菜单 3-按钮*/
    private Integer type;
    /** 是否隐藏*/
    private boolean hidden;
    /** 路由名*/
    private String routeName;
    /** 组件*/
    private String component;
    /** 隐藏*/
    private boolean hideChildrenInMenu;
    /** 子路由*/
    private List<AntRouteResponse> children;
}
