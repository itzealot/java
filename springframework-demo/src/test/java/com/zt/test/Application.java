package com.zt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
	private static ApplicationContext context = null;

	/**
	 * 使用注解 @Bean 来将创建的Bean交给 Spring 容器管理
	 * 
	 * @return
	 */
	@Bean
	public MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}

	public static void main(String[] args) {
		// 通过 AnnotationConfigApplicationContext 对象来获取某个类下使用注解配置的Bean
		context = new AnnotationConfigApplicationContext(Application.class);

		// 获取 Bean
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}
}