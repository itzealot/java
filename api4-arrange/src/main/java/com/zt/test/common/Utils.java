package com.zt.test.common;

public class Utils {

	/**
	 * 将int类型转换成byte[]
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] intToBytes(int value) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}

	/**
	 * 将byte[]合并成int
	 * 
	 * @param b
	 * @return
	 */
	public static final int bytesToInt(byte[] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
	}

	/**
	 * 将boolean转化成byte
	 * 
	 * @param val
	 * @return
	 */
	public static final byte booleanToByte(boolean val) {
		return (byte) (val ? 1 : 0);
	}

	/**
	 * 将byte解析成boolean
	 * 
	 * @param val
	 * @return
	 */
	public static final boolean byteToBoolean(byte val) {
		return (val == 0x00) ? false : true;
	}
}