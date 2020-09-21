package com.huuu.web.system.response;

import com.huuu.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 菜单表格树
 * @author huuu
 */
@Getter
@Setter
public class MenuTreeTableResponse extends BaseResponse {
    private static final long serialVersionUID = -4742672786101353847L;

    private Long id;
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
    /** 状态： 0-正常 1-禁用*/
    private Integer status;
    /** 是否隐藏*/
    private boolean hidden;
    /** 排序*/
    private Integer orderNum;
    /** 子菜单*/
    private List<MenuTreeTableResponse> children;

    public List<MenuTreeTableResponse> getChildren() {
        if (CollectionUtils.isEmpty(children)) {
            return null;
        }
        return children;
    }
}
