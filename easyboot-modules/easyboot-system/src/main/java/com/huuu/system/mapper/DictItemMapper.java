package com.huuu.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.mapper.BaseMapper;
import com.huuu.system.entity.DictItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典数据表
 * @author	EasyBoot
 * @date	2020-09-05 22:40:23
 */
@Repository
public interface DictItemMapper extends BaseMapper<DictItem> {


    /**
     * 更新字典类型
     * @param oldDictType   原类型
     * @param newDictType   新类型
     * @return              影响行数
     */
    int updateDictTypeByOldDictType(@Param("oldDictType") String oldDictType,@Param("newDictType") String newDictType);

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

    /**
     * 根据类型查询
     * @param dictType  类型
     * @return
     */
    default List<DictItem> selectByDictType(String dictType) {
        LambdaQueryWrapper<DictItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DictItem::getDictType, dictType);
        queryWrapper.orderByAsc(DictItem::getOrderNum);
        return selectList(queryWrapper);
    }
}