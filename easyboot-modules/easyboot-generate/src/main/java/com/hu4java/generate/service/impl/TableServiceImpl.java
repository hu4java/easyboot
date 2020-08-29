package com.hu4java.generate.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.generate.condition.TableCondition;
import com.hu4java.generate.entity.Table;
import com.hu4java.generate.mapper.TableMapper;
import com.hu4java.generate.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hu4java
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
