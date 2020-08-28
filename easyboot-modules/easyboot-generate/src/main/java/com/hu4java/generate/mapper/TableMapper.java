package com.hu4java.generate.mapper;

import com.hu4java.generate.entity.Table;
import org.springframework.stereotype.Repository;

/**
 * @author chenzhenhu
 */
@Repository
public interface TableMapper {

    /**
     * 根据表名查询
     * @param tableName 表名
     * @return          表
     */
    Table selectByTableName(String tableName);
}
