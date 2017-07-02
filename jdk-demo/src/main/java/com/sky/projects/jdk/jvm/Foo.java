package com.sky.projects.jdk.jvm;

public class Foo {

	public static final int MAX_DEPTH = 1000;
	public static final float MAX_FLOAT_VALUE = 10000F;

	private static int count = 0;

	public void add() {
		if (count >= MAX_DEPTH) {
			throw new RuntimeException();
		}
		count++;
	}

	public static int getCount() {
		return count;
	}

	public static float getMaxFloatValue() {
		return MAX_FLOAT_VALUE;
	}
}
