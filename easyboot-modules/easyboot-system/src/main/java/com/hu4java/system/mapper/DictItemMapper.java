package com.hu4java.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.entity.DictItem;
import org.springframework.stereotype.Repository;

/**
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
@Repository
public interface DictItemMapper extends BaseMapper<DictItem> {

    /**
     * 根据标题和字典类型查询
     * @param title     标题
     * @param dictType  字典类型
     * @return
     */
    default DictItem selectByTitleAndDictType(String title, String dictType) {
        LambdaQueryWrapper<DictItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DictItem::getTitle, title);
        queryWrapper.eq(DictItem::getDictType, dictType);
        return selectOne(queryWrapper);
    }
}