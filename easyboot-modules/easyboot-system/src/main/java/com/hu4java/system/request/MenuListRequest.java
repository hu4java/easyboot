package com.hu4java.system.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单列表请求数据
 * @author hu4java
 */
@Getter
@Setter
public class MenuListRequest extends BaseRequest {
    private static final long serialVersionUID = 1829919636645654298L;

    /** 菜单标题*/
    private String title;

}
