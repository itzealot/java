package com.sky.projects.spring.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 关于在spring 容器初始化 bean 和销毁前所做的操作定义方式有三种：<br />
 * --第一种：通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作.<br />
 * --第二种是：通过 在xml中定义init-method 和 destory-method 方法.<br />
 * --第三种是： 通过bean实现InitializingBean 和 DisposableBean 接口.<br />
 * 
 * @author zt
 *
 */
public class MessageBean {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void init() {
		System.out.println("I'm  init  method  using  @PostConstrut...."
				+ message);
	}

	@PreDestroy
	public void dostory() {
		System.out.println("I'm  destory method  using  @PreDestroy....."
				+ message);
	}

}