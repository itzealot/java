package com.zt.design.j2ee.frontcontroller;

/**
 * 调度器
 * 
 * @author zealot
 *
 */
public class Dispatcher {

	private StudentView studentView;

	private HomeView homeView;

	public Dispatcher() {
		// 创建 Dispatcher 时，初始化视图
		studentView = new StudentView();

		homeView = new HomeView();
	}

	/**
	 * 根据不同的 request 调度
	 * 
	 * @param request
	 */
	public void dispatch(String request) {
		if (request.equalsIgnoreCase("STUDENT")) {
			studentView.show();
		} else {
			homeView.show();
		}
	}
}
