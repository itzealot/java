package com.zt.test;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class ClockTest {
	final Clock clock = Clock.systemUTC();

	@Test
	public void test() {
		// 第一个是Clock类，它通过指定一个时区，然后就可以获取到当前的时刻，日期与时间
		// Get the system clock as UTC offset
		System.out.println(clock.instant());
		System.out.println(clock.millis());
	}

	/**
	 * LocaleDate只持有ISO-8601格式且无时区信息的日期部分。<br />
	 * 相应的，LocaleTime只持有ISO-8601格式且无时区信息的时间部分 。<br />
	 * LocaleDate与LocalTime都可以从Clock中得到。
	 */
	@Test
	public void test2() {
		// Get the local date and local time
		final LocalDate date = LocalDate.now();
		final LocalDate dateFromClock = LocalDate.now(clock);

		System.out.println(date);
		System.out.println(dateFromClock);

		// Get the local date and local time
		final LocalTime time = LocalTime.now();
		final LocalTime timeFromClock = LocalTime.now(clock);

		System.out.println(time);
		System.out.println(timeFromClock);
	}

	/**
	 * LocaleDateTime把LocaleDate与LocaleTime的功能合并起来，它持有的是ISO-8601格式无时区信息的日期与时间。
	 */
	@Test
	public void test3() {
		// Get the local date/time
		final LocalDateTime datetime = LocalDateTime.now();
		final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

		System.out.println(datetime);
		System.out.println(datetimeFromClock);
	}

	/**
	 * 如果你需要特定时区的日期/时间，那么ZonedDateTime是你的选择。<br />
	 * 它持有ISO-8601格式具具有时区信息的日期与时间。
	 */
	@Test
	public void test4() {
		// Get the zoned date/time
		final ZonedDateTime zonedDatetime = ZonedDateTime.now();
		final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
		final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

		System.out.println(zonedDatetime);
		System.out.println(zonedDatetimeFromClock);
		System.out.println(zonedDatetimeFromZone);
	}

	/**
	 * 让我们看一下Duration类：在秒与纳秒级别上的一段时间。Duration使计算两个日期间的不同变的十分简单。
	 */
	@Test
	public void test5() {
		// Get duration between two dates
		final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
		final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

		// 两个日期2014年4月16号与2015年4月16号之间的过程
		final Duration duration = Duration.between(from, to);
		System.out.println("Duration in days: " + duration.toDays());
		System.out.println("Duration in hours: " + duration.toHours());
	}

	/**
	 * JavaScript引擎Nashorn.<br />
	 * 并且它们俩遵循相同的规则，允许Java与JavaScript相互调用。
	 * 
	 * @throws ScriptException
	 */
	@Test
	public void test6() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		System.out.println(engine.getClass().getName());
		System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
	}
}
