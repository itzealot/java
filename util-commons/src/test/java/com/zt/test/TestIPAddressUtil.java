package com.zt.test;

import java.net.SocketException;

import org.junit.Test;

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
