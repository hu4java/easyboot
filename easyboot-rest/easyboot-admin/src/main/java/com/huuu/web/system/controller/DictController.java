package com.huuu.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.base.annotation.Log;
import com.huuu.base.enums.LogType;
import com.huuu.base.enums.Status;
import com.huuu.base.request.RemoveRequest;
import com.huuu.base.request.ViewRequest;
import com.huuu.base.result.Result;
import com.huuu.system.entity.Dict;
import com.huuu.system.service.DictService;
import com.huuu.web.system.request.DictFormRequest;
import com.huuu.web.system.request.DictTableRequest;
import com.huuu.web.system.request.DictUpdateRequest;
import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
	@Autowired
	private MapperFacade mapperFacade;

	/**
	 * 字典列表
	 * @param request	参数
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:dict:view")
	public Result<Page<Dict>> list(DictTableRequest request) {
		Page<Dict> page = dictService.listByPage(request.toPage(), request.queryWrapper());
		return Result.success(page);
	}

	/**
	 * 下拉框列表
	 * @return
	 */
	@GetMapping("/selectList")
	public Result<List<Dict>> selectList() {
		List<Dict> list = dictService.listAll();
		list = list.stream().filter(dict -> dict.getStatus().equals(Status.ENABLE.getStatus())).collect(Collectors.toList());
		return Result.success(list);
	}

	/**
	 * 字典详细
	 * @param request	参数
	 * @return
	 */
	@GetMapping("/detail")
	@RequiresPermissions("sys:dict:view")
	public Result<Dict> detail(@Validated ViewRequest request) {
		Dict dict = dictService.getById(request.getId());
		return Result.success(dict);
	}

	/**
	 * 保存字典
	 * @param request	参数
	 * @return
	 */
	@PostMapping("/save")
	@Log(desc = "保存字典", type = LogType.SAVE)
	@RequiresPermissions("sys:dict:save")
	public Result<Void> save(@RequestBody @Validated DictFormRequest request) {
		Dict exist = dictService.getByType(request.getType());
		if (null != exist) {
			return Result.error("字典类型已存在");
		}
		Dict dict = mapperFacade.map(request, Dict.class);
		dictService.save(dict);
		return Result.success();
	}


	/**
	 * 更新字典
	 * @param request 参数
	 * @return
	 */
	@PostMapping("/update")
	@Log(desc = "更新字典", type = LogType.UPDATE)
	@RequiresPermissions("sys:dict:update")
	public Result<Void> update(@RequestBody @Validated DictUpdateRequest request) {
		Dict exist = dictService.getByType(request.getType());
		if (null != exist && !exist.getId().equals(request.getId())) {
			return Result.error("字典类型已存在");
		}
		Dict dict = mapperFacade.map(request, Dict.class);
		dictService.update(dict);
		return Result.success();
	}

	/**
	 * 删除字典
	 * @param request	参数
	 * @return
	 */
	@PostMapping("/remove")
	@Log(desc = "删除字典", type = LogType.REMOVE)
	@RequiresPermissions("sys:dict:remove")
	public Result<Dict> remove(@RequestBody @Validated RemoveRequest request) {
		Dict dict = dictService.getById(request.getId());
		if (null != dict) {
			dictService.removeById(request.getId());
		}
		return Result.success(dict);
	}
}