package com.huuu.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huuu.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 区域表
 * @author	EasyBoot
 * @date	2020-09-17 11:20:38
 */
@Getter
@Setter
@TableName("sys_region")
public class Region extends BaseEntity {
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

	@TableField(exist = false)
	private List<Region> children;

}