package com.huuu.generate.entity;

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

    private String columnName;

    private String columnComment;

    private String dataType;

}
