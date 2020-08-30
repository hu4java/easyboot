package com.hu4java.generate.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author hu4java
 */
@Getter
@Setter
public class GenerateFieldRequest extends BaseRequest {
    private static final long serialVersionUID = -3752721360126578947L;

    /** 列名称*/
    @NotBlank(message = "列名称不能为空")
    private String columnName;
    /** 字段名称*/
    @NotBlank(message = "字段名称不能为空")
    private String fieldName;
    /** 数据类型*/
    @NotBlank(message = "数据类型不能为空")
    private String dateType;
    /** Java类型*/
    @NotBlank(message = "Java类型不能为空")
    private String javaType;
    /** 注释*/
    @NotBlank(message = "注释不能为空")
    private String columnComment;

}
