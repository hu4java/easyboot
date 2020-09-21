package com.huuu.system.service;

import com.huuu.base.service.Service;
import com.huuu.system.entity.Region;

import java.util.List;

/**
 * 区域表
 * @author	EasyBoot
 * @date	2020-09-17 11:20:38
 */
public interface RegionService extends Service<Region> {


    /**
     * 根据上级ID查询
     * @param pid   上级ID
     * @return
     */
    List<Region> listByPid(Long pid);

    /**
     * 树节点数据
     * @return
     */
    List<Region> listByTree();
}