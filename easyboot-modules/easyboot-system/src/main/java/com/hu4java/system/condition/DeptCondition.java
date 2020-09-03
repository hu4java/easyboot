package com.hu4java.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.system.entity.Dept;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class DeptCondition extends Condition<Dept> {

    /** 部门名称*/
    private String name;
    /** 状态：0-正常 1-禁用*/
    private Integer status;

    @Override
    public LambdaQueryWrapper<Dept> queryWrapper() {
        return null;
    }
}
