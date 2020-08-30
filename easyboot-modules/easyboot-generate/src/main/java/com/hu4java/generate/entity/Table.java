package com.hu4java.generate.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 表
 * @author chenzhenhu
 */
@Getter
@Setter
public class Table {

    /** 表名*/
    private String tableName;
    /** 注释*/
    private String tableComment;
    /** 数据库*/
    private String tableSchema;
    /** 创建时间*/
    private LocalDateTime createTime;
    /** 字符集*/
    private String tableCollation;
}
