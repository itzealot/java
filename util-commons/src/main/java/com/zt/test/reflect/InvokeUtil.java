package com.zt.test.reflect;

import java.lang.reflect.Method;

/**
 * 反射的工具类 InvokeUtil
 * 
 * @author zt
 *
 */
public final class InvokeUtil {

	private InvokeUtil() {
	}

	/**
	 * 根据Class对象与方法名称执行该类中的方法并返回结果
	 * 
	 * @param calzz
	 *            需要反射的类对象
	 * @param methodName
	 *            方法名称
	 * @return 返回执行结果
	 */
	public static Object execute(Class<?> calzz, String methodName,
			Object[] args, Class<?>... parameterTypes) {
		Method m = null;
		Object action = null;
		Object result = null;
		try {

			// 1. 根据方法名称与clazz对象获取Method对象
			m = calzz.getMethod(methodName, parameterTypes);

			// 2. 通过clazz对象获取对象实例
			action = calzz.newInstance();

			// 3. 通过方法与实例对象反射执行方法
			result = m.invoke(action, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据全类名与方法名执行该类中的方法，并返回结果
	 * 
	 * @param calzz
	 *            需要反射的类对象
	 * @param methodName
	 *            方法名称
	 * @return 返回执行结果
	 */
	public static Object execute(String className, String methodName,
			Object[] args, Class<?>... parameterTypes) {
		Object result = null;
		try {

			// 1. 通过全类名获取clazz对象
			Class<?> calzz = Class.forName(className);

			// 2. 执行方法并返回结果
			result = execute(calzz, methodName, args, parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}