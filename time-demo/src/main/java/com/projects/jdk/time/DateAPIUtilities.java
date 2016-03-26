package com.projects.jdk.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期API工具：我们早些时候提到过，大多数日期/时间API类都实现了一系列工具方法，如：加/减天数、周数、月份数，等等。
 * 还有其他的工具方法能够使用TemporalAdjuster调整日期，并计算两个日期间的周期。
 * 
 * @author Administrator
 *
 */
public class DateAPIUtilities {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();

		// Get the Year, check if it's leap year
		System.out.println("Year " + today.getYear() + " is Leap Year? " + today.isLeapYear());

		// Compare two LocalDate for before and after
		System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDate.of(2015, 1, 1)));

		// Create LocalDateTime from LocalDate
		System.out.println("Current Time=" + today.atTime(LocalTime.now()));

		// plus and minus operations
		System.out.println("10 days after today will be " + today.plusDays(10));
		System.out.println("3 weeks after today will be " + today.plusWeeks(3));
		System.out.println("20 months after today will be " + today.plusMonths(20));

		System.out.println("10 days before today will be " + today.minusDays(10));
		System.out.println("3 weeks before today will be " + today.minusWeeks(3));
		System.out.println("20 months before today will be " + today.minusMonths(20));

		// Temporal adjusters for adjusting the dates
		System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
		LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
		System.out.println("Last date of this year= " + lastDayOfYear);

		Period period = today.until(lastDayOfYear);
		System.out.println("Period Format= " + period);
		System.out.println("Months remaining in the year= " + period.getMonths());
	}
}