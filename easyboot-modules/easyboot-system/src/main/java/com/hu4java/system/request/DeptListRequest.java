package com.hu4java.system.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class DeptListRequest extends BaseRequest {
    private static final long serialVersionUID = 3169540294506210459L;

    /** 部门名称*/
    private String name;
}
