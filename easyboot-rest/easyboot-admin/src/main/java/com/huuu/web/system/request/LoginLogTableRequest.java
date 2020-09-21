package com.huuu.web.system.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huuu.base.request.PageRequest;
import com.huuu.system.entity.LoginLog;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class LoginLogTableRequest extends PageRequest<LoginLog> {
    private static final long serialVersionUID = 3764292973654329007L;

    private String account;

    private String location;

    private Integer status;

    private LocalDate startTime;

    private LocalDate endTime;

    @Override
    public LambdaQueryWrapper<LoginLog> queryWrapper() {
        LambdaQueryWrapper<LoginLog> queryWrapper = Wrappers.lambdaQuery(LoginLog.class);
        queryWrapper.orderByDesc(LoginLog::getId);

        if (StringUtils.isNotBlank(account)) {
            queryWrapper.like(LoginLog::getAccount, account);
        }

        if (StringUtils.isNotBlank(location)) {
            queryWrapper.like(LoginLog::getLocation, location);
        }

        if (null != status) {
            queryWrapper.eq(LoginLog::getStatus, status);
        }

        if (null != startTime && null != endTime) {
            queryWrapper.between(LoginLog::getLoginTime, startTime, endTime);
        }

        return queryWrapper;
    }
}
