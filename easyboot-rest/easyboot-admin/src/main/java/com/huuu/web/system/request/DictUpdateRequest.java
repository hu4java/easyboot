package com.huuu.web.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 字典更新表单
 * @author huuu
 */
@Getter
@Setter
public class DictUpdateRequest extends DictFormRequest {
    private static final long serialVersionUID = -2553637138779455007L;

    /** 字典id*/
    @NotNull(message = "id不能为空")
    private Long id;
}
