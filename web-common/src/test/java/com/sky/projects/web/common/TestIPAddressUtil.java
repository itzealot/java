package com.sky.projects.web.common;

import java.net.SocketException;

import com.sky.projects.web.common.mvc.IPAddressUtil;

import junit.framework.TestCase;

public class TestIPAddressUtil extends TestCase {

	public void testGetLocalRealIp() {
		try {
			System.out.println(IPAddressUtil.getLocalRealIp());
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
