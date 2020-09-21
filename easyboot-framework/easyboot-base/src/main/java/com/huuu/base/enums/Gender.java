package com.huuu.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 * @author chenzhenhu
 */
@Getter
@AllArgsConstructor
public enum Gender {

    UNKNOWN(0, "未知"),

    MALE(1, "男"),

    FEMALE(2, "女");

    private final int gender;

    private final String desc;
}
