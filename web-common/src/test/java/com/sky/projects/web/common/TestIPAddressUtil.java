package com.sky.projects.web.common;

import java.net.SocketException;

import org.junit.Test;

import com.sky.projects.web.common.mvc.IPAddressUtil;

public class TestIPAddressUtil {

	@Test
	public void testGetLocalRealIp() {
		try {
			System.out.println(IPAddressUtil.getLocalRealIp());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
