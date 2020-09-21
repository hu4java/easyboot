package com.huuu.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.condition.Condition;
import com.huuu.system.entity.LoginLog;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

/**
 * 登录日志
 * @author	EasyBoot
 * @date	2020-09-15 11:16:28
 */
@Getter
@Setter
public class LoginLogCondition extends Condition {
	private static final long serialVersionUID = -1L;

	/** 登录账号*/
	private String account;

	/** 访问IP*/
	private String ip;

	/** IP位置*/
	private String location;

	/** 登录状态：0-成功 1-失败*/
	private Integer status;

	/** 登录时间*/
	private LocalDateTime loginTime;

	/** 浏览器*/
	private String browser;

	/** 系统*/
	private String os;

	/** 错误信息*/
	private String errorMsg;

	@Override
	public LambdaQueryWrapper<LoginLog> queryWrapper() {
		return null;
	}

}