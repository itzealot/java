package com.projects.sky.util.conf;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Sky Configuration
 * 
 * @author zt
 */
public final class SkyConfiguration {

	private Configuration config;
	private static final String DEFAUL_CONF_PATH = "/conf/";
	private static final String DEFAUL_CONF_FILE = "conf.properties";
	private static final String DEFAUL_CONF = DEFAUL_CONF_PATH + DEFAUL_CONF_FILE;

	/**
	 * 运行方式存储在系统 System 中的 key
	 */
	public static final String SKY_CONFIG_MODEL_KEY = "sky.config.model";

	/**
	 * 用于产品的运行方式，默认方式，可使用 System.setProperty({@value #SKY_CONFIG_MODEL_KEY},
	 * {@value #SKY_CONFIG_MODEL_PRODUCT})
	 */
	public static final String SKY_CONFIG_MODEL_PRODUCT = "product";

	/**
	 * 用于测试的运行方式,需使用 System.setProperty({@value #SKY_CONFIG_MODEL_KEY},
	 * {@value #SKY_CONFIG_MODEL_TEST})
	 */
	public static final String SKY_CONFIG_MODEL_TEST = "test";

	// 从运行环境中获取设置的运行方式
	private static final String MODEL = System.getProperty(SKY_CONFIG_MODEL_KEY);

	private static final boolean MODEL_FLAG = MODEL == null || SKY_CONFIG_MODEL_PRODUCT.equals(MODEL);

	public SkyConfiguration() {
		String path = MODEL_FLAG ? (System.getProperty("user.dir") + DEFAUL_CONF) : DEFAUL_CONF_FILE;

		try {
			config = new PropertiesConfiguration(path);
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("load configuration error.", e);
		}
	}

	public SkyConfiguration(final String conf) {
		String path = MODEL_FLAG ? System.getProperty("user.dir") + conf : conf;

		try {
			config = new PropertiesConfiguration(path);
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("load configuration path error, conf=" + conf, e);
		}
	}

	public String get(String key) {
		return get(key, null);
	}

	public String get(String key, String defaultValue) {
		return this.config.getString(key);
	}

	public Integer getInt(String key) {
		return getInt(key, 0);
	}

	public Integer getInt(String key, int defaultValue) {
		return this.config.getInt(key, defaultValue);
	}

	public String[] getArray(String key) {
		return this.config.getStringArray(key);
	}

	public String getStringsFromArrayWithSpliter(final String key, final String defaluValue, final String spliter) {
		String[] results = getArray(key);
		if (results == null || results.length == 0)
			return defaluValue;

		StringBuffer buffer = new StringBuffer(results[0]);
		for (int i = 1, len = results.length; i < len; i++) {
			buffer.append(spliter).append(results[i]);
		}

		return buffer.toString();
	}

	public String getStringsFromArrayWithSpliter(final String key) {
		return getStringsFromArrayWithSpliter(key, null, ",");
	}
}