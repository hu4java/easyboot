package com.hu4java.web.system.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author hu4java
 */
@Getter
@Setter
public class UserSaveRequest extends UserFormRequest {
    private static final long serialVersionUID = -3174410192141051004L;

    /** 登录账号*/
    @NotBlank(message = "登录账号不能为空")
    @Length(min = 2, max = 50, message = "登录账号长度 2 ~ 50")
    private String username;
    /** 登录密码*/
    @NotBlank(message = "登录密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度 6 ~ 20 ")
    private String password;
}
