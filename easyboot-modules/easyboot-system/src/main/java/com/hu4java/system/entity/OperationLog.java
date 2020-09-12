package com.hu4java.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 操作日志
 * @author	EasyBoot
 * @date	2020-09-12 18:11:57
 */
@Getter
@Setter
@TableName("sys_operation_log")
public class OperationLog extends BaseEntity {
	private static final long serialVersionUID = -1L;

	private LocalDateTime operateTime;

	private Long operateUserId;

	private String operateUser;

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

}