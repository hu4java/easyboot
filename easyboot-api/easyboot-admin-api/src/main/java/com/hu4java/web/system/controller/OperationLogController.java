package com.hu4java.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.result.Result;
import com.hu4java.system.entity.OperationLog;
import com.hu4java.system.service.OperationLogService;
import com.hu4java.web.system.request.OperationLogTableRequest;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Result<Page<OperationLog>> list(OperationLogTableRequest request) {
		Page<OperationLog> page = operationLogService.listByPage(request.toPage(), request.queryWrapper());
		return Result.success(page);
	}

}