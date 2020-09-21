package com.huuu.base.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;

/**
 * @author huuu
 */
public abstract class Condition<T>  implements Serializable {


    public abstract LambdaQueryWrapper<T> queryWrapper();

}
