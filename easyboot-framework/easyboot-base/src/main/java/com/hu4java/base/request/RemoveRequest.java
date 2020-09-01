package com.hu4java.base.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author hu4java
 */
@Getter
@Setter
public class RemoveRequest extends BaseRequest {
    private static final long serialVersionUID = 3486271397131421989L;

    /** 数据id*/
    @NotNull(message = "id 不能为空")
    private Long id;
}
