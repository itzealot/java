package com.sky.projects.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AppTest {
	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		SpringApplication.run(ScheduledTasks.class, args);
	}
}