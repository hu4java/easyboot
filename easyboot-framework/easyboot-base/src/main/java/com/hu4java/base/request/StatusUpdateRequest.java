package com.hu4java.base.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author hu4java
 */
@Getter
@Setter
public class StatusUpdateRequest extends BaseRequest {
    private static final long serialVersionUID = -7258945802912650725L;

    /** 数据id*/
    @NotNull(message = "id 不能为空")
    private Long id;

    /** 状态*/
    @NotNull(message = "状态不能为空")
    private Integer status;
}
