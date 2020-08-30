package com.hu4java.generate.response;

import com.hu4java.base.response.BaseResponse;
import com.hu4java.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hu4java
 */
@Getter
@Setter
public class TableInfoResponse extends BaseResponse {
    private static final long serialVersionUID = -2472394865967208625L;

    /** 表名*/
    private String tableName;
    /** 注释*/
    private String tableComment;
    /** 创建时间*/
    private LocalDateTime createTime;
    /** 字符集*/
    private String tableCollation;
    /** 实体类名称*/
    private String entityName;

    private List<ColumnListResponse> columnList;

    public String getEntityName() {
        if (null == tableName) {
            return null;
        }
        return StringUtils.underlineToCamel(tableName, true);
    }
}
