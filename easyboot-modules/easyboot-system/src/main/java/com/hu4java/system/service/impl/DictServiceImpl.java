package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.mapper.DictItemMapper;
import com.hu4java.system.service.DictService;
import com.hu4java.system.entity.Dict;
import com.hu4java.system.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
@Service
public class DictServiceImpl extends AbstractServiceImpl<Dict, DictMapper> implements DictService {

    @Autowired
    private DictItemMapper dictItemMapper;

    @Override
    public Dict getByType(String type) {
        return mapper.selectByType(type);
    }

    @Override
    public boolean update(Dict entity) {
        Dict exist = getById(entity.getId());
        // 是否更新关联数据项类型
        if (!exist.getType().equals(entity.getType())) {
            dictItemMapper.updateDictTypeByOldDictType(exist.getType(), entity.getType());
        }
        return super.update(entity);
    }
}