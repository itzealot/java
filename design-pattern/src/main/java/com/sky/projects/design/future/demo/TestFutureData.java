package com.sky.projects.design.future.demo;

public class TestFutureData {

	public static void main(String[] args) throws Exception {
		// 1. 客户端发起请求，获取 RealData
		Data result = new Client().request("req");

		// 2. do other things
		doOtherThings();

		// 3. handle the result
		handle(result);
	}

	public static void handle(Data result) {
		System.out.println("the result: " + result.getResult());
	}

	public static void doOtherThings() throws InterruptedException {
		System.out.println("start do other things.....");
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
		}
		System.out.println("finish do other things.....");
	}
}
