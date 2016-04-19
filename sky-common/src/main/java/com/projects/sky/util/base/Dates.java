package com.projects.sky.util.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

public final class Dates {

	static enum DateFormat {
		// format date like 2016-01-01 18:00:00, for 24 小时制
		DEFAULT_FORMAT("yyyy-MM-dd HH:mm:ss"),

		// format date like 2016-01-01 01:00:00 for, 12 小时制
		FORMAT_TO_HOUR_12("yyyy-MM-dd hh:mm:ss"),

		// yyyyMMddhhmmss
		FORMAT_TO_NO_SPILIT("yyyyMMddhhmmss"),

		// yyyy-MM-dd
		FORMAT_TO_DATE("yyyy-MM-dd"),

		// HH:mm:ss
		FORMAT_TO_TIME("HH:mm:ss");

		String format;

		DateFormat(String format) {
			this.format = format;
		}
	}

	/**
	 * 清除了时、分、秒、微秒
	 *
	 * @param date
	 * @return
	 */
	public static Date clearTime(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取昨天，如：今天为：2011-12-29 10:47:00，则返回2011-12-28 00:00:00
	 * 
	 * @return
	 */
	public static Date yesterday() {
		Date d = DateUtils.addDays(new Date(), -1);
		return DateUtils.truncate(d, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取[start, end]之间的所有日期.<br />
	 * 如果start == null && end != null,则返回end.<br />
	 * 如果start != null && end == null,则返回[start,yesterday]之间的所有日期.<br />
	 * 如果start == null && end == null,则返回yesterday.<br />
	 * 如果start after end,则返回yesterday.<br />
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> between(Date start, Date end) {
		if (end == null) {
			end = yesterday();
		}

		end = DateUtils.truncate(end, Calendar.DAY_OF_MONTH);

		if (start == null) {
			return Arrays.asList(end);
		}

		if (start.after(end)) {
			return Arrays.asList(end);
		}

		List<Date> dateList = new ArrayList<Date>();
		start = DateUtils.truncate(start, Calendar.DAY_OF_MONTH);
		dateList.add(start);

		do {
			start = DateUtils.addDays(start, 1);
			dateList.add(start);
		} while (start.before(end));

		return dateList;
	}
	
	public static Date after(Date date, int days) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);

		return calendar.getTime();
	}
	
	public static Date add(Date date, int offset) {
		checkNotNull(date, "date must not be null");

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		// 整数往后推,负数往前移动
		calendar.add(Calendar.DATE, offset);

		return calendar.getTime();
	}

	public static boolean between(Date start, Date source, Date end) {
		checkNotNull(source, "source date must not be null");

		if (source.after(start) && source.before(end)) {
			return true;
		}

		return false;
	}

	public static boolean between(long start, long source, long end) {
		if (source > start && source < end) {
			return true;
		}

		return false;
	}

	public static SimpleDateFormat get(String format) {
		checkNotNull(format, "format String must not be null");

		return new SimpleDateFormat(format);
	}

	public static String format(String format, Date date) {
		checkNotNull(date, "date must not be null");

		return get(format).format(date);
	}

	public static String format(DateFormat format, Date date) {
		return format(format.format, date);
	}

	public static String format(Date date) {
		return format(DateFormat.DEFAULT_FORMAT, date);
	}

	public static String format2Time(Date date) {
		return format(DateFormat.FORMAT_TO_TIME, date);
	}

	public static String format2Date(Date date) {
		return format(DateFormat.FORMAT_TO_DATE, date);
	}

	public static String format2NoSpilit(Date date) {
		return format(DateFormat.FORMAT_TO_NO_SPILIT, date);
	}

	public static String format2HourSmall(Date date) {
		return format(DateFormat.FORMAT_TO_HOUR_12, date);
	}

	public static Date of(String source, String format) {
		checkNotNull(format, "format String must not be null");
		checkNotNull(source, "source String must not be null");

		try {
			return get(format).parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date of(String source) {
		return of(source, DateFormat.DEFAULT_FORMAT);
	}

	public static Date of(String source, DateFormat format) {
		return of(source, format.format);
	}

	public static Date ofDate(String source) {
		return of(source, DateFormat.FORMAT_TO_DATE);
	}

	public static Date ofTime(String source) {
		return of(source, DateFormat.FORMAT_TO_TIME);
	}

	public static Date ofHourSmall(String source) {
		return of(source, DateFormat.FORMAT_TO_HOUR_12);
	}

	public static Date ofNoSpilit(String source) {
		return of(source, DateFormat.FORMAT_TO_NO_SPILIT);
	}

	private Dates() {
	}

}
