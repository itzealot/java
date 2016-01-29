package com.zt.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 1、spring的@Scheduled注解 需要写在实现上、
 * 
 * 2、 定时器的任务方法不能有返回值（如果有返回值，spring初始化的时候会告诉你有个错误、需要设定一个
 * proxytargetclass的某个值为true、 具体就去百度google吧）
 * 
 * 3、实现类上要有组件的注解@Component
 * 
 * @author zt
 *
 */
@Component
public class MyTestServiceImpl implements IMyTestService {
	/**
	 * 格式: [秒] [分] [小时] [日] [月] [周] [年]
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void myTest() {
		System.out.println("进入测试");
	}
}