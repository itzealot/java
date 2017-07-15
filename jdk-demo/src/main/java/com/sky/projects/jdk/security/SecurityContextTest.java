package com.sky.projects.jdk.security;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.Callable;

import junit.framework.TestCase;

/**
 * AccessControlContext, AccessController
 * 
 * @author zealot
 */
public class SecurityContextTest extends TestCase {

	/**
	 * PrivilegedCallable:{@link java.util.concurrent.Executors.PrivilegedCallable<T>}
	 * {@link java.util.concurrent.Executors.PrivilegedCallableUsingCurrentClassLoader<T>}
	 * {@link java.util.concurrent.Executors.RunnableAdapter<T>}
	 * {@link java.util.concurrent.Executors.PrivilegedThreadFactory}
	 * 
	 * @throws Exception
	 */
	public void testAccessControlContext() throws Exception {
		AccessControlContext acc = AccessController.getContext();

		String result = execute(acc, () -> {
			return "Hello World.";
		});
		System.out.println("result:" + result);
	}

	/**
	 * 使用权限访问执行 Callable 方法，防止运行时没有权限访问而抛出异常
	 * 
	 * @param acc
	 *            AccessControlContext
	 * @param task
	 *            Callable task
	 * @return
	 * @throws Exception
	 */
	public <T> T execute(AccessControlContext acc, Callable<T> task) throws Exception {
		try {
			PrivilegedExceptionAction<T> action = (PrivilegedExceptionAction<T>) () -> task.call();
			return AccessController.doPrivileged(action, acc);
		} catch (PrivilegedActionException e) {
			throw e.getException();
		}
	}

}
