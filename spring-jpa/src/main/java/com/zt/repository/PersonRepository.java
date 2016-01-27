package com.zt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zt.entity.Person;

/**
 * 1. interface PersonRepository extends JpaRepository<Person, Long> : The
 * Person Repository.<br />
 * 
 * 2. 使用 @Repository 注解将其交给 Spring 容器管理.<br />
 * 
 * @author a
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	/**
	 * To find Person by email
	 * 
	 * @param email
	 * @return
	 */
	public Person findByEmail(String email);

	/**
	 * To find persons by lastName
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Person> findByLastName(String lastName);
}
