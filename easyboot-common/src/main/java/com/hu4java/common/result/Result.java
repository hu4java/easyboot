package com.hu4java.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 返回数据封装类
 * @author chenzhenhu
 */
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -2708629255697329692L;

    private Integer code;

    private String message;

    private T data;

    private boolean success;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null, false);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ResultCode.ERROR.getCode(), message, null, false);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null, false);
    }

    public boolean isSuccess() {
        return Objects.equals(ResultCode.SUCCESS.getCode(), code);
    }
}
