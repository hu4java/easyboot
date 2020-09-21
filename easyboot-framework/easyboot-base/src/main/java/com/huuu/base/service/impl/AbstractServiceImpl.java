package com.huuu.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.huuu.base.mapper.BaseMapper;
import com.huuu.base.service.Service;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * 公共业务实现
 * @author chenzhenhu
 */
public abstract class AbstractServiceImpl<T, M extends BaseMapper<T>> implements Service<T> {
    protected Log log = LogFactory.getLog(getClass());
    private static final int DEFAULT_BATCH_SIZE = 1000;

    @Autowired
    protected M mapper;

    protected Class<?> entityClass = currentEntityClass();

    protected Class<?> mapperClass = currentMapperClass();

    @Override
    public boolean save(T entity) {
        return returnBool(mapper.insert(entity));
    }

    @Override
    public boolean saveBatch(List<T> entityList) {
        if (null == entityList || entityList.size() == 0) {
            return false;
        }
        String sqlStatement = SqlHelper.getSqlStatement(mapperClass, SqlMethod.INSERT_ONE);
        return SqlHelper.executeBatch(this.entityClass, this.log, entityList, DEFAULT_BATCH_SIZE,
                (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    @Override
    public boolean update(T entity) {
        return returnBool(mapper.updateById(entity));
    }

    @Override
    public boolean removeById(Long id) {
        return returnBool(mapper.deleteById(id));
    }

    @Override
    public boolean removeByIds(Long[] ids) {
        if (null == ids || ids.length == 0) {
            return false;
        }
        return returnBool(mapper.deleteBatchIds(Arrays.asList(ids)));
    }

    @Override
    public T getById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> listAll() {
        return mapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public <E extends IPage<T>> E listByPage(E page, Wrapper<T> queryWrapper) {
        return mapper.selectPage(page, queryWrapper);
    }

    protected Class<T> currentEntityClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    protected Class<T> currentMapperClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    protected boolean returnBool(int count) {
        return count > 0;
    }
}
