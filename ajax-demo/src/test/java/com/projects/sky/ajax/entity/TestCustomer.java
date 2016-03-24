package com.projects.sky.ajax.entity;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCustomer {

	@Test
	public void test() throws JsonProcessingException {
		Customer customer = new Customer("1001", "zhangsan", "2011-1-1", "YC");

		ObjectMapper mapper = new ObjectMapper();

		String result = mapper.writeValueAsString(customer);
		System.out.println(result);
	}
}
