package com.hu4java.web.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.common.result.Result;
import com.hu4java.system.condition.DictItemCondition;
import com.hu4java.system.entity.DictItem;
import com.hu4java.system.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理-数据字典数据
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
@RestController
@RequestMapping("/system/DictItem")
public class DictItemController {

	@Autowired
	private DictItemService dictItemService;

	@GetMapping("/list")
	public Result<Page<DictItem>> list(Page<DictItem> page, DictItemCondition condition) {
		page = dictItemService.listByPage(page, condition.queryWrapper());
		return Result.success(page);
	}

}