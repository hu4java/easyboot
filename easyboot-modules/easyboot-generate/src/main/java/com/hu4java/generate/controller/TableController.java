package com.hu4java.generate.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.common.result.Result;
import com.hu4java.generate.condition.TableCondition;
import com.hu4java.generate.entity.Column;
import com.hu4java.generate.entity.Table;
import com.hu4java.generate.request.TableInfoRequest;
import com.hu4java.generate.service.ColumnService;
import com.hu4java.generate.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hu4java
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;
    @Autowired
    private ColumnService columnService;

    @GetMapping("/list")
    public Result<Page<Table>> list(Page<Table> page, TableCondition condition) {
        page = tableService.listByPage(page, condition);
        return Result.success(page);
    }

    @GetMapping("/info")
    public Result<Table> info(@Validated TableInfoRequest request) {

        Table table = tableService.getByTableName(request.getTableName());
        if (null == table) {
            Result.error("表数据不存在");
        }

        List<Column> columnList = columnService.listByTableName(request.getTableName());
        table.setColumnList(columnList);
        return Result.success(table);
    }
}
