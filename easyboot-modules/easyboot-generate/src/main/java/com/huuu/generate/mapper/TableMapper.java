package com.huuu.generate.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.generate.condition.TableCondition;
import com.huuu.generate.entity.Table;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据条件查询
     * @param page      分页
     * @param condition 条件
     * @return          表
     */
    Page<Table> selectByPage(Page<Table> page, @Param("condition") TableCondition condition);
}
