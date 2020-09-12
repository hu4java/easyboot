package com.hu4java.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.system.entity.OperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志
 * @author	EasyBoot
 * @date	2020-09-12 18:11:57
 */
@Getter
@Setter
public class OperationLogCondition extends Condition {
	private static final long serialVersionUID = -1L;

	/** 业务类型*/
	private Integer type;

	/** 描述*/
	private String description;

	/** IP*/
	private String ip;

	/** 参数*/
	private String parameter;

	/** 返回数据*/
	private String returnBody;

	/** 状态: 0-成功 1-异常*/
	private Integer status;

	/** 错误信息*/
	private String errorMsg;

	/** 位置*/
	private String location;

	/** 方法*/
	private String method;

	/** 请求方法*/
	private String requestMethod;

	@Override
	public LambdaQueryWrapper<OperationLog> queryWrapper() {
		return null;
	}

}