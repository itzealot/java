package com.sky.projects.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 注解 @EnableAutoConfiguration 申明让 spring boot 自动给程序进行必要的配置；
 * 
 * 注解 @Controller 表示这个一个 Controller 类；
 * 
 * @author a
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}