package com.hu4java.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.common.result.Result;
import com.hu4java.system.entity.Dict;
import com.hu4java.system.service.DictService;
import com.hu4java.web.system.request.DictTableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理-数据字典
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
@RestController
@RequestMapping("/dict")
public class DictController {

	@Autowired
	private DictService dictService;

	/**
	 * 字典列表
	 * @param request	参数
	 * @return
	 */
	@GetMapping("/list")
	public Result<Page<Dict>> list(DictTableRequest request) {
		Page<Dict> page = dictService.listByPage(request.toPage(), request.queryWrapper());
		return Result.success(page);
	}

}