package com.zt.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
	/**
	 * To get all declared methods by the class name.<br />
	 * 根据全类名获取所有的方法
	 * 
	 * @param className
	 *            全类名
	 * @return
	 */
	public static Method[] methods(String className) {

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getDeclaredMethods();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To get all declared methods by the class name.<br />
	 * 根据全类名获取所有的public 方法
	 * 
	 * @param className
	 *            全类名
	 * @return
	 */
	public static Method[] publicMethods(String className) {

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getMethods();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To get all declared fields by the class name.<br />
	 * 根据全类名获取所有的属性.<br />
	 * 
	 * @param className
	 *            全类名
	 * @return
	 */
	public static Field[] fields(String className) {

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getDeclaredFields();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To get all declared fields by the class name.<br />
	 * 根据全类名获取所有的public 属性.<br />
	 * 
	 * @param className
	 *            全类名
	 * @return
	 */
	public static Field[] publicFields(String className) {

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getFields();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To get all declared fields by the class name.<br />
	 * 根据全类名获取所有的 注解.<br />
	 * 
	 * @param className
	 *            全类名
	 * @return
	 */
	public static Annotation[] annotations(String className) {

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getAnnotations();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To get all declared fields by the class name.<br />
	 * 根据全类名获取所有的 注解.<br />
	 * 
	 * @param className
	 *            全类名
	 * @return
	 */
	public static Annotation[] declaredAnnotations(String className) {

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getDeclaredAnnotations();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
