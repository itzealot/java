package com.zt.design.j2ee.busydelegate;

/**
 * 创建实体服务类 JMSService
 * 
 * @author zengtao
 *
 */
public class JMSService implements BusinessService {

	public void doProcessing() {
		System.out.println("Processing task by invoking JMS Service");
	}
}