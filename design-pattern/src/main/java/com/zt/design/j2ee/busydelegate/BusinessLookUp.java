package com.zt.design.j2ee.busydelegate;

/**
 * 创建业务查询服务 BusinessLookUp
 * 
 * @author zengtao
 *
 */
public class BusinessLookUp {
	/**
	 * 根据服务类型创建不同的服务对象
	 * 
	 * @param serviceType
	 * @return
	 */
	public BusinessService getBusinessService(String serviceType) {
		if (serviceType.equalsIgnoreCase("EJB")) {
			return new EJBService();
		} else {
			return new JMSService();
		}
	}
}
