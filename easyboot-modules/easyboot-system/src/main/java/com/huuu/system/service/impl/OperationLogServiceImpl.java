package com.huuu.system.service.impl;

import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.system.service.OperationLogService;
import com.huuu.system.entity.OperationLog;
import com.huuu.system.mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 * @author	EasyBoot
 * @date	2020-09-12 18:11:57
 */
@Service
public class OperationLogServiceImpl extends AbstractServiceImpl<OperationLog, OperationLogMapper> implements OperationLogService {

}