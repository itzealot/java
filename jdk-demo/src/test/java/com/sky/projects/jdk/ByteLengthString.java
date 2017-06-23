package com.sky.projects.jdk;

public class ByteLengthString {

	/**
	 * 计算字节长度，这里假设一个英文字符占一个字节；一个中文字符占两个字节
	 * 
	 * @param ch
	 * @return
	 */
	public static int getCharLength(int ch) {
		return ch >= 0x00 && ch <= 0xFF ? 1 : 2;
	}

	public static String getStringByByteLength(String source, int byteLength) {
		if (source == null || source.isEmpty()) {
			return "";
		}

		if (byteLength <= 0) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int i = 0;
		char ch;
		int sum = byteLength - 1;
		int length = source.length();
		while (i < length && sum > 0) {
			ch = source.charAt(i);
			buffer.append(ch);
			sum -= getCharLength(ch);
			i++;
		}

		/**
		 * 判断最后一个是否为英文字母.<br />
		 * 为英文字母且sum == 0说明最后一个是英文字母且是最后一个
		 */
		ch = source.charAt(i);
		if (getCharLength(ch) == 1 && sum == 0) {
			buffer.append(ch);
		}
		return buffer.toString();
	}

	public static void print(String source) {
		int length = source.length();
		for (int i = 0; i < length; i++) {
			// 转换为int类型的编码
			int ch = source.charAt(i);
			System.out.println(source.charAt(i) + "'s code :" + ch);
		}
	}
}
