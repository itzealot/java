package com.zt.projects.sky.util;

import java.security.MessageDigest;

/**
 * MD5算法工具类
 * 
 * @author zengtao
 *
 */
public class MD5Utils {

	/**
	 * To get the MD5String<br />
	 * 
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String getMD5String(String source) throws Exception {
		if (source == null || "".equals(source)) {
			return source;
		}
		StringBuffer buffer = new StringBuffer();
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		byte[] bytes = source.getBytes();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] targ = digest.digest(bytes);
		for (byte b : targ) {
			// To get the 4 high bits
			buffer.append(chars[b >> 4 & 0x0F]);
			// To get the 4 low bits
			buffer.append(chars[b & 0x0F]);
		}
		return buffer.toString();
	}
}
