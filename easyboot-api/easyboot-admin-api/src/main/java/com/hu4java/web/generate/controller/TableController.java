package com.hu4java.web.generate.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.result.Result;
import com.hu4java.generate.condition.TableCondition;
import com.hu4java.generate.entity.Column;
import com.hu4java.generate.entity.Table;
import com.hu4java.generate.request.TableInfoRequest;
import com.hu4java.generate.service.ColumnService;
import com.hu4java.generate.service.TableService;
import com.hu4java.web.generate.response.ColumnListResponse;
import com.hu4java.web.generate.response.TableInfoResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 代码生成-数据库表
 * @author hu4java
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private MapperFacade mapperFacade;

    @GetMapping("/list")
    public Result<Page<Table>> list(Page<Table> page, TableCondition condition) {
        page = tableService.listByPage(page, condition);
        return Result.success(page);
    }

    @GetMapping("/info")
    public Result<TableInfoResponse> info(@Validated TableInfoRequest request) {

        Table table = tableService.getByTableName(request.getTableName());
        if (null == table) {
            Result.error("表数据不存在");
        }
        TableInfoResponse response = mapperFacade.map(table, TableInfoResponse.class);
        List<Column> columnList = columnService.listByTableName(request.getTableName());
        List<ColumnListResponse> columnListResponseList = mapperFacade.mapAsList(columnList, ColumnListResponse.class);
        response.setColumnList(columnListResponseList);
        return Result.success(response);
    }
}
