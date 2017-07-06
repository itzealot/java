package com.sky.projects.jdk;

import java.net.URL;
import java.util.Properties;

public class MetaInfFileLoadTest {

	public static void main(String[] args) {
		String file = "/META-INF/test/test.properties";

		Properties props = new Properties();

		try {
			URL url = MetaInfFileLoadTest.class.getResource(file);

			if (url != null) {
				props.load(MetaInfFileLoadTest.class.getResourceAsStream(file));
			}
		} catch (Exception e) {
		}

		System.out.println("key=" + props.getProperty("key"));
	}
}
