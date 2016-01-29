package com.sky.projects.gateway;

import org.springframework.scheduling.annotation.Scheduled;

public class Task {
	@Scheduled(fixedRate = 2000)
	public void doTask() {
		System.out.println("do some task");
	}
}