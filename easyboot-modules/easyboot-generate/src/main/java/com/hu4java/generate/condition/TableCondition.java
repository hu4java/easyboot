package com.hu4java.generate.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.generate.entity.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hu4java
 */
@Getter
@Setter
public class TableCondition extends Condition<Table> {

    private String name;

    @Override
    public LambdaQueryWrapper<Table> queryWrapper() {
        return null;
    }
}
