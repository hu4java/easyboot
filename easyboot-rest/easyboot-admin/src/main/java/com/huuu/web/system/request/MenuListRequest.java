package com.huuu.web.system.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单列表请求数据
 * @author huuu
 */
@Getter
@Setter
public class MenuListRequest extends BaseRequest {
    private static final long serialVersionUID = 1829919636645654298L;

    /** 菜单标题*/
    private String title;

}
