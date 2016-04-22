package com.sky.projects.spring.annotation;

public class MessageTest extends AbstractTest {

	public void test() {
		MessageBean message = context.getBean(MessageBean.class);
		message.dostory();
	}

}