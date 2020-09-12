package com.hu4java.common.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hu4java.base.entity.BaseEntity;
import com.hu4java.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共字段填充
 * @author chenzhenhu
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始填充插入字段");
        this.strictInsertFill(metaObject, BaseEntity.CREATE_BY_FIELD, Long.class, getLoginId());
        this.strictInsertFill(metaObject, BaseEntity.CREATE_TIME_FIELD, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, BaseEntity.DELETE_FIELD, Boolean.class, Boolean.FALSE);
        this.strictInsertFill(metaObject, BaseEntity.VERSION_FIELD, Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始填充更新字段");
        this.strictUpdateFill(metaObject, BaseEntity.UPDATE_BY_FIELD, Long.class, getLoginId());
        this.strictUpdateFill(metaObject, BaseEntity.UPDATE_TIME_FIELD, LocalDateTime.class, LocalDateTime.now());
    }


    private Long getLoginId() {
        Long loginId = 0L;
        if (ShiroUtils.isLogin()) {
            BaseEntity baseEntity = ShiroUtils.currentLogin();
            loginId = baseEntity.getId();
        }
        return loginId;
    }
}
