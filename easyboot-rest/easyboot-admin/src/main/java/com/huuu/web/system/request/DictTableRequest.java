package com.huuu.web.system.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.request.PageRequest;
import com.huuu.system.entity.Dict;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 字典表格请求参数
 * @author chenzhenhu
 */
@Getter
@Setter
public class DictTableRequest extends PageRequest<Dict> {
    private static final long serialVersionUID = -7596164580245020349L;

    /** 字典名称*/
    private String name;
    /** 类型*/
    private String type;
    /** 状态：0-正常 1-禁用*/
    private Integer status;

    @Override
    public LambdaQueryWrapper<Dict> queryWrapper() {
        LambdaQueryWrapper<Dict> queryWrapper = super.queryWrapper();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Dict::getName, name);
        }
        if (StringUtils.isNotBlank(type)) {
            queryWrapper.like(Dict::getType, type);
        }
        if (null != status) {
            queryWrapper.eq(Dict::getStatus, status);
        }
        return queryWrapper;
    }
}
