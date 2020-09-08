package com.hu4java.system.service;

import com.hu4java.base.service.Service;
import com.hu4java.system.entity.Dict;
/**
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
public interface DictService extends Service<Dict> {

    /**
     * 根据type 查询
     * @param type  类型
     * @return
     */
    Dict getByType(String type);
}