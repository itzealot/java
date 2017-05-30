package com.sky.projects.jdk.pack.info.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1. 首先定义一个包类型的注解，它只能放置的一个包上<br />
 * 2. 是一个标注在包上的注解定义<br />
 * 
 * @author a
 *
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PkgAnnotation {

	String version() default "";
}