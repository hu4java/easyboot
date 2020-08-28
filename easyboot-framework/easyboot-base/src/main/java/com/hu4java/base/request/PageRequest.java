package com.hu4java.base.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页数据请求
 * @author hu4java
 */
@Getter
@Setter
public class PageRequest extends BaseRequest {
    private static final long serialVersionUID = 7773624120567087405L;

    /** 每页条数*/
    private long size = 10;
    /** 当前页码*/
    private long current = 1;
}
