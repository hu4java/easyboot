package com.huuu.web.generate.response;

import com.huuu.base.response.BaseResponse;
import com.huuu.generate.helper.CodeHelper;
import com.huuu.util.StrUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author huuu
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
        return StrUtils.underlineToCamel(columnName, false);
    }

    public String getJavaType() {
        if (null == dataType) {
            return null;
        }
        return CodeHelper.javaType(dataType);
    }
}
