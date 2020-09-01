package com.hu4java.base.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author hu4java
 */
@Getter
@Setter
public class RemoveBatchRequest extends BaseRequest {
    private static final long serialVersionUID = 6167029978946601839L;

    /** 数据id*/
    @NotNull(message = "ids 不能为空")
    private Long[] ids;
}
