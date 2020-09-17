package com.hu4java.web.system.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 数据表单
 * @author hu4java
 */
@Getter
@Setter
public class DictItemFormRequest extends BaseRequest {
    private static final long serialVersionUID = -4106691261915414473L;

    /** 数据标题*/
    @NotBlank(message = "数据标题不能为空")
    private String title;
    /** 数据值*/
    @NotBlank(message = "数据值不能为空")
    private String value;
    /** 数据类型*/
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
    /** 状态：0-正常 1-禁用*/
    @NotNull(message = "状态不能为空")
    private Integer status;
    /** 排序*/
    @NotNull(message = "排序不能为空")
    private Integer orderNum;
    /** 备注*/
    private String remark;
}
