package com.zt.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class TestLoginAndLogout {

	@Test
	public void testHelloworld() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:com/zt/test/shiro.ini");

		// 2、得到SecurityManager 实例并绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到 Subject 及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}

	/**
	 * 自定义 Realm
	 */
	@Test
	public void testMyRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:com/zt/test/shiro-realm.ini");

		// 2、得到SecurityManager 实例并绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到 Subject 及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
			System.out.println("login success...");
		} catch (AuthenticationException e) {
			System.out.println("login failed...");
			// 5、身份验证失败
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}

	/**
	 * 数据库连接池
	 */
	@Test
	public void testDataSource() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:com/zt/test/shiro-jdbc-realm.ini");

		// 2、得到SecurityManager 实例并绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到 Subject 及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
			System.out.println("login success...");
		} catch (AuthenticationException e) {
			System.out.println("login failed...");
			// 5、身份验证失败
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}
}
