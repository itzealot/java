package com.sky.projects.spring;

import javax.xml.ws.Endpoint;

import com.sky.projects.spring.spi.impl.UserServiceImpl;

/*
 * 发布Web Service
 */
public class TestUserService {

	public static void main(String[] args) {
		String address = "http://localhost:8181/spring-ws-web/userService";

		Endpoint.publish(address, new UserServiceImpl());

		System.out.println("发布webservice成功!");
	}
}