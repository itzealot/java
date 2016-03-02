package com.zt.test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面
 * 
 * @author zt
 * 
 */
@Aspect
@Component
public class AspectDemo {
	/**
	 * 切入点,切点表达式
	 * 
	 * execution 为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包。(..)代 表各种方法
	 */
	@Pointcut("execution(* com.bird.service.impl.PersonServiceBean.*(..))")
	private void anyMethod() {
	}

	/**
	 * 前置通知
	 * 
	 * @param name
	 */
	@Before("anyMethod() && args(name)")
	public void before(String name) {
		// TODO
	}

	/**
	 * 后置通知
	 */
	@AfterReturning("anyMethod()")
	public void doAfter() {
		// TODO
	}

	/**
	 * 最终通知
	 */
	@After("anyMethod()")
	public void after() {
		// TODO
	}

	/**
	 * 例外通知
	 */
	@AfterThrowing("anyMethod()")
	public void doAfterThrow() {
		// TODO
	}

	/**
	 * 环绕通知
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint point) throws Throwable {
		// before method

		// execute
		Object object = point.proceed();

		// after method

		return object;
	}
}