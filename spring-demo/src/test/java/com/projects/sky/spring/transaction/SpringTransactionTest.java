package com.projects.sky.spring.transaction;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.projects.sky.spring.dao.BookShopDao;
import com.projects.sky.spring.dao.CashierDao;
import com.projects.sky.spring.service.BookService;

public class SpringTransactionTest {

	private ApplicationContext applicationContext = null;
	private BookShopDao bookShopDao = null;

	private BookService bookService = null;

	private CashierDao cashierDao = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = applicationContext.getBean(BookShopDao.class);
		cashierDao = applicationContext.getBean(CashierDao.class);
	}

	@Test
	public void testTransactionPropagation() {
		// 事务默认的传播行为，事务进行捆绑
		cashierDao.checkout("AA", Arrays.asList("1001", "1002"));
	}

	@Test
	public void testBookShopService() {
		bookService = applicationContext.getBean(BookService.class);
		bookService.purchase("AA", "1001");
	}

	@Test
	public void testBookShopDaoUpdateUserAccount() {
		bookShopDao.updateUserAccount("AA", 200);
	}

	@Test
	public void testBookShopDaoFindPriceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}

	@Test
	public void testBookShopDaoUpdateBookStock() {
		bookShopDao.updateBookStock("1001");
	}
}
