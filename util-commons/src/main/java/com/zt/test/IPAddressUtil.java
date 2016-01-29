package com.zt.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 1. 获取真实IP 地址的工具类.<br />
 * 
 * 2. NetworkInterface ： <br />
 * ---此类表示一个由名称和分配给此接口的 IP 地址列表组成的网络接口。它用于标识加入多播组的本地接口。 接口通常是按名称（如 "le0"）区分的。
 * 
 * 3. NetworkInterface main method : <br />
 * 1). String displayName()<br />
 * ---获取网络接口的显示名称
 * 
 * 2). byte[] getHardwareAddress()<br />
 * ---获取网络接口的物理地址,通常是MAC地址
 * 
 * 3). int getMTU()<br />
 * ---返回此接口的最大传输单元（Maximum Transmission Unit，MTU）
 * 
 * 4). String getName()<br />
 * ---获取此网络接口的名称
 * 
 * 5). boolean isLoopback()<br />
 * ---返回网络接口是否是回送接口
 * 
 * 6). boolean isPointToPoint()<br />
 * ---返回网络接口是否是点对点接口
 * 
 * 7). boolean isUp()<br />
 * ---返回网络接口是否已经开启并运行
 * 
 * 8). boolean isVirtual()<br />
 * ---返回此接口是否是虚拟接口（也称为子接口）
 * 
 */
public class IPAddressUtil {
	/**
	 * 获取本机真正的IP
	 * 
	 * @return 本机IP地址
	 * @throws SocketException
	 */
	public static String getLocalRealIp() throws SocketException {
		// 本地IP，如果没有配置外网IP则返回它
		String localip = null;

		// 外网IP
		String netip = null;

		// NetworkInterface 中定义了很多获取网络接口信息的方法，其中很多是jdk1.6版本以后才加入进去的
		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;

		// 是否找到外网IP
		boolean finded = false;

		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				
				if (!ip.isSiteLocalAddress() 
						&& !ip.isLoopbackAddress() 
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() 
							&& !ip.isLoopbackAddress() 
							&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	/**
	 * 根据 HttpServletRequest 对象 获取登录用户IP地址
	 * 
	 * @param request
	 *            HttpServletRequest object
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}
}
