package com.huuu.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenzhenhu
 */
@Getter
@AllArgsConstructor
public enum MenuType {

    CATALOG(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private int type;

    private String desc;
}
