package com.huuu.system.service.impl;

import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.system.service.DictItemService;
import com.huuu.system.entity.DictItem;
import com.huuu.system.mapper.DictItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
@Service
public class DictItemServiceImpl extends AbstractServiceImpl<DictItem, DictItemMapper> implements DictItemService {

    @Override
    public DictItem getByTitleAndDictType(String title, String dictType) {
        return mapper.selectByTitleAndDictType(title, dictType);
    }

    @Override
    public List<DictItem> listByDictType(String dictType) {
        return mapper.selectByDictType(dictType);
    }
}