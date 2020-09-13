package com.hu4java.base.annotation;

import com.hu4java.base.enums.LogType;

import java.lang.annotation.*;

/**
 * @author hu4java
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String desc() default "";

    LogType type() default LogType.NONE;
}
