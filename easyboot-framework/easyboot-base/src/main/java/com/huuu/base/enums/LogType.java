package com.huuu.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志类型
 * @author huuu
 */
@Getter
@AllArgsConstructor
public enum LogType {

    NONE(0, "无"),
    SAVE(1, "保存"),
    UPDATE(2, "更新"),
    REMOVE(3, "删除");

    private int type;

    private String desc;
}
