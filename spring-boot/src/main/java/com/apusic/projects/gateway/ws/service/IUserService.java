package com.apusic.projects.gateway.ws.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.apusic.projects.gateway.entity.User;

/**
 * WebService 接口
 * 
 * @author a
 *
 */
@WebService
public interface IUserService {
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
