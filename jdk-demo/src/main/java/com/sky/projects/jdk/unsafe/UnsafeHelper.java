package com.sky.projects.jdk.unsafe;

import java.lang.reflect.Field;

/**
 * UnsafeHelper
 * 
 * @author zealot
 */
@SuppressWarnings("restriction")
public final class UnsafeHelper {

	// Unsafe 不允许直接调用，需要通过反射获取
	private static final sun.misc.Unsafe UNSAFE;

	static {
		try {
			// 根据内部属性名称获取字段
			Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);

			// 通过属性获取，因为是 static 属性，所以 object 为 null
			UNSAFE = (sun.misc.Unsafe) f.get(null);
		} catch (Exception e) { // 需抛出 RuntimeException 阻止 Jvm 正常运行
			throw new RuntimeException("reflect sun.misc.Unsafe instance fail.", e);
		}
	}

	public static sun.misc.Unsafe unsafe() {
		return UNSAFE;
	}

	private UnsafeHelper() {
	}
}
