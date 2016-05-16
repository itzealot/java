package com.sky.projects.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sky.projects.spring.configuration.repository.OrderRepository;
import com.sky.projects.spring.configuration.service.OrderService;

/**
 * 好处：
 * 
 * 1.使用纯java代码，不在需要xml
 * 
 * 2.在配置中也可享受OO带来的好处
 * 
 * 3.类型安全对重构也能提供良好的支持
 * 
 * 4.依旧能享受到所有springIoC容器提供的功能
 * 
 * @author zt
 */
@Configuration
public class ApplicationConfig {

	/**
	 * 过java代码来装配 OrderService 的方案
	 * 
	 * @return
	 */
	@Bean
	public OrderService orderService() {
		return new OrderService(orderRepository());
	}

	@Bean
	public OrderRepository orderRepository() {
		return new OrderRepository(dataSource());
	}

	@Bean
	public String dataSource() {
		return "hello";
	}
}