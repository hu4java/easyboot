package com.hu4java.generate.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hu4java
 */
@Getter
@Setter
public class GenerateFieldRequest extends BaseRequest {
    private static final long serialVersionUID = -3752721360126578947L;

    private String columnName;

    private String fieldName;

    private String dateType;

    private String javaType;

    private String comment;

}
