package com.huuu.generate.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.condition.Condition;
import com.huuu.generate.entity.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author huuu
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
