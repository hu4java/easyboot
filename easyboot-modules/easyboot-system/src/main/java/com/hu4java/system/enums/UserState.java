package com.hu4java.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 员工人事状态枚举
 * @author chenzhenhu
 */
@Getter
@AllArgsConstructor
public enum UserState {

    UNKNOWN(0, "未知"),
    INTERVIEW(1, "面试期"),
    TRAINING(2, "培训期"),
    PROBATION_PERIOD(3, "试用期"),
    PART_TIME(4, "兼职员工"),
    REGULAR_EMPLOYEE(5, "正式员工"),
    FAILED_TO_BECOME_A_REGULAR(6, "转正失败"),
    FURLOUGH(7, "停薪留职"),
    VOLUNTARY_RESIGNATION(8, "主动离职"),
    DISMISSED(9, "被解聘");

    private int state;

    private String desc;
}
