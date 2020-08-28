package com.hu4java.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hu4java
 */
@Getter
@AllArgsConstructor
public enum Status {

    ENABLE(0, "正常"),

    DISABLE(1, "禁用");

    private final int status;

    private final String desc;
}
