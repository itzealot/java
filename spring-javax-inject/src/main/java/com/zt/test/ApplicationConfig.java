package com.zt.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zt.test.entity.Person;

/**
 * 注解 @Configuration 通过java代码来装配bean的方案：
 * 
 * @author a
 *
 */
@Configuration
public class ApplicationConfig {
	/**
	 * 创建 Person 对象并交由 Spring 容器管理
	 * 
	 * @return
	 */
	@Bean(name = "p")
	public Person get() {
		return new Person("zhangsan");
	}
}