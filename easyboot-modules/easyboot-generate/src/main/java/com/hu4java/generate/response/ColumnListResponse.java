package com.hu4java.generate.response;

import com.hu4java.base.response.BaseResponse;
import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hu4java
 */
@Getter
@Setter
public class ColumnListResponse extends BaseResponse {
    private static final long serialVersionUID = 8245661258679813975L;

    private String columnName;

    private String dataType;

    private String columnComment;

    private String fieldName;

    private String javaType;

    public String getFieldName() {
        if (null == columnName) {
            return null;
        }
        return StringUtils.underlineToCamel(columnName, false);
    }

    public String getJavaType() {
        if (null == dataType) {
            return null;
        }
        return CodeHelper.javaType(dataType);
    }
}
