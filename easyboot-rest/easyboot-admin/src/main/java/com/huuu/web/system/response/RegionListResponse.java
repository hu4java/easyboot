package com.huuu.web.system.response;

import com.huuu.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class RegionListResponse extends BaseResponse {

    private Long id;

    /** 名称*/
    private String name;

    /** 简称*/
    private String shortName;

    /** 代码*/
    private String code;

    /** 父级 ID*/
    private Long pid;

    /** 等级:0-国家 1-省 2-市 3-区/县*/
    private Integer level;

    /** 首字符*/
    private String firstLetter;

    /** 合称*/
    private String mergerName;
}
