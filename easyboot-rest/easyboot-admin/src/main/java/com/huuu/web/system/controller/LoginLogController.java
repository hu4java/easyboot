package com.huuu.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.base.result.Result;
import com.huuu.system.entity.LoginLog;
import com.huuu.system.service.LoginLogService;
import com.huuu.web.system.request.LoginLogTableRequest;
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