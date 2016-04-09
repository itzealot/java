package com.sky.projects.jdk.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParseFormatExample {

	public static void main(String[] args) {
		// Format examples
		LocalDate date = LocalDate.now();

		// default format
		System.out.println("Default format of LocalDate=" + date);

		// specific format
		System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

		LocalDateTime dateTime = LocalDateTime.now();
		// default format
		System.out.println("Default format of LocalDateTime=" + dateTime);
		// specific format
		System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
		System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

		Instant timestamp = Instant.now();
		// default format
		System.out.println("Default format of Instant=" + timestamp);

		// Parse examples
		// 那么系统会抛出java.time.DateTimeException，这是一种运行时异常，我们并不需要显式地捕获它。
		LocalDateTime dt = LocalDateTime.parse("27:Apr:2014 21:39::48",
				DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
		System.out.println("Default format after parsing = " + dt);
	}

}