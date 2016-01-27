package com.zt.test.base;

public class TestByte2String {

	/**
	 * To get the String from byte array.<br />
	 * If the byte array isn't validate, return null; else return the byte
	 * array's String.<br />
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getString(byte[] bytes) {
		if (!isValidate(bytes)) {
			return null;
		}
		return new String(bytes);
	}

	/**
	 * To get the byte array from String.<br />
	 * If the String isn't validate then return null; else return the String's
	 * byte array.<br />
	 * 
	 * @param source
	 * @return
	 */
	public static byte[] getBytes(String source) {
		if (!isValidate(source)) {
			return null;
		}
		return source.getBytes();
	}

	public static void main(String[] args) {
		System.out.println(getString(null));
		display(getBytes("abaas我"));
		display(getBytes(""));
		display(getBytes(" "));
		System.out.println(getBytes(null));
	}

	/**
	 * 字母以字符的 ASCII 码输出<br />
	 * Unicode 编码以三个字节输出
	 * 
	 * @param bytes
	 */
	public static void display(byte[] bytes) {
		if (isValidate(bytes)) {
			int length = bytes.length;
			for (int i = 0; i < length; i++) {
				System.out.print(bytes[i] + " ");
			}
			System.out.println();

			return;
		}
		System.out.println("null");

	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static boolean isValidate(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isValidate(String source) {
		if (source == null) {
			return false;
		}
		return true;
	}
}
