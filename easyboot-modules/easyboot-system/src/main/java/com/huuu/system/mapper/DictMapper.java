package com.huuu.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.mapper.BaseMapper;
import com.huuu.system.entity.Dict;
import org.springframework.stereotype.Repository;

/**
 * 字典表
 * @author	EasyBoot
 * @date	2020-09-05 22:35:15
 */
@Repository
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据类型查询
     * @param type  类型
     * @return
     */
    default Dict selectByType(String type) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dict::getType, type);
        return selectOne(queryWrapper);
    }
}