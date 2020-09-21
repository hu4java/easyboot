package com.huuu.web.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求数据
 * @author huuu
 */
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

}
