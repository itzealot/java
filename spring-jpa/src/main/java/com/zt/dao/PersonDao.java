package com.zt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.zt.entity.Person;

/**
 * 
 * 
 * Title: PersonDao.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年8月29日
 */
@Repository
public class PersonDao {

	// 如何获取到和当前事务关联的 EntityManager 对象?
	// 通过 @PersistenceContext 注解来标记成员变量
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Person person) {
		entityManager.persist(person);
	}
}
