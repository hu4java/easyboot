package com.hu4java.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author hu4java
 */
@Getter
@Setter
public class UserUpdateRequest extends UserFormRequest {
    private static final long serialVersionUID = 367614938200625924L;

    @NotNull(message = "用户id不能为空")
    private Long id;
}
