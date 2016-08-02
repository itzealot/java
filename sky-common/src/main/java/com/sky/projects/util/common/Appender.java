package com.sky.projects.util.common;

import java.util.List;

/**
 * Append util by StringBuffer and String Builder, default is StringBuffer.
 * 
 * @author zealot
 */
public final class Appender {

	public static String append(Object... objects) {
		return objects == null ? "null" : append0(objects);
	}

	private static String append0(Object... objects) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = objects.length; i < len; i++) {
			buffer.append(objects[i]);
		}
		return buffer.toString();
	}

	public static <T> String append(List<T> lists) {
		return lists == null ? "null" : append0(lists);
	}

	private static <T> String append0(List<T> lists) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = lists.size(); i < len; i++) {
			buffer.append(lists.get(i));
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> String append(List<T>... arsg) {
		return arsg == null ? "null" : append0(arsg);
	}

	@SuppressWarnings("unchecked")
	private static <T> String append0(List<T>... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(append0(args[i]));
		}
		return buffer.toString();
	}

	public static String appends(char... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(char... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(byte... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(byte... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(boolean... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(boolean... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(short... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(short... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(int... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(int... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(float... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(float... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(long... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(long... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appends(double... args) {
		return args == null ? "null" : append0(args);
	}

	private static String append0(double... args) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(Object... objects) {
		return objects == null ? "null" : appendWithStringBuilder0(objects);
	}

	private static String appendWithStringBuilder0(Object... objects) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = objects.length; i < len; i++) {
			buffer.append(objects[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(boolean... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(boolean... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(byte... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(byte... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(char... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(char... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(short... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(short... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(int... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(int... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(float... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(float... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(long... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(long... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String appendWithStringBuilder(double... args) {
		return args == null ? "null" : appendWithStringBuilder0(args);
	}

	private static String appendWithStringBuilder0(double... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static <T> String appendWithStringBuilder(List<T> lists) {
		return lists == null ? "null" : appendWithStringBuilder0(lists);
	}

	private static <T> String appendWithStringBuilder0(List<T> lists) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = lists.size(); i < len; i++) {
			buffer.append(lists.get(i));
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> String appendWithStringBuilder(List<T>... arsg) {
		return arsg == null ? "null" : appendWithStringBuilder0(arsg);
	}

	@SuppressWarnings("unchecked")
	private static <T> String appendWithStringBuilder0(List<T>... args) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = args.length; i < len; i++) {
			buffer.append(append0(args[i]));
		}
		return buffer.toString();
	}

	private Appender() {
	}

}
