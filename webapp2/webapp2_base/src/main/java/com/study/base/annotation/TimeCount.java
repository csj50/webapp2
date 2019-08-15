package com.study.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.study.base.annotation 时间统计注解
 * 在需要时间统计的方法上添加该注解，并提供时间统计handler类
 * 默认时间统计handler类为DefaultTimeCountHandler
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TimeCount {
	String value() default "com.study.base.component.DefaultTimeCountHandler";
}
