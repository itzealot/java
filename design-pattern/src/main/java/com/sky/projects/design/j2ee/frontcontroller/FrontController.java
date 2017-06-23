package com.sky.projects.design.j2ee.frontcontroller;

/**
 * 前端控制器
 * 
 * @author zealot
 *
 */
public class FrontController {

	// 持有调度器实例
	private Dispatcher dispatcher;

	// 初始化控制器的同时，初始调度器
	public FrontController() {
		dispatcher = new Dispatcher();
	}

	/**
	 * 是否有权限
	 * 
	 * @return
	 */
	private boolean isAuthenticUser() {
		System.out.println("User is authenticated successfully.");
		return true;
	}

	/**
	 * 记录何种请求
	 * 
	 * @param request
	 */
	private void trackRequest(String request) {
		System.out.println("Page requested: " + request);
	}

	/**
	 * 根据请求进行调度
	 * 
	 * @param request
	 */
	public void dispatchRequest(String request) {
		// 记录每一个请求
		trackRequest(request);

		// 对用户进行身份验证
		if (isAuthenticUser()) {
			dispatcher.dispatch(request);
		}
	}
}
