package com.sky.projects.web.common.mvc;

public class HtmlStringUtil {
	/**
	 * 对特殊的html字符进行编码，并返回编码后的字符串
	 * 
	 * @param message
	 *            含特殊字符的HTML原字符串
	 * @return 返回html中能够显示的字符串
	 */
	public static String filter(String message) {

		int length = 0;
		if (message == null || (length = message.length()) == 0) {
			return null;
		}
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < length; i++) {
			switch (message.charAt(i)) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(message.charAt(i));
			}
		}
		return result.toString();

	}
}