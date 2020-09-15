package com.hu4java.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.base.response.PageResponse;
import com.hu4java.base.result.Result;
import com.hu4java.system.entity.OperationLog;
import com.hu4java.system.service.OperationLogService;
import com.hu4java.web.system.request.OperationLogTableRequest;
import com.hu4java.web.system.response.OperateLogTableResponse;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 操作日志
 * @author	EasyBoot
 * @date	2020-09-12 18:11:57
 */
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private MapperFacade mapperFacade;

	@GetMapping("/list")
	@RequiresPermissions("sys:operationLog:view")
	public Result<PageResponse<OperateLogTableResponse>> list(OperationLogTableRequest request) {
		Page<OperationLog> page = operationLogService.listByPage(request.toPage(), request.queryWrapper());
		Type<Page<OperationLog>> fromType = new TypeBuilder<Page<OperationLog>>(){}.build();
		Type<PageResponse<OperateLogTableResponse>> toType = new TypeBuilder<PageResponse<OperateLogTableResponse>>(){}.build();
		PageResponse<OperateLogTableResponse> response = mapperFacade.map(page, fromType, toType);
		return Result.success(response);
	}

	@GetMapping("/detail")
	@RequiresPermissions("sys:operationLog:view")
	public Result<OperationLog> detail(@Validated ViewRequest request) {
		OperationLog log = operationLogService.getById(request.getId());
		return Result.success(log);
	}
}