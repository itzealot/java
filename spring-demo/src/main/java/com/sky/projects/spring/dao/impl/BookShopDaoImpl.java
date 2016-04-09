package com.sky.projects.spring.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sky.projects.spring.dao.BookShopDao;
import com.sky.projects.spring.exception.BookStockException;

/**
 * 使用注解的方式配置bean
 * 
 * @author zengtao
 *
 */
@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	/**
	 * 使用注解的方式自动注入值
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int findBookPriceByIsbn(String isbn) {
		String sql = "SELECT price FROM Book WHERE isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
	}

	public void updateBookStock(String isbn) {
		// 验证书的库存是否足够，不够则抛出异常。
		String sql2 = "SELECT stock FROM book_stock WHERE isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql2, Integer.class, isbn);
		if (stock == 0) {
			throw new BookStockException("库存不足！");
		}

		String sql = "UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?";
		jdbcTemplate.update(sql, isbn);
	}

	public void updateUserAccount(String username, int price) {
		String sql2 = "SELECT balance FROM Account WHERE username = ?";

		int balance = jdbcTemplate.queryForObject(sql2, Integer.class, username);

		if (balance < price) {
			throw new BookStockException("金额不足！");
		}

		String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
		jdbcTemplate.update(sql, price, username);
	}

}
