package com.hu4java.system.controller;

import com.hu4java.base.request.RemoveRequest;
import com.hu4java.base.request.ViewRequest;
import com.hu4java.common.result.Result;
import com.hu4java.system.condition.DeptCondition;
import com.hu4java.system.entity.Dept;
import com.hu4java.system.request.DeptListRequest;
import com.hu4java.system.request.DeptRequest;
import com.hu4java.system.request.DeptUpdateRequest;
import com.hu4java.system.response.DeptTreeTableResponse;
import com.hu4java.system.service.DeptService;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理-部门
 * 部门管理
 * @author hu4java
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
    public Result<Void> save(@RequestBody @Validated DeptRequest request) {
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
    public Result<Void> remove(@RequestBody @Validated RemoveRequest request) {
        List<Dept> deptList = deptService.listByPid(request.getId());
        if (deptList.size() > 0) {
            return Result.error("请先删除下级部门");
        }
        deptService.removeById(request.getId());
        return Result.success();
    }
}
