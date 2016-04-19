package com.apusic.sicp.util;

public final class Throwables {
	public static final boolean hasTypeInCauses(Throwable throwable, Class<?> findType) {
		Throwable cause = throwable;

		while (cause != null) {
			if (findType.isAssignableFrom(cause.getClass())) {
				return true;
			}
			cause = throwable.getCause();
		}

		return false;
	}

	public static void main(String[] args) {
		System.out.println(Integer.class.isAssignableFrom(Object.class));
	}
}
