package com.zt.design.j2ee.busydelegate;

/**
 * 创建客户端
 * 
 * @author zengtao
 *
 */
public class Client {
	BusinessDelegate businessService;

	public Client(BusinessDelegate businessService) {
		this.businessService = businessService;
	}

	/**
	 * 调用BusinessDelegate 实例的执行任务方法
	 */
	public void doTask() {
		// 执行任务
		businessService.doTask();
	}
}
