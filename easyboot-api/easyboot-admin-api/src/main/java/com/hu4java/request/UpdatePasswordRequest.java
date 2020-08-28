package com.hu4java.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码
 * @author hu4java
 */
@Getter
@Setter
public class UpdatePasswordRequest extends BaseRequest {
    private static final long serialVersionUID = -7462469508189052537L;

    /** 原密码*/
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;
    /** 新密码*/
    @NotBlank(message = "新密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度 6 - 20")
    private String newPassword;
    /** 确认密码*/
    @NotBlank(message = "确认密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度 6 - 20")
    private String confirmPassword;
}
