package com.hu4java.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.system.entity.Region;
import lombok.Getter;
import lombok.Setter;

/**
 * 区域表
 * @author	EasyBoot
 * @date	2020-09-17 11:20:38
 */
@Getter
@Setter
public class RegionCondition extends Condition<Region> {
	private static final long serialVersionUID = -1L;

	/** 名称*/
	private String name;

	/** 简称*/
	private String shortName;

	/** 全称*/
	private String fullName;

	/** 代码*/
	private String code;

	/** 父级 ID*/
	private Long pid;

	/** 名称拼音*/
	private String pinyin;

	/** 等级:0-国家 1-省 2-市 3-区/县*/
	private Integer level;

	/** 邮编*/
	private String zipCode;

	/** 电话区号*/
	private String phoneCode;

	/** 首字符*/
	private String firstLetter;

	/** 经度*/
	private String longitude;

	/** 纬度*/
	private String latitude;

	/** 父级ID字符串*/
	private String pids;

	/** 合称*/
	private String mergerName;

	@Override
	public LambdaQueryWrapper<Region> queryWrapper() {
		return null;
	}

}