package com.sky.projects.jdk.rmi.hello.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.sky.projects.jdk.rmi.hello.spi.Helloable;

/**
 * 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。
 */
public class HelloClient {

	public static void main(String args[]) {
		try {
			// 在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法
			Helloable rhello = (Helloable) Naming.lookup("rmi://localhost:8888/RHello");

			System.out.println(rhello.helloWorld());
			System.out.println(rhello.sayHelloToSomeBody("熔岩"));

		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}