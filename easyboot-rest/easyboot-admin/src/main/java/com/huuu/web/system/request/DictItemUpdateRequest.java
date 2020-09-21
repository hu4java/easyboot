package com.huuu.web.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 数据更新表单
 * @author huuu
 */
@Getter
@Setter
public class DictItemUpdateRequest extends DictItemFormRequest {
    private static final long serialVersionUID = -7044277641759536863L;

    /** 数据id*/
    @NotNull(message = "数据id不能为空")
    private Long id;
}
