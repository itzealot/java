package com.sky.projects.design.structural.proxy.dynamic.javassist;

import java.lang.reflect.Field;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * JavassistProxyFactory
 * 
 * @author zealot
 *
 */
public final class JavassistProxyFactory {

	/**
	 * 根据实现类及MethodHandler实现类创建代理
	 * 
	 * @param target
	 *            需要创建代理的实现类
	 * @param handler
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T target, MethodHandler handler) {
		ProxyFactory factory = new ProxyFactory();

		// set interface
		// factory.setInterfaces(target.getClass().getInterfaces());

		// deprecated method
		// factory.setHandler(handler);

		// 设置 super class，如果被代理类中需要调用未抽象出接口的方法，选择调用该方法
		factory.setSuperclass(target.getClass());

		try {
			T instance = (T) factory.createClass().newInstance();
			((ProxyObject) instance).setHandler(handler);
			return instance;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T createJavassistDynamicProxy(T delegate, MethodHandler handler) throws Exception {
		ProxyFactory factory = new ProxyFactory();

		factory.setInterfaces(delegate.getClass().getInterfaces());

		Class<?> proxyClass = factory.createClass();
		T proxt = (T) proxyClass.newInstance();

		((ProxyObject) proxt).setHandler(handler);
		return proxt;
	}

	/**
	 * createBytecodeDynamicProxy
	 * 
	 * @param delegate
	 * @param clazz
	 *            interface's Class Object
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createBytecodeDynamicProxy(T delegate, Class<?> clazz) throws Exception {
		// ClassPool pool = new ClassPool(true); // or
		ClassPool pool = ClassPool.getDefault();

		String className = clazz.getName();
		CtClass cc = pool.makeClass(className + "JavassistProxy");

		cc.addInterface(pool.get(className));

		// add default Constructor
		cc.addConstructor(CtNewConstructor.defaultConstructor(cc));

		// add Field delegate to save the implement object
		cc.addField(CtField.make("public " + className + " delegate;", cc));

		// add the implement method
		cc.addMethod(CtNewMethod.make("public int count() { return delegate.count(); }", cc));

		T proxy = (T) cc.toClass().newInstance();

		// set delegate
		Field filed = proxy.getClass().getField("delegate");
		filed.set(proxy, delegate);

		return proxy;
	}

	private JavassistProxyFactory() {
	}
}
