package com.hu4java.web.system.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.request.PageRequest;
import com.hu4java.system.entity.OperationLog;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * @author hu4java
 */
@Getter
@Setter
public class OperationLogTableRequest extends PageRequest<OperationLog> {
    private static final long serialVersionUID = 1519626068378222575L;

    private String name;

    private Integer status;

    private Integer type;

    private LocalDate startTime;

    private LocalDate endTime;

    @Override
    public LambdaQueryWrapper<OperationLog> queryWrapper() {
        LambdaQueryWrapper<OperationLog> queryWrapper = super.queryWrapper();
        queryWrapper.orderByDesc(OperationLog::getId);

        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(OperationLog::getOperateUser, name);
        }

        if (null != status) {
            queryWrapper.eq(OperationLog::getStatus, status);
        }

        if (null != type) {
            queryWrapper.eq(OperationLog::getType, type);
        }

        if (null != startTime && null != endTime) {
            queryWrapper.between(OperationLog::getOperateTime, startTime, endTime);
        }

        return queryWrapper;
    }
}
