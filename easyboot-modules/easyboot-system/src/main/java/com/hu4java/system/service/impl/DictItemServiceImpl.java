package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.service.DictItemService;
import com.hu4java.system.entity.DictItem;
import com.hu4java.system.mapper.DictItemMapper;
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