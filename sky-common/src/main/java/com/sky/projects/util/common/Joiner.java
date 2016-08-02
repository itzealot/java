package com.sky.projects.util.common;

import java.util.List;

public final class Joiner {

	public static String join(final String spliter, Object... objects) {
		StringBuffer buffer = new StringBuffer();
		if (objects == null) {
			buffer.append("null");
		} else {
			int i = 0, len = objects.length;
			for (; i < len - 1; i++) {
				buffer.append(objects[i]);
				buffer.append(spliter);
			}
			buffer.append(objects[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, byte... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, boolean... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, char... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, short... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, int... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, float... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, long... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static String join(final String spliter, double... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(args[i]);
				buffer.append(spliter);
			}
			buffer.append(args[i]);
		}
		return buffer.toString();
	}

	public static <T> String join(final String spliter, List<T> args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.size();
			for (; i < len - 1; i++) {
				buffer.append(args.get(i));
				buffer.append(spliter);
			}
			buffer.append(args.get(i));
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> String join(final String spliter, List<T>... args) {
		StringBuffer buffer = new StringBuffer();
		if (args == null) {
			buffer.append("null");
		} else {
			int i = 0, len = args.length;
			for (; i < len - 1; i++) {
				buffer.append(join(spliter, args[i]));
				buffer.append(spliter);
			}
			buffer.append(join(spliter, args[i]));
		}
		return buffer.toString();
	}

	private Joiner() {
	}
}
