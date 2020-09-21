package com.huuu.web.system.controller;

import com.huuu.base.annotation.Log;
import com.huuu.base.enums.LogType;
import com.huuu.base.request.RemoveRequest;
import com.huuu.base.request.ViewRequest;
import com.huuu.base.result.Result;
import com.huuu.system.condition.DeptCondition;
import com.huuu.system.entity.Dept;
import com.huuu.system.service.DeptService;
import com.huuu.web.system.request.DeptFormRequest;
import com.huuu.web.system.request.DeptListRequest;
import com.huuu.web.system.request.DeptUpdateRequest;
import com.huuu.web.system.response.DeptTreeTableResponse;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理-部门
 * 部门管理
 * @author huuu
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private MapperFacade mapperFacade;

    /**
     * 部门列表
     * @param request   查询参数
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dept:view")
    public Result<List<DeptTreeTableResponse>> list(DeptListRequest request) {
        List<Dept> deptList;
        if (StringUtils.isNotBlank(request.getName())) {
            DeptCondition condition = mapperFacade.map(request, DeptCondition.class);
            deptList = deptService.listByCondition(condition);
        } else {
            deptList = deptService.listByPid(0L);
        }
        List<DeptTreeTableResponse> list = mapperFacade.mapAsList(deptList, DeptTreeTableResponse.class);
        return Result.success(list);
    }

    /**
     * 部门详细
     * @param request   查询参数
     * @return
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:dept:view")
    public Result<DeptUpdateRequest> detail(@Validated ViewRequest request) {
        Dept dept = deptService.getById(request.getId());
        if (null == dept) {
            return Result.error("部门数据不存在");
        }
        DeptUpdateRequest response = mapperFacade.map(dept, DeptUpdateRequest.class);
        return Result.success(response);
    }

    /**
     * 保存部门
     * @param request   保存参数
     * @return
     */
    @PostMapping("/save")
    @Log(desc = "保存部门", type = LogType.SAVE)
    @RequiresPermissions("sys:dept:save")
    public Result<Void> save(@RequestBody @Validated DeptFormRequest request) {
        Dept dept = mapperFacade.map(request, Dept.class);
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 更新部门
     * @param request   更新参数
     * @return
     */
    @PostMapping("/update")
    @Log(desc = "更新部门", type = LogType.UPDATE)
    @RequiresPermissions("sys:dept:update")
    public Result<Void> update(@RequestBody @Validated DeptUpdateRequest request) {
        Dept dept = mapperFacade.map(request, Dept.class);
        deptService.update(dept);
        return Result.success();
    }

    /**
     * 删除部门
     * @param request   删除参数
     * @return
     */
    @PostMapping("/remove")
    @Log(desc = "删除部门", type = LogType.REMOVE)
    @RequiresPermissions("sys:dept:remove")
    public Result<Dept> remove(@RequestBody @Validated RemoveRequest request) {
        List<Dept> deptList = deptService.listByPid(request.getId());
        if (deptList.size() > 0) {
            return Result.error("请先删除下级部门");
        }
        Dept dept = deptService.getById(request.getId());
        deptService.removeById(request.getId());
        return Result.success(dept);
    }
}
