package com.sky.projects.ws.demo.client;

import javax.xml.ws.Endpoint;

import com.sky.projects.ws.demo.service.impl.UserServiceImpl;

public class WsClient {
	public static void main(String[] args) {
		String address = "http://172.20.1.36:8282/simple-ws-demo/userService";
		Endpoint.publish(address, new UserServiceImpl());
	}
}
