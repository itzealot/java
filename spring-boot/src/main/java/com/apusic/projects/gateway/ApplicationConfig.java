package com.apusic.projects.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apusic.projects.gateway.entity.User;

/**
 * 注解 @Configuration 通过java代码来装配bean的方案：
 * 
 * @author a
 *
 */
@Configuration
public class ApplicationConfig {
	@Bean(name = "user")
	public User getUserBean() {
		return new User(1L, "zhangsan");
	}
}