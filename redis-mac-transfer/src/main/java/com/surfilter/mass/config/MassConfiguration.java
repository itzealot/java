package com.surfilter.mass.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MassConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(MassConfiguration.class);
	
	private Configuration config;
	private static final String CONFIG_DIR_NAME = "/conf";
	private static final String CONFIG_FILE_NAME = "/conf.properties";

	public MassConfiguration() {
		try {
			// for linux
			String userPath = System.getProperty("user.dir");
			String configPath = userPath + CONFIG_DIR_NAME;
			config = new PropertiesConfiguration(configPath + CONFIG_FILE_NAME);
			
			// for eclipse
//			config = new PropertiesConfiguration("conf.properties");
		} catch (ConfigurationException e) {
			LOG.error("read configure error, {}", e);
			new RuntimeException("read configure error");
		}
	}

	public String get(String key) {
		return this.config.getString(key);
	}

	public Integer getInt(String key) {
		return this.config.getInt(key);
	}

	public String[] getArray(String key) {
		return this.config.getStringArray(key);
	}

	public String getStrings(String key) {
		String[] results = getArray(key);
		if (results == null || results.length == 0)
			return null;

		StringBuilder stringBuilder = new StringBuilder();
		
		if (results != null && results.length > 0) {
			for (int i = 0; i < results.length; i++) {
				if (i == results.length - 1) {
					stringBuilder.append(results[i]);
				} else {
					stringBuilder.append(results[i]).append(",");
				}
			}
		}

		return stringBuilder.toString();
	}
}