package com.hu4java.system.service;

import com.hu4java.base.service.Service;
import com.hu4java.system.entity.DictItem;
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
}