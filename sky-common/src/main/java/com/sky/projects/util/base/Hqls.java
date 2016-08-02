package com.sky.projects.util.base;

import static com.sky.projects.util.base.Strings.simpleName;

/**
 * HibernateQueryLanguage Util
 * 
 * @author zt
 */
public class Hqls {

	public static String query(Class<?> clazz) {
		return "FROM " + simpleName(clazz) + " as t";
	}

	public static String query(Class<?> clazz, int limit) {
		return "FROM " + simpleName(clazz) + " AS t LIMIT " + limit;
	}

	public static String queryId(Class<?> clazz, String idName) {
		return "SELECT t." + idName + " FROM " + simpleName(clazz) + " AS t";
	}

	public static String queryId(Class<?> clazz, String idName, int limit) {
		return "SELECT t." + idName + " FROM " + simpleName(clazz) + " AS t LIMIT " + limit;
	}

	public static String count(Class<?> clazz) {
		return "SELECT COUNT(*) FROM " + simpleName(clazz) + " AS t";
	}

	private Hqls() {
	}
}
