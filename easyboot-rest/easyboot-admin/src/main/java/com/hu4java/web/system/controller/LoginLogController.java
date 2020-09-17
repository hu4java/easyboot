package com.hu4java.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.result.Result;
import com.hu4java.system.entity.LoginLog;
import com.hu4java.system.service.LoginLogService;
import com.hu4java.web.system.request.LoginLogTableRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 登录日志
 * @author	EasyBoot
 * @date	2020-09-15 11:16:28
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

	@Autowired
	private LoginLogService loginLogService;

	@GetMapping("/list")
	@RequiresPermissions("sys:loginLog:view")
	public Result<Page<LoginLog>> list(LoginLogTableRequest request) {
		Page<LoginLog> page = loginLogService.listByPage(request.toPage(), request.queryWrapper());
		return Result.success(page);
	}

}