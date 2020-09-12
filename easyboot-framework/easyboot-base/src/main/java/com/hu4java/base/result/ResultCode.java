package com.hu4java.base.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 * @author chenzhenhu
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(0, "成功"),

    ERROR(1, "失败"),

    NEED_LOGIN(1000, "未登录，请登录。"),

    UNAUTHORIZED(1001, "没有权限"),

    UNKNOWN_ACCOUNT(1002, "账户不存在"),

    USERNAME_OR_PASSWORD_ERROR(1003, "用户或密码错误"),

    OLD_PASSWORD_ERROR(1004, "原密码不正确"),

    THE_TWO_PASSWORD_INPUTS_ARE_INCONSISTENT(1005, "两次密码输入不一致"),

    PARAMETER_INVALID(1006, "参数校验错误"),

    NO_HANDLER(4000, "访问地址不存在"),

    INTERNAL_EXCEPTION(5000, "系统内部错误");

    private int code;

    private String message;


}
