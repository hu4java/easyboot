package com.huuu.web.system.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class RegionFormRequest extends BaseRequest {

    /** 名称*/
    @NotBlank(message = "名称不能为空")
    private String name;

    /** 简称*/
    private String shortName;

    /** 代码*/
    private String code;

    /** 父级 ID*/
    @NotNull(message = "上级ID不能为空")
    private Long pid;

    /** 等级:0-国家 1-省 2-市 3-区/县*/
    @NotNull(message = "等级不能为空")
    private Integer level;

    /** 首字符*/
    private String firstLetter;

    /** 合称*/
    private String mergerName;
}
