package com.sky.projects.mock.easymock.service;

import org.easymock.EasyMock;

import com.sky.projects.mock.dao.UserDao;
import com.sky.projects.mock.entity.User;
import com.sky.projects.mock.service.impl.UserServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserServiceImplTest extends TestCase {

	public UserServiceImplTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(UserServiceImplTest.class);
	}

	public void testQuery() {
		User expectedUser = new User();
		expectedUser.setId(1001L);

		// 创建Mock对象
		UserDao mock = EasyMock.createMock(UserDao.class);

		// 录制Mock对象预期行为
		EasyMock.expect(mock.getById(1001L)).andReturn(expectedUser);

		// 重放Mock对象，测试时以录制的对象预期行为代替真实对象的行为
		EasyMock.replay(mock);

		UserServiceImpl service = new UserServiceImpl();
		service.setUserDao(mock);

		// 调用测试方法
		User user = service.query(1001L);

		// 断言测试结果
		assertEquals(expectedUser, user);

		// 验证 Mock 对象被调用
		EasyMock.verify(mock);
	}
}
