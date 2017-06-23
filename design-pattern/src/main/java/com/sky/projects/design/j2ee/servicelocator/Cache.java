package com.sky.projects.design.j2ee.servicelocator;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存
 * 
 * @author zealot
 *
 */
public class Cache {

	private List<Service> services;

	public Cache() {
		services = new ArrayList<Service>();
	}

	/**
	 * To get Service from List<Service>
	 * 
	 * @param serviceName
	 * @return
	 */
	public Service getService(String serviceName) {
		for (Service service : services) {
			if (service.getName().equalsIgnoreCase(serviceName)) {
				System.out.println("Returning cached  " + serviceName + " object");
				return service;
			}
		}

		return null;
	}

	/**
	 * To get Service into List<Service>
	 * 
	 * @param newService
	 */
	public void addService(Service newService) {
		boolean exists = false;

		for (Service service : services) {
			if (service.getName().equalsIgnoreCase(newService.getName())) {
				exists = true;
			}
		}

		if (!exists) {
			services.add(newService);
		}
	}
}