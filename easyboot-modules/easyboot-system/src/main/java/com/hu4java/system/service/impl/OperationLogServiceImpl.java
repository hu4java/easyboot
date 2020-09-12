package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.service.OperationLogService;
import com.hu4java.system.entity.OperationLog;
import com.hu4java.system.mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 * @author	EasyBoot
 * @date	2020-09-12 18:11:57
 */
@Service
public class OperationLogServiceImpl extends AbstractServiceImpl<OperationLog, OperationLogMapper> implements OperationLogService {

}