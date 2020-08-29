package com.hu4java.generate.service;

import com.hu4java.generate.entity.Column;

import java.util.List;

/**
 * @author hu4java
 */
public interface ColumnService {

    /**
     * 根据表名查询
     * @param tableName 表名
     * @return          列数据
     */
    List<Column> listByTableName(String tableName);
}
