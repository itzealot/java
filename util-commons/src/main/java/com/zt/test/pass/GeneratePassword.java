package com.zt.test.pass;

import java.util.UUID;

public final class GeneratePassword {
	private GeneratePassword() {
	}

	/**
	 * 产生长度为length 的密钥
	 * 
	 * @param length
	 * @return
	 */
	public static String generatePassword(int length) {
		if (length <= 0 || length > 8) {
			length = 8;
		}
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, length);
	}

	/**
	 * 随机产生8位密钥
	 * 
	 * @return
	 */
	public static String generatePassword() {
		return generatePassword(8);
	}
}
