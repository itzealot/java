package com.zt.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	@Scheduled(cron = "0/5 * * * * ?")
	public void test() {
		System.out.println("test................................");
	}

}