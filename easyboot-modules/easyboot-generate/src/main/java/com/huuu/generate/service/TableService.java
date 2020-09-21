package com.huuu.generate.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.generate.condition.TableCondition;
import com.huuu.generate.entity.Table;

/**
 * 数据表
 * @author huuu
 */
public interface TableService {

    /**
     * 分页查询
     * @param page
     * @param condition
     * @return
     */
    Page<Table> listByPage(Page<Table> page, TableCondition condition);

    /**
     * 根据表名查询
     * @param tableName 表名
     * @return          表数据
     */
    Table getByTableName(String tableName);
}
