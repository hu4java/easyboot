package com.huuu.base.annotation;

import com.huuu.base.enums.LogType;

import java.lang.annotation.*;

/**
 * @author huuu
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String desc() default "";

    LogType type() default LogType.NONE;
}
