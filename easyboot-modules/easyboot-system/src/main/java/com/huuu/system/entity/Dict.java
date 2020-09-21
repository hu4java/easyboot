package com.huuu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huuu.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
@Getter
@Setter
@TableName("sys_dict")
public class Dict extends BaseEntity {
	private static final long serialVersionUID = -1L;

	/** 字典名称*/
	private String name;

	/** 类型*/
	private String type;

	/** 状态：0-正常 1-禁用*/
	private Integer status;

	/** 备注*/
	private String remark;

}