package com.huuu.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.condition.Condition;
import com.huuu.system.entity.Menu;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class MenuCondition extends Condition<Menu> {

    private Long pid;

    private String title;

    private Integer status;

    @Override
    public LambdaQueryWrapper<Menu> queryWrapper() {
        return null;
    }
}
