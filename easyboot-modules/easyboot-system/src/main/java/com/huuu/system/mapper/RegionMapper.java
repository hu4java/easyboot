package com.huuu.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.mapper.BaseMapper;
import com.huuu.system.entity.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 区域表
 * @author	EasyBoot
 * @date	2020-09-17 11:20:38
 */
@Repository
public interface RegionMapper extends BaseMapper<Region> {

    /**
     * 根据上级ID查询
     * @param pid   上级id
     * @return
     */
    default List<Region> selectByPid(Long pid) {
        LambdaQueryWrapper<Region> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Region::getPid, pid);
        return selectList(queryWrapper);
    }

    /**
     * 根据上级ID查询
     * @param level   等级
     * @return
     */
    default List<Region> selectByLevel(Integer level) {
        LambdaQueryWrapper<Region> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Region::getLevel, level);
        return selectList(queryWrapper);
    }

}