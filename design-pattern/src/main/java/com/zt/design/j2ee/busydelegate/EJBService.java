package com.zt.design.j2ee.busydelegate;

/**
 * EJB服务实现类 EJBService
 * 
 * @author zengtao
 *
 */
public class EJBService implements BusinessService {

	public void doProcessing() {
		System.out.println("Processing task by invoking EJB Service");
	}
}
