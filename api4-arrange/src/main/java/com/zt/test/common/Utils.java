package com.zt.test.common;

public class Utils {

	/**
	 * 将 int 类型转换成 byte[]
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] int2Bytes(int value) {
		byte[] b = new byte[4];

		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}

		return b;
	}

	/**
	 * 将 byte[] 合并成 int
	 * 
	 * @param b
	 * @return
	 */
	public static final int bytes2Int(byte[] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
	}

	/**
	 * 将 boolean 转化成 byte
	 * 
	 * @param val
	 * @return
	 */
	public static final byte boolean2Byte(boolean val) {
		return (byte) (val ? 1 : 0);
	}

	/**
	 * 将 byte 解析成 boolean
	 * 
	 * @param val
	 * @return
	 */
	public static final boolean byte2Boolean(byte val) {
		return (val == 0x00) ? false : true;
	}
}
