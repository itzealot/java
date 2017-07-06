package com.sky.projects.jdk.hessian;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * HessianClient
 * 
 * @author zealot
 *
 */
public class HessianClientTest {

	public static void main(String[] args) throws MalformedURLException {
		// 服务端 url
		String url = "http://localhost:8080/sky-hessian-demo/HelloWorldServlet";

		HessianProxyFactory factory = new HessianProxyFactory();
		BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);

		// 接口方法名称可以不一致，但是接口函数及返回值必须一致
		System.out.println("hello: " + basic.hello());
	}
}
