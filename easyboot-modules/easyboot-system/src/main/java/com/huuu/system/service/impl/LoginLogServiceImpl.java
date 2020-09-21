package com.huuu.system.service.impl;

import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.system.service.LoginLogService;
import com.huuu.system.entity.LoginLog;
import com.huuu.system.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

/**
 * 登录日志
 * @author	EasyBoot
 * @date	2020-09-15 11:16:28
 */
@Service
public class LoginLogServiceImpl extends AbstractServiceImpl<LoginLog, LoginLogMapper> implements LoginLogService {

}