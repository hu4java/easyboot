package com.hu4java.web.system.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.request.PageRequest;
import com.hu4java.system.entity.DictItem;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 字典数据表格请求参数
 * @author chenzhenhu
 */
@Getter
@Setter
public class DictItemTableRequest extends PageRequest<DictItem> {
    private static final long serialVersionUID = -4800572533774767401L;

    /** 字典名称*/
    private String title;
    /** 字典类型*/
    private String dictType;
    /** 状态：0-正常 1-禁用*/
    private Integer status;

    @Override
    public LambdaQueryWrapper<DictItem> queryWrapper() {
        LambdaQueryWrapper<DictItem> queryWrapper = super.queryWrapper();
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like(DictItem::getTitle, title);
        }
        if (StringUtils.isNotBlank(dictType)) {
            queryWrapper.eq(DictItem::getDictType, dictType);
        }
        if (null != status) {
            queryWrapper.eq(DictItem::getStatus, status);
        }
        return queryWrapper;
    }
}
