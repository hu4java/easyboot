package com.huuu.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author huuu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse implements Serializable {
    private static final long serialVersionUID = -8656443326857323652L;
}
