package com.sky.projects.web.common.mvc;

/**
 * Html String Util
 * 
 * @author zealot
 */
public final class HtmlStringUtil {

	/**
	 * 对特殊的html字符进行转义，返回转义后的字符串
	 * 
	 * @param message
	 * @return 返回转义后的字符串
	 */
	public static String filter(String message) {
		if (message == null || message.isEmpty()) {
			return null;
		}

		StringBuffer result = new StringBuffer();
		for (int i = 0, len = message.length(); i < len; i++) {
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

	private HtmlStringUtil() {
	}
}