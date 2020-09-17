package com.hu4java.base.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;

/**
 * @author hu4java
 */
public abstract class Condition<T>  implements Serializable {


    public abstract LambdaQueryWrapper<T> queryWrapper();

}
