package com.huuu.base.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author huuu
 */
@Getter
@Setter
public class ViewRequest extends BaseRequest {
    private static final long serialVersionUID = 8800423329686359772L;

    /** 数据id*/
    @NotNull(message = "id 不能为空")
    private Long id;
}
