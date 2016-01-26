package com.zt.design.j2ee.frontcontroller;

/**
 * 创建调度器 Dispatcher
 * 
 * @author zengtao
 *
 */
public class Dispatcher {
	private StudentView studentView;
	private HomeView homeView;

	// 创建 Dispatcher 时，初始化视图
	public Dispatcher() {
		studentView = new StudentView();
		homeView = new HomeView();
	}

	/**
	 * 根据不同的String request 调度
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
