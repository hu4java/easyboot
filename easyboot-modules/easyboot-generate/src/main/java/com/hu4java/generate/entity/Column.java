package com.hu4java.generate.entity;

import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 列
 * @author chenzhenhu
 */
@Getter
@Setter
public class Column {

    private String tableName;

    private String name;

    private String comment;

    private String type;

    private String fieldName;

    private String javaType;

    public String getFieldName() {
        if (null != fieldName && !"".equals(fieldName.trim())) {
            return fieldName;
        }
        if (null == name || "".equals(name.trim())) {
            return null;
        }

        fieldName = StringUtils.underlineToCamel(name, false);
        return fieldName;
    }

    public String getJavaType() {
        return CodeHelper.javaType(type);
    }
}
