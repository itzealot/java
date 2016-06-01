package com.sky.projects.apache.common.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkyConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(SkyConfiguration.class);

	private Configuration config;
	private static final String DEFAUL_CONF = "/conf/conf.properties";

	public SkyConfiguration() {
		this(DEFAUL_CONF);
	}

	public SkyConfiguration(String conf) {
		try {
			config = new PropertiesConfiguration(System.getProperty("user.dir") + conf);
		} catch (ConfigurationException e) {
			LOG.error("read configure error, {}", e);
			new RuntimeException("read configure error", e);
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
		if (results == null)
			return defaluValue;

		StringBuffer buffer = new StringBuffer();
		int len = results.length;

		buffer.append(results[0]);

		for (int i = 1; i < len; i++) {
			buffer.append(spliter).append(results[i]);
		}

		return buffer.toString();
	}

	public String getStringsFromArrayWithSpliter(final String key) {
		return getStringsFromArrayWithSpliter(key, null, ",");
	}
}