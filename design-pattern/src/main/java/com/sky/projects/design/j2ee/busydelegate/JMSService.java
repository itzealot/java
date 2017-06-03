package com.sky.projects.design.j2ee.busydelegate;

/**
 * JMS服务实现类 JMSService
 * 
 * @author zealot
 *
 */
public class JMSService implements BusinessService {

	@Override
	public void doProcessing() {
		System.out.println("Processing task by invoking JMS Service");
	}
}