package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.entity.DictItem;

import java.util.List;

/**
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
public interface DictItemService extends Service<DictItem> {

    /**
     * 根据标题和类型查询
     * @param title 标题
     * @param dictType 类型
     * @return
     */
    DictItem getByTitleAndDictType(String title, String dictType);

    /**
     * 根据类型查询
     * @param dictType  类型
     * @return
     */
    List<DictItem> listByDictType(String dictType);
}