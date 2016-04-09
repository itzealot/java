package com.sky.projects.spring.aop.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {

	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		// System.out.println("before method...");
		System.out.println("The method " + methodName + " begins with " + args);
	}

	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		// System.out.println("before method...");
		System.out.println("The method " + methodName + " ends with " + args);
	}

	public void afterRunning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with " + result);
	}

	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + ex);
	}

	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("arround method.");
		Object result = null;
		String methodName = joinPoint.getSignature().getName();

		// 执行目标方法
		try {
			// 前置通知
			List<Object> args = Arrays.asList(joinPoint.getArgs());

			System.out.println("The method " + methodName + " ends with " + args);
			result = joinPoint.proceed();
			// 后置通知
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			// TODO: handle exception
			// 异常通知
			System.out.println("The method " + methodName + " occurs exception: " + e);
			throw new RuntimeException(e);
		}
		return result;
	}
}
