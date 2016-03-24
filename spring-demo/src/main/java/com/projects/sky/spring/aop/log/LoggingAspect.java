package com.projects.sky.spring.aop.log;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 把这个类声明为一个切面： 需要把该类放入袋IOC容器中，再声明为一个切面
 * 
 * @author zengtao
 *
 */
@Aspect
@Component
public class LoggingAspect {

	/**
	 * 声明该方法是一个前置通知：在目标方法开始之前执行，对单个方法进行配置
	 * 
	 * 加入切入点JoinPoint
	 * 
	 * 对所有方法进行通配
	 * 
	 * @param joinPoint
	 */
	@Before("execution(public int spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());

		// System.out.println("before method...");

		System.out.println("The method " + methodName + " begins with " + args);
	}

	/**
	 * 对单个方法配置
	 * 
	 * 对所有方法进行通配 在后置通知中不能访问目标方法执行的结果
	 */
	@After("execution(public int spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))")
	public void afterMethod() {
		System.out.println("after method...");
	}
}
