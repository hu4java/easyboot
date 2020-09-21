package com.huuu.web.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 用户更新表单参数
 * @author huuu
 */
@Getter
@Setter
public class UserUpdateRequest extends UserFormRequest {
    private static final long serialVersionUID = 367614938200625924L;

    /** 用户id*/
    @NotNull(message = "用户id不能为空")
    private Long id;
}
