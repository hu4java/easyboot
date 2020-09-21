package com.huuu.web.generate.response;

import com.huuu.base.response.BaseResponse;
import com.huuu.util.StrUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author huuu
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
        return StrUtils.underlineToCamel(tableName, true);
    }
}
