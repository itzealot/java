package com.zt.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MacAddressUtil {

	/**
	 * 将得来的int类型数字转化为十六进制数字符串
	 * 
	 * @param integer
	 * @return
	 */
	private static String toHexString(int integer) {
		// 将得来的int类型数字转化为十六进制数
		String str = Integer.toHexString((int) (integer & 0xFF));

		// 如果遇到单字符，前置0占位补满两格
		if (str.length() == 1) {
			str = "0" + str;
		}
		return str.toUpperCase();
	}

	/**
	 * 得到本机的Mac地址
	 * 
	 * @return
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static String getMACAddr() throws SocketException, UnknownHostException {
		// 获得IP
		NetworkInterface netInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		final String split = "-";

		// 获得Mac地址的byte数组
		byte[] macAddr = netInterface.getHardwareAddress();

		String macAddress = "";

		for (byte b : macAddr) {
			macAddress += toHexString(b);
			macAddress += split;
		}
		return macAddress.substring(0, macAddress.length() - 1);
	}

	public static void main(String[] args) {
		try {
			System.out.println(getMACAddr());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
