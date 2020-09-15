package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.service.LoginLogService;
import com.hu4java.system.entity.LoginLog;
import com.hu4java.system.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

/**
 * 登录日志
 * @author	EasyBoot
 * @date	2020-09-15 11:16:28
 */
@Service
public class LoginLogServiceImpl extends AbstractServiceImpl<LoginLog, LoginLogMapper> implements LoginLogService {

}