package com.hu4java.web.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 令牌响应数据
 * @author hu4java
 */
@Getter
@Setter
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = -6554229479491524764L;

    /** 令牌*/
    private String token;
}
