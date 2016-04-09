package com.sky.projects.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注解 @EnableAutoConfiguration 申明让 spring boot 自动给程序进行必要的配置；
 * 
 * 注解 @Controller 表示这个一个 Controller 类；
 *
 * @author a
 *
 */
@Controller
@EnableAutoConfiguration
public class App {

	/**
	 * @RequestMapping("/hello") 表示通过/hello可以访问的方法获取说是请求url；
	 * 
	 * @ResponseBody 表示将结果直接返回给调用着。
	 * @return
	 */
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello world!";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}