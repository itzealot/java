package com.projects.jdk.time;

import java.time.LocalTime;
import java.time.ZoneId;

/**
 * java.time.LocalTime：LocalTime是一个不可变的类，它的实例代表一个符合人类可读格式的时间，默认格式是hh:mm:ss.zzz。
 * 像LocalDate一样，该类也提供了时区支持，同时也可以传入小时、分钟和秒等输入参数创建实例，我们来看一个简单的程序，演示该类的使用方法。
 * 
 * @author Administrator
 *
 */
public class LocalTimeExample {

	public static void main(String[] args) {

		// Current Time
		LocalTime time = LocalTime.now();
		System.out.println("Current Time=" + time);

		// Creating LocalTime by providing input arguments
		LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
		System.out.println("Specific Time of Day=" + specificTime);

		// Try creating time by providing invalid inputs
		// LocalTime invalidTime = LocalTime.of(25,20);
		// Exception in thread "main" java.time.DateTimeException:
		// Invalid value for HourOfDay (valid values 0 - 23): 25

		// Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
		LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("Current Time in IST=" + timeKolkata);

		// java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
		// LocalTime todayIST = LocalTime.now(ZoneId.of("IST"));

		// Getting date from the base date i.e 01/01/1970
		LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
		System.out.println("10000th second time= " + specificSecondTime);

	}

}