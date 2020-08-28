package com.hu4java.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author hu4java
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse implements Serializable {
    private static final long serialVersionUID = -8656443326857323652L;
}
