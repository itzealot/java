package com.zt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.dao.PersonDao;
import com.zt.entity.Person;
import com.zt.repository.PersonRepository;

/**
 * Service层 使用@Service 注解标示
 * 
 * Title: PersonService.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年8月29日
 */
@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;

	@Autowired
	PersonRepository personRepository;

	/**
	 * batch save persons.<br />
	 * 使用@Transactional 注解来添加事务处理
	 * 
	 * Title: savePersons.<br />
	 * Description: .<br />
	 * 
	 * @param persons
	 */
	@Transactional
	public void savePersons(List<Person> persons) {
		if (persons == null) {
			return;
		}
		int length = persons.size();
		for (int i = 0; i < length; i++) {
			personDao.save(persons.get(i));
		}
	}

	/**
	 * batch save persons.<br />
	 * 使用@Transactional 注解来添加事务处理
	 * 
	 * @param persons
	 */
	@Transactional
	public void savePersons(Person... persons) {
		if (persons == null) {
			return;
		}
		int length = persons.length;
		for (int i = 0; i < length; i++) {
			personRepository.save(persons[i]);
			System.out.println(10 / 0);
		}
	}
}
