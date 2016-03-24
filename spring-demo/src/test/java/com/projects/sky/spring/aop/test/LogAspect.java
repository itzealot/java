package com.projects.sky.spring.aop.test;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 把这个类声明为一个切面： 需要把该类放入袋IOC容器中，再声明为一个切面
 * 
 * 注解 @Order 设置切面的优先级，值越小，优先级越高，从而确定切面的执行顺序。
 * 
 * @author zengtao
 *
 */
@Order(2)
@Aspect
@Component
public class LogAspect {

	/**
	 * 定义一个方法，用于声明切点表达式，一般地，该方法中不再需要添加其他的代码。 其他切面需要引用时，若不在同一个包中，则需要加包名.类名。
	 */
	@Pointcut("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(..))")
	public void declareJointExpression() {
	}

	/**
	 * 声明该方法是一个前置通知：在目标方法开始之前执行，对单个方法进行配置
	 * 
	 * 加入切入点JoinPoint
	 * 
	 * 对所有方法进行通配
	 * 
	 * ..代表连个参数；.代表一个参数
	 * 
	 * @param joinPoint
	 */
	@Before("declareJointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		// System.out.println("before method...");

		System.out.println("The method " + methodName + " begins with " + args);
	}

	/**
	 * 对单个方法配置
	 * 
	 * 对所有方法进行通配
	 * 
	 * 在后置通知中不能访问目标方法执行的结果
	 * 
	 * @param joinPoint
	 */
	@After("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());

		// System.out.println("before method...");

		System.out.println("The method " + methodName + " ends with " + args);
	}

	/**
	 * 在方法正常结束执行的代码。 返回通知是可以访问到方法的返回值的！
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "declareJointExpression()", returning = "result")
	public void afterRunning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with " + result);
	}

	/**
	 * 在目标方法出现异常时会执行的代码。 可以访问到异常对象；且可以指定在出现特定异常时在执行通知代码。
	 * 
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "declareJointExpression()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + ex);
	}

	/**
	 * 环绕通知需要写的ProceedingJoinPoint 类型的参数。 环绕通知类似于动态代理的全过程： ProceedingJoinPoint
	 * 类型的参数可以决定是否执行目标方法。 且环绕通知必须有返回值，返回值即为目标方法的返回值。
	 */
	// @Around("execution(public int
	// spring.aop.test.ArithmeticCalculatorImpl.*(int, int))")
	@Around("declareJointExpression()")
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
			// TODO
			// 异常通知
			System.out.println("The method " + methodName + " occurs exception: " + e);
			throw new RuntimeException(e);
		}

		return result;
	}
}
