package com.projects.sky.util;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JodaTimeTest extends TestCase {
	public JodaTimeTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(JodaTimeTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	public void testDateTime() {
		DateTime time = DateTime.now();

		System.out.println(time);
		// 下面就是按照一点的格式输出时间
		System.out.println(time.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
		System.out.println(time.toString("dd-MM-yyyy HH:mm:ss"));
		System.out.println(time.toString("EEEE dd MMMM, yyyy HH:mm:ssa"));
		System.out.println(time.toString("MM/dd/yyyy HH:mm ZZZZ"));
		System.out.println(time.toString("MM/dd/yyyy HH:mm Z"));

		System.out.println(time.toString("yyyy/MM/dd HH:mm:ss EE"));
		System.out.println(time.toString("yyyy/MM/dd HH:mm:ss EE", Locale.ENGLISH));

		System.out.println(DateTime.parse("2012-12-21 23:22:45", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));

		// 减
		System.out.println(DateTime.now().minusYears(1));
		System.out.println(DateTime.now().minusDays(5));
		System.out.println(DateTime.now().minusHours(5));
		System.out.println(DateTime.now().minusMinutes(60));

		// 加
		System.out.println(DateTime.now().plusYears(1));
		System.out.println(DateTime.now().plusDays(5));
		System.out.println(DateTime.now().plusHours(5));
		System.out.println(DateTime.now().plusMinutes(60));
	}

	public void testDays() {
		LocalDate start = new LocalDate(2014, 01, 15);
		LocalDate end = new LocalDate(2013, 01, 15);

		// -365
		System.out.println(Days.daysBetween(start, end).getDays());
		System.out.println(Months.monthsBetween(end, start));
	}
}
