package com.zt.util;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 按时间生成表名称的工具类
 * 
 * @author zengtao
 *
 */
public class TableNameUtil {

	/**
	 * 生成日志表名称:logs_year_month.<br />
	 * offset是偏移量:the value about the current month
	 * 
	 * @param offset
	 *            the value about the current month
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		// the singleton Calendar
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0-11 + 1 = 1 - 12
		int month = c.get(Calendar.MONTH) + 1;

		// 加偏移量
		month += offset;
		if (month > 12) {
			year++;
			// or month -= 12
			month = month - 12;
		}
		if (month < 1) {
			year--;
			month = month + 12;
		}
		DecimalFormat format = new DecimalFormat();
		// 使用格式即两位，不够高位补0
		format.applyPattern("00");
		return "logs_" + year + "_" + format.format(month);
	}

	/**
	 * To generate table name like tableName_year_month<br />
	 * 根据tableName与offset生成表名称.<br />
	 * tableName是表的名称，offset是偏移量<br />
	 * 
	 * @param tableName
	 *            the tableName you want you generate
	 * @param offset
	 *            the offset the input
	 * @return
	 */
	public static String generateTableName(String tableName, int offset) {
		// the singleton Calendar
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0-11 + 1 = 1 - 12
		int month = c.get(Calendar.MONTH) + 1;

		// 加偏移量.To add offset
		month += offset;
		if (month > 12) {
			year++;
			// or month -= 12
			month = month - 12;
		}
		if (month < 1) {
			year--;
			month = month + 12;
		}
		DecimalFormat format = new DecimalFormat();
		// 使用格式即两位，不够高位补0. To format the number
		format.applyPattern("00");
		return tableName + "_" + year + "_" + format.format(month);
	}
}
