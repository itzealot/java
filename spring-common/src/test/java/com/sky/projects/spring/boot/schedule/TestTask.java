package com.sky.projects.spring.boot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	// 格式: [秒] [分] [小时] [日] [月] [周] [年]
	// 每分钟执行一次任务。
	@Scheduled(cron = "0 0/1 * * * ?")
	public void test() {
		System.out.println("test................................");
	}

}