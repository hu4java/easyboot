package com.hu4java.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公共业务
 * @author chenzhenhu
 */
public interface Service<T> {

    /**
     * 保存
     * @param entity    对象
     * @return          是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean save(T entity);

    /**
     * 批量保存
     * @param entityList    对象列表
     * @return              是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean saveBatch(List<T> entityList);
    /**
     * 更新
     * @param entity    对象
     * @return          是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean update(T entity);

    /**
     * 根据ID删除
     * @param id    数据ID
     * @return      是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean removeById(Long id);

    /**
     * 根据ID数组删除
     * @param ids   数据ID数组
     * @return      是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean removeByIds(Long[] ids);

    /**
     * 根据ID查询
     * @param id    数据ID
     * @return      对象
     */
    T getById(Long id);

    /**
     * 查询所有数据
     * @return      数据对象列表
     */
    List<T> listAll();

    /**
     * 分页查询
     * @param page          分页请求
     * @param queryWrapper  查询条件
     * @return
     */
    <E extends IPage<T>> E listByPage(E page, Wrapper<T> queryWrapper);
}
