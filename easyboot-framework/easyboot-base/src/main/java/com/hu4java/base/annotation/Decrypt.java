package com.hu4java.base.annotation;

import java.lang.annotation.*;

/**
 * @author chenzhenhu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {
}
