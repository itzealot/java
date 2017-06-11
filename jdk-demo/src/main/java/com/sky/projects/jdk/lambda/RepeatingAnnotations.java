package com.sky.projects.jdk.lambda;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重复注解，相同的注解可以在同一地方声明多次。
 * 
 * @author zealot
 *
 */
public class RepeatingAnnotations {

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Filters {
		Filter[] value();
	}

	/**
	 * 重复注解机制本身必须用 @Repeatable 注解
	 */
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Repeatable(Filters.class)
	public @interface Filter {
		String value();
	}

	@Filter("filter1")
	@Filter("filter2")
	public interface Filterable {
	}

}