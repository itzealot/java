package com.sky.projects.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sky.projects.spring.dao.BookShopDao;
import com.sky.projects.spring.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookShopDao bookShopDao;

	// 添加事务注解
	// 1.使用propagation 指定事务的传播行为，即当前的事务方法被另一个事务方法调用时，
	// 如何使用事务，默认值为Propagation.REQUIRED，即使用调用方法的事务。（事务捆绑，一起执行）
	// @Transactional(propagation=Propagation.REQUIRED)
	// (propagation=Propagation.REQUIRES_NEW): 使用自己的事务，调用事务方法的事务被挂起。

	// 2.使用isolation 指定事务的隔离级别最常用的取值为READ_COMMITTED

	// 3.默认情况下Spring的声明时事务对所有的运行时异常进行回滚，也可以通过对应的属性进行配置。
	// noRollbackFor={UserAccountException.class}//对哪些异常不进行回滚
	// 通常情况下取默认值即可。

	// 4.使用readOnly 指定事务只读属性。（只读取不更新数据，则为true，否则为false）
	// 这样可以帮助数据库引擎优化事务，若真的是一个只读取的方法，应该设置为true

	// 5.使用timeout 指定强制回滚之前事务可以占用的时间，单位秒。
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void purchase(String username, String isbn) {
		// 1.获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);

		// 2.更新书的库存
		bookShopDao.updateBookStock(isbn);

		// 3.更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}
}
