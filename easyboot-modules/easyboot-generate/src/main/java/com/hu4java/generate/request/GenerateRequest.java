package com.hu4java.generate.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hu4java
 */
@Getter
@Setter
public class GenerateRequest extends BaseRequest {
    private static final long serialVersionUID = 6820367315667062364L;

    private String tableName;

    private String module;

    private boolean removePrefix;

    private String basePackage;

    private String entityName;

    private String author;
}
