package com.sky.projects.spring.spi;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sky.projects.spring.entity.User;

@WebService
public interface UserService {

	@WebMethod
	public User get(Long id);

	@WebMethod
	public Collection<User> getAll();

}