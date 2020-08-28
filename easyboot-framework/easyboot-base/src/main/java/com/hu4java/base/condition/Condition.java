package com.hu4java.base.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * @author hu4java
 */
public abstract class Condition<T> {


    public abstract LambdaQueryWrapper<T> queryWrapper();

}
