package com.hu4java.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
@Getter
@Setter
@TableName("sys_dict_item")
public class DictItem extends BaseEntity {
	private static final long serialVersionUID = -1L;

	/** 标题*/
	private String title;

	/** 值*/
	private String value;

	/** 状态：0-正常 1-禁用*/
	private Integer status;

	/** 排序*/
	private Integer orderNum;

	/** 备注*/
	private String remark;

	/** 关联字典类型*/
	private String dictType;

}