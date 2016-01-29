package com.zt.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
	/**
	 * 1. set注入即利用 setXXX 方法进行注入.<br />
	 * 2. 使用注解 @Autowired 标识属性，Spring 容器是利用 setXXX 方法来注入，所以应该注意属性名称与 Spring 管理的
	 * Bean 的名称是否一致，不一致需要利用注解 @Bean 进行命名
	 */
	final private MessageService service;

	/**
	 * 使用注解 @Autowired 来标识构造函数，将该Bean注入，即构造注入
	 * 
	 * @param service
	 */
	@Autowired
	public MessagePrinter(MessageService service) {
		this.service = service;
	}

	public void printMessage() {
		System.out.println(this.service.getMessage());
	}
}