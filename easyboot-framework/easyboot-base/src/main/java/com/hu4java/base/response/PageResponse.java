package com.hu4java.base.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页数据响应
 * @author hu4java
 */
@Getter
@Setter
public class PageResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 6664101947941268741L;

    /** 总条数*/
    private long total;
    /** 数据*/
    private List<T> records;
    /** 每页条数*/
    private long size;
    /** 当前页码*/
    private long current;
}
