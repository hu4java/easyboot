package com.huuu.web.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.base.annotation.Log;
import com.huuu.base.enums.LogType;
import com.huuu.base.request.PageRequest;
import com.huuu.base.request.RemoveRequest;
import com.huuu.base.request.ViewRequest;
import com.huuu.base.result.Result;
import com.huuu.system.condition.RegionCondition;
import com.huuu.system.entity.Region;
import com.huuu.system.service.RegionService;
import com.huuu.web.system.request.RegionFormRequest;
import com.huuu.web.system.request.RegionUpdateRequest;
import com.huuu.web.system.response.RegionSelectResponse;
import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 区域表
 * @author	EasyBoot
 * @date	2020-09-17 11:20:38
 */
@RestController
@RequestMapping("/region")
public class RegionController {

	@Autowired
	private RegionService regionService;
	@Autowired
	private MapperFacade mapperFacade;

	@GetMapping("/list")
	@RequiresPermissions("sys:region:view")
	public Result<Page<Region>> list(PageRequest<Region> request, RegionCondition condition) {
		Page<Region> page = regionService.listByPage(request.toPage(), condition.queryWrapper());
		return Result.success(page);
	}

	@GetMapping("/selectList")
	public Result<List<RegionSelectResponse>> selectList() {

		List<Region> list = regionService.listByTree();
		List<RegionSelectResponse> responseList = mapperFacade.mapAsList(list, RegionSelectResponse.class);
		return Result.success(responseList);
	}

	@GetMapping("/detail")
	@RequiresPermissions("sys:region:view")
	public Result<RegionUpdateRequest> detail(@Validated ViewRequest request) {
		Region region = regionService.getById(request.getId());
		RegionUpdateRequest response = mapperFacade.map(region, RegionUpdateRequest.class);
		return Result.success(response);
	}

	@PostMapping("/save")
	@Log(desc = "保存区域", type = LogType.SAVE)
	@RequiresPermissions("sys:region:save")
	public Result<Void> save(@RequestBody @Validated RegionFormRequest request) {
		Region region = mapperFacade.map(request, Region.class);
		regionService.save(region);
		return Result.success();
	}

	@PostMapping("/update")
	@Log(desc = "更新区域", type = LogType.UPDATE)
	@RequiresPermissions("sys:region:update")
	public Result<Void> update(@RequestBody @Validated RegionUpdateRequest request) {
		Region region = mapperFacade.map(request, Region.class);
		regionService.update(region);
		return Result.success();
	}

	@PostMapping("/remove")
	@Log(desc = "删除区域", type = LogType.REMOVE)
	@RequiresPermissions("sys:region:remove")
	public Result<Void> remove(@RequestBody @Validated RemoveRequest request) {
		regionService.removeById(request.getId());
		return Result.success();
	}
}