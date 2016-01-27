package com.sky.projects.ws.demo.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sky.projects.ws.demo.model.User;

/**
 * WebService 接口
 * 
 * @author a
 *
 */
@WebService
public interface UserService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public User get(Long id);

	@WebMethod
	public Collection<User> getAll();

}