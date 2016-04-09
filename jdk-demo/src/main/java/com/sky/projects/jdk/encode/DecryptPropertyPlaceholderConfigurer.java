package com.sky.projects.jdk.encode;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 配置文件des加密，然后读取配置文件时解密
 * 
 * @author a
 *
 */
public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	// 资源信息
	private Resource[] locations;

	/**
	 * 加载properties配置文科
	 * 
	 * @param props
	 * @throws IOException
	 */
	public void loadProperties(Properties props) throws IOException {
		if (this.locations != null) {
			PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

			// 遍历所有
			for (int i = 0; i < this.locations.length; i++) {

				// 所有的资源信息
				Resource location = this.locations[i];

				// 配置日志信息
				if (logger.isInfoEnabled()) {
					logger.info("Loading properties file from " + location);
				}
				InputStream is = null;
				try {

					// 通过locations 获取输入流
					is = location.getInputStream();
					BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					StringBuffer buffer = new StringBuffer();
					String line = "";

					// 行读入
					while ((line = in.readLine()) != null) {
						buffer.append(line);
					}
					String rs = "";

					// 是加密了的文件
					if ("dbconn.properties".equals(location.getFilename())) {

						// 加密解密的工具类
						DESUtil des = new DESUtil("1234567");

						// 解密工具类
						rs = des.decryptStr(buffer.toString());
					} else {
						rs = buffer.toString();
					}
					propertiesPersister.load(props, new ByteArrayInputStream(rs.getBytes()));
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		}
	}

	public Resource[] getLocations() {
		return locations;
	}

}