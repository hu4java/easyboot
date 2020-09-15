package com.hu4java.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.annotation.Log;
import com.hu4java.base.enums.LogType;
import com.hu4java.base.request.RemoveRequest;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.base.result.Result;
import com.hu4java.system.entity.DictItem;
import com.hu4java.system.service.DictItemService;
import com.hu4java.web.system.request.DictItemFormRequest;
import com.hu4java.web.system.request.DictItemQueryRequest;
import com.hu4java.web.system.request.DictItemTableRequest;
import com.hu4java.web.system.request.DictItemUpdateRequest;
import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理-数据字典数据
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
@RestController
@RequestMapping("/dict/item")
public class DictItemController {

	@Autowired
	private DictItemService dictItemService;
	@Autowired
	private MapperFacade mapperFacade;

	/**
	 * 数据列表
	 * @param request	参数
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:dictItem:view")
	public Result<Page<DictItem>> list(DictItemTableRequest request) {
		Page<DictItem> page = dictItemService.listByPage(request.toPage(), request.queryWrapper());
		return Result.success(page);
	}

	/**
	 * 字典数据列表
	 * @param request	参数
	 * @return
	 */
	@GetMapping("/listByDictType")
	public Result<List<DictItem>> listByDictType(DictItemQueryRequest request) {
		List<DictItem> list = dictItemService.listByDictType(request.getDictType());
		return Result.success(list);
	}

	/**
	 * 数据详细
	 * @param request	参数
	 * @return
	 */
	@GetMapping("/detail")
	@RequiresPermissions("sys:dictItem:view")
	public Result<DictItem> detail(@Validated ViewRequest request) {
		DictItem dictItem = dictItemService.getById(request.getId());
		return Result.success(dictItem);
	}

	/**
	 * 保存数据
	 * @param request	参数
	 * @return
	 */
	@PostMapping("/save")
	@Log(desc = "保存数据", type = LogType.SAVE)
	@RequiresPermissions("sys:dictItem:save")
	public Result<Void> save(@RequestBody @Validated DictItemFormRequest request) {
		DictItem exist = dictItemService.getByTitleAndDictType(request.getTitle(), request.getDictType());
		if (null != exist) {
			return Result.error("数据标题已存在");
		}
		DictItem dictItem = mapperFacade.map(request, DictItem.class);
		dictItemService.save(dictItem);
		return Result.success();
	}


	/**
	 * 更新数据
	 * @param request 参数
	 * @return
	 */
	@PostMapping("/update")
	@Log(desc = "更新数据", type = LogType.UPDATE)
	@RequiresPermissions("sys:dictItem:update")
	public Result<Void> update(@RequestBody @Validated DictItemUpdateRequest request) {
		DictItem exist = dictItemService.getByTitleAndDictType(request.getTitle(), request.getDictType());
		if (null != exist && !exist.getId().equals(request.getId())) {
			return Result.error("数据标题已存在");
		}
		DictItem dictItem = mapperFacade.map(request, DictItem.class);
		dictItemService.update(dictItem);
		return Result.success();
	}

	/**
	 * 删除数据
	 * @param request	参数
	 * @return
	 */
	@PostMapping("/remove")
	@Log(desc = "删除数据", type = LogType.REMOVE)
	@RequiresPermissions("sys:dictItem:remove")
	public Result<DictItem> remove(@RequestBody @Validated RemoveRequest request) {
		DictItem dictItem = dictItemService.getById(request.getId());
		if (null != dictItem) {
			dictItemService.removeById(request.getId());
		}
		return Result.success(dictItem);
	}

}