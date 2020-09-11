package com.hu4java.web.system.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 重置密码
 * @author chenzhenhu
 */
@Getter
@Setter
public class UserResetPasswordRequest extends BaseRequest {
    private static final long serialVersionUID = 2517915023186250873L;

    /** 用户ID*/
    @NotNull(message = "用户ID不能为空")
    private Long id;
    /** 密码*/
    @NotBlank(message = "密码不能为空")
    private String password;
    /** 确认密码*/
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
