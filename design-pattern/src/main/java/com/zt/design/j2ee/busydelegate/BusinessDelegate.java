package com.zt.design.j2ee.busydelegate;

/**
 * 创建业务代表 BusinessDelegate
 * 
 * @author zengtao
 *
 */
public class BusinessDelegate {
	// 持有业务查询服务 BusinessLookUp 实例
	private BusinessLookUp lookupService = new BusinessLookUp();

	// 持有服务BusinessService 引用
	private BusinessService businessService;

	// 持有服务类型
	private String serviceType;

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * 执行任务
	 */
	public void doTask() {
		// 根据服务类型创建不同的服务对象
		businessService = lookupService.getBusinessService(serviceType);

		// 调用服务方法
		businessService.doProcessing();
	}
}