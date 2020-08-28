package com.hu4java.generate.mapper;

import com.hu4java.generate.entity.Column;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Repository
public interface ColumnMapper {

    /**
     * 根据表名查询列
     * @param tableName 表名
     * @return          列表
     */
    List<Column> selectByTableName(String tableName);
}
