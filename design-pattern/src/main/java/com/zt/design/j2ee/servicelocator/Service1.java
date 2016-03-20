package com.zt.design.j2ee.servicelocator;

public class Service1 implements Service {

	public String getName() {
		return "Service1";
	}

	public void execute() {
		System.out.println("Executing Service1");
	}

}
