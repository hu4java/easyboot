package com.hu4java.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.system.entity.Menu;
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
