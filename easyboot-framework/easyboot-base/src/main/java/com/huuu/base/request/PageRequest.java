package com.huuu.base.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页数据请求
 * @author huuu
 */
@Getter
@Setter
public class PageRequest<T> extends BaseRequest {
    private static final long serialVersionUID = 7773624120567087405L;

    /** 每页条数*/
    private long size = 10;
    /** 当前页码*/
    private long current = 1;

    public Page<T> toPage() {
        Page<T> page = new Page<>(current, size);
        return page;
    }

    public LambdaQueryWrapper<T> queryWrapper() {
        return Wrappers.lambdaQuery();
    }
}
