package com.sky.projects.jdk.jni;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JniTest extends TestCase {
	public JniTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(JniTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	/**
	 * 引用数组类型格式(L[..className;),n维数组n个[
	 */
	public void testRefClass() {
		// java.lang.String
		System.out.println(String.class);
		// [Ljava.lang.String;
		System.out.println(String[].class);
		// [[Ljava.lang.String;
		System.out.println(String[][].class);

		System.out.println(Object[].class);
		System.out.println(Object.class);

		System.out.println(Integer[].class);
		System.out.println(Integer.class);

		System.out.println(Void.class.getName());
	}

	/**
	 * 原始类型及对原始类型的数组类型的Class对象
	 */
	public void testPrimitiveClass() {
		/** 1 bit */
		// [Z(一维数组即一个[)
		System.out.println("boolean[].class:" + boolean[].class.getName());
		// [[Z(二维数组即两个[)
		System.out.println("boolean[][].class:" + boolean[][].class.getName());
		// [[[Z(三维数组即三个[)
		System.out.println("boolean[][][].class:" + boolean[][][].class.getName());
		// boolean
		System.out.println("boolean.class:" + boolean.class.getName());
		System.out.println();

		/** 8 bit=>1B */
		// [B
		System.out.println("byte[].class:" + byte[].class.getName());
		System.out.println("byte[][].class:" + byte[][].class.getName());
		System.out.println("byte[][][].class:" + byte[][][].class.getName());
		// byte
		System.out.println("byte.class:" + byte.class.getName());
		System.out.println("bits:" + Byte.SIZE);
		System.out.println();

		/** 16 bit=>2B */
		// [C
		System.out.println("char[].class:" + char[].class.getName());
		// char
		System.out.println("char.class:" + char.class.getName());
		System.out.println("bits:" + Character.SIZE);
		System.out.println();

		/** 16 bit=>2B */
		// [S
		System.out.println("short[].class:" + short[].class.getName());
		// short
		System.out.println("short.class:" + short.class.getName());
		System.out.println("bits:" + Short.SIZE);
		System.out.println();

		/** 32 bit=>4B */
		// [I
		System.out.println("int[].class:" + int[].class.getName());
		// int
		System.out.println("int.class:" + int.class.getName());
		System.out.println("bits:" + Integer.SIZE);
		System.out.println();

		/** 32 bit=>4B */
		// [F
		System.out.println("float[].class:" + float[].class.getName());
		// float
		System.out.println("float.class:" + float.class.getName());
		System.out.println("bits:" + Float.SIZE);
		System.out.println();

		/** 64 bit=>8B */
		// [J
		System.out.println("long[].class:" + long[].class.getName());
		// long
		System.out.println("long.class:" + long.class.getName());
		System.out.println("bits:" + Long.SIZE);
		System.out.println();

		/** 64 bit=>8B */
		// [D
		System.out.println("double[].class:" + double[].class.getName());
		// double
		System.out.println("double.class:" + double.class.getName());
		System.out.println("bits:" + Double.SIZE);
		System.out.println();

		// void
		System.out.println(void.class.getName());
	}

}
