package com.apusic.sicp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
    public static final Log log = LogFactory.getLog(DateUtil.class);

    public static final String STANDARD_FORMAT = "yyyy-MM-dd";
    public static final String PRECISE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MOST_PRECISE_FORMAT = "yyyyMMddHHmmssSSS";

    public static String format(java.util.Date date) {
        if (date != null) {
            return new SimpleDateFormat(STANDARD_FORMAT).format(date);
        }
        return "";
    }

    public static String formatDate(java.util.Date date) {
        return format(date);
    }

    public static String formatDateMostPrecise(java.util.Date date) {
        if (date != null) {
            return new SimpleDateFormat(MOST_PRECISE_FORMAT).format(date);
        }
        return "";
    }

    public static String formatTime(java.util.Date date) {
        return formatPrecise(date);
    }

    public static String formatPrecise(java.util.Date date) {
        if (date != null) {
            return new SimpleDateFormat(PRECISE_FORMAT).format(date);
        }
        return "";
    }

    public static Date parseDate(String s) throws ParseException {
        if (StringUtils.isNotBlank(s)) {
            return new SimpleDateFormat(PRECISE_FORMAT).parse(s);
        }
        return null;
    }

    public static Date parse(String s) throws ParseException {
        if (StringUtils.isNotBlank(s)) {
            return new SimpleDateFormat(STANDARD_FORMAT).parse(s);
        }
        return null;
    }

    public static Date getDateAfterDays(Date date, int days) {
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
     * 获取昨天，如：今天为：2011-12-29 10:47:00，则返回2011-12-28 00:00:00
     * @return
     */
    public static Date getYesterday(){
        Date d = DateUtils.addDays(new Date(), -1);
        return DateUtils.truncate(d, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取[start,end]之间的所有日期
     * 如果start == null && end != null,则返回end
     * 如果start != null && end == null,则返回[start,yesterday]之间的所有日期
     * 如果start == null && end == null,则返回yesterday
     * 如果start after end,则返回yesterday
     * @param start
     * @param end
     * @return
     */
    public static List<Date> getDateSegments(Date start, Date end) {
        if (end == null) {
            end = getYesterday();
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
