package com.sky.projects.spring.aop.hello.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.sky.projects.spring.aop.hello.ArithmeticCalculator;

public class ArithmeticCalculatorProxy {
	// 需要代理的对象
	private ArithmeticCalculator target;

	public ArithmeticCalculatorProxy(ArithmeticCalculator target) {
		this.target = target;
	}

	// 动态代理
	public ArithmeticCalculator getProxy() {
		ArithmeticCalculator proxy = null;
		// 代理对象由哪一个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();

		// 代理对象的类型，即其中有哪些方法
		@SuppressWarnings("rawtypes")
		Class[] interfaces = new Class[] { ArithmeticCalculator.class };

		// 当调用代理对象其中的方法时，该执行的代码。
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy: 正在返回的那个代理对象，一般情况下，在invoke 方法中都不使用该对象。 method: 正在被调用的方法。
			 * args: 调用方法时，传入的参数。
			 */
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String methodName = method.getName();

				// 日志
				System.out.println("The method " + methodName + " begin with " + Arrays.asList(args));

				Object result = null;

				try {
					// 执行方法，可能会出异常
					// 前置通知
					result = method.invoke(target, args);
					// 返回通知，可以访问到方法的返回值。
				} catch (Exception e) {
					// TODO
					// 异常通知，可以访问到方法出现的异常。
				}
				// 后置通知

				// 日志
				System.out.println("The method " + methodName + " ends with " + result);
				return result;
			}
		};

		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);

		return proxy;
	}
}
