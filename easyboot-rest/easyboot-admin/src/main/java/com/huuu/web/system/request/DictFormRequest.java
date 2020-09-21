package com.huuu.web.system.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典表单
 * @author huuu
 */
@Getter
@Setter
public class DictFormRequest extends BaseRequest {
    private static final long serialVersionUID = -4106691261915414473L;

    /** 字典名称*/
    @NotBlank(message = "字典名称不能为空")
    private String name;
    /** 字典类型*/
    @NotBlank(message = "字典类型不能为空")
    private String type;
    /** 状态：0-正常 1-禁用*/
    @NotNull(message = "状态不能为空")
    private Integer status;
    /** 备注*/
    private String remark;
}
