package com.huuu.generate.service;

import com.huuu.generate.entity.Column;

import java.util.List;

/**
 * @author huuu
 */
public interface ColumnService {

    /**
     * 根据表名查询
     * @param tableName 表名
     * @return          列数据
     */
    List<Column> listByTableName(String tableName);
}
