package com.zt.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	// 格式: [秒] [分] [小时] [日] [月] [周] [年]
	@Scheduled(cron = "0/5 0 * * * ?")
	public void test() {
		System.out.println("test................................");
	}

}