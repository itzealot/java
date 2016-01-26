package com.zt.design.j2ee.busydelegate;

/**
 * 创建实体服务类 EJBService
 * 
 * @author zengtao
 *
 */
public class EJBService implements BusinessService {

	public void doProcessing() {
		System.out.println("Processing task by invoking EJB Service");
	}
}
