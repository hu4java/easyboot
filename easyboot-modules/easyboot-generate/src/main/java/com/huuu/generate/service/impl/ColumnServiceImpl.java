package com.huuu.generate.service.impl;

import com.huuu.generate.entity.Column;
import com.huuu.generate.mapper.ColumnMapper;
import com.huuu.generate.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huuu
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;

    @Override
    public List<Column> listByTableName(String tableName) {
        return columnMapper.selectByTableName(tableName);
    }
}
