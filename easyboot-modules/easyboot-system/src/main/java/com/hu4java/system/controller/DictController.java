package com.hu4java.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.common.result.Result;
import com.hu4java.system.condition.DictCondition;
import com.hu4java.system.entity.Dict;
import com.hu4java.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
@RestController
@RequestMapping("/system/Dict")
public class DictController {

	@Autowired
	private DictService dictService;

	@GetMapping("/list")
	public Result<Page<Dict>> list(Page<Dict> page, DictCondition condition) {
		page = dictService.listByPage(page, condition.queryWrapper());
		return Result.success(page);
	}

}