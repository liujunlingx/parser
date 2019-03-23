package com.company.annotation;

import java.lang.annotation.*;

/**
 * Created on 2018/9/25.
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyWord {

    String[] value() default "";
}
