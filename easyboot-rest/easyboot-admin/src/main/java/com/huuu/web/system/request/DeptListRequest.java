package com.huuu.web.system.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门列表请求参数
 * @author chenzhenhu
 */
@Getter
@Setter
public class DeptListRequest extends BaseRequest {
    private static final long serialVersionUID = 3169540294506210459L;

    /** 部门名称*/
    private String name;
}
