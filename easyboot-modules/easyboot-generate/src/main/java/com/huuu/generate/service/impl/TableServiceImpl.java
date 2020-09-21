package com.huuu.generate.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.generate.condition.TableCondition;
import com.huuu.generate.entity.Table;
import com.huuu.generate.mapper.TableMapper;
import com.huuu.generate.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huuu
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public Page<Table> listByPage(Page<Table> page, TableCondition condition) {
        return tableMapper.selectByPage(page, condition);
    }

    @Override
    public Table getByTableName(String tableName) {
        return tableMapper.selectByTableName(tableName);
    }
}
