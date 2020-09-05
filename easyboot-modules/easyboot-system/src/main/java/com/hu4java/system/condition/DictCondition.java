package com.hu4java.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.system.entity.Dict;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
@Getter
@Setter
public class DictCondition extends Condition {
	private static final long serialVersionUID = -1L;

	/** 字典名称*/
	private String name;

	/** 类型*/
	private String type;

	/** 状态：0-正常 1-禁用*/
	private Integer status;

	/** 备注*/
	private String remark;

	@Override
	public LambdaQueryWrapper<Dict> queryWrapper() {
		return null;
	}

}