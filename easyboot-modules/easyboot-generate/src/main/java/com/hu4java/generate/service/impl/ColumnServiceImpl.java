package com.hu4java.generate.service.impl;

import com.hu4java.generate.entity.Column;
import com.hu4java.generate.mapper.ColumnMapper;
import com.hu4java.generate.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hu4java
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
