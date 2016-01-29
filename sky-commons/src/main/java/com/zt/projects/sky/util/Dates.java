package com.zt.projects.sky.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public final class Dates {

	private Dates() {
	}

	public static enum DateFormateType {
		DATE("yyyy-MM-dd"), DATETIME("yyyy-MM-dd HH:mm:ss"), MOST_DATETIME(
				"yyyyMMddHHmmssSSS");
		private String format;

		private DateFormateType(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

	}

	/**
	 * To format the date by DateFormateType
	 * 
	 * @param date
	 * @param type
	 * @return
	 */
	public static String format(Date date, DateFormateType type) {
		if (date != null) {
			return new SimpleDateFormat(type.format).format(date);
		}
		return "";
	}

	/**
	 * To parse date String to Date by DateFormateType
	 * 
	 * @param date
	 * @param type
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, DateFormateType type) {
		if (StringUtils.isNotBlank(date)) {
			try {
				return new SimpleDateFormat(type.format).parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		return null;
	}

	/**
	 * To get the Date after some days
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date after(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
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
	 * To get yesterday
	 * 
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
}
