package com.zt.util;

import java.security.MessageDigest;

/**
 * SHA算法工具类
 * 
 * @author zengtao
 *
 */
public class SHAUtil {
	/***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param source
	 *            待加密字符串
	 * @return 返回40位SHA码
	 */
	public static String shaEncode(String source) throws Exception {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		// 根据UTF-8返回String source的字节码
		byte[] byteArray = source.getBytes("UTF-8");

		// 字节码的hash值
		byte[] md5Bytes = digest.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		int length = md5Bytes.length;

		for (int i = 0; i < length; i++) {
			// 获取低16位值
			int val = ((int) md5Bytes[i]) & 0xFF;

			// 值小于16，补0
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}