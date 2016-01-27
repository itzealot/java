package com.zt.projects.sky.util;

public class Strings {
	/**
	 * the source String is containing the destination String.<br />
	 * 原串 source 是否包含 目标串 dest，支持特殊字符匹配.<br />
	 * --1). * is all.<br />
	 * --2). _ is one char.<br />
	 * --3). # is one number.<br />
	 * --4). $ is one word.<br />
	 * 
	 * @param source
	 *            String
	 * @param dest
	 * @return
	 */
	public boolean contains(String source, String dest, String special) {
		int index1 = source.indexOf(special);

		// 包含特殊字符
		if (index1 != -1) {
			while (true) {
				source.substring(0, index1);
				source.substring(index1 + 1);
			}
		}
		return source.contains(dest);
	}
}
