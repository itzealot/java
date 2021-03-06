package com.sky.projects.jdk.time;

import java.time.LocalTime;
import java.time.ZoneId;

/**
 * java.time.LocalTime：LocalTime是一个不可变的类，
 * 
 * 它的实例代表一个符合人类可读格式的时间，默认格式是hh:mm:ss.zzz
 * 
 * 像LocalDate一样，该类也提供了时区支持，同时也可以传入小时、分钟和秒等输入参数创建实例
 * 
 * @author Administrator
 *
 */
public class LocalTimeExample {

	public static void main(String[] args) {
		// Current Time=hh:mm:ss.zzz
		LocalTime time = LocalTime.now();
		System.out.println("Current Time=" + time);

		// Creating LocalTime by providing input arguments
		// Specific Time of Day=12:20:25.000000040
		LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
		System.out.println("Specific Time of Day=" + specificTime);

		// Try creating time by providing invalid inputs
		// LocalTime invalidTime = LocalTime.of(25,20);
		// Exception in thread "main" java.time.DateTimeException:
		// Invalid value for HourOfDay (valid values 0 - 23): 25

		// Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
		// Current Time in IST=20:21:37.644
		LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("Current Time in IST=" + timeKolkata);

		// java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
		// LocalTime todayIST = LocalTime.now(ZoneId.of("IST"));

		// Getting date from the base date i.e 01/01/1970
		// 10000th second time= 02:46:40
		LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
		System.out.println("10000th second time= " + specificSecondTime);
	}

}