package com.sky.projects.pool.hive;

public interface HiveConfig {
	
	/**
	 * Hive Driver Class
	 */
	public static final String DEFAULT_DRIVER_CLASS = "org.apache.hive.jdbc.HiveDriver";

	/**
	 * default hive url
	 */
	public static final String DEFAULT_HIVE_URL = "hive.url=jdbc:hive2://localhost:10000/default";

	/**
	 * default class property is: driverClass
	 */
	public static final String DRIVER_CLASS_PROPERTY = "driverClass";

	/**
	 * hive url property is: hiveUrl
	 */
	public static final String HIVE_URL_PROPERTY = "hiveUrl";
}
