package com.apusic.projects.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apusic.projects.gateway.entity.User;
import com.apusic.projects.gateway.ws.service.IUserService;

/**
 * 注解 @EnableAutoConfiguration 申明让 spring boot 自动给程序进行必要的配置；
 * 
 * 注解 @RequestMapping 修饰类表明需要在项目url前加 上 相应的REST Full的名词
 * 
 * 注解 @RestController 表明类为可添加 REST Full 的Controller
 * 
 * @author a
 *
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private IUserService userService;

	/**
	 * 注解 @RequestMapping 修饰方法，指明REST Full 的url
	 * 
	 * 注解 @PathVariable 指明需要注入的值
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}")
	public User view(@PathVariable("id") Long id) {
		return userService.get(id);
	}

}