package com.sky.projects.spring.generic.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sky.projects.spring.generic.repository.BaseRepository;

public class BaseService<T> {

	@Autowired
	protected BaseRepository<T> repository;

	public void add() {
		System.out.println("add...");
		System.out.println(repository);
	}
}
