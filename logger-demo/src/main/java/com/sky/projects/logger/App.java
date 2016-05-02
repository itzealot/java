package com.sky.projects.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		String msg = "Hello World!";

		// Level : TRACE < DEBUG < INFO < WARN < ERROR
		LOG.trace("logger trace print message is : {}", msg);

		LOG.debug("logger debug print message is : {}", msg);

		LOG.info("logger info print message is : {}", msg);

		LOG.warn("logger warn print message is : {}", msg);

		LOG.error("logger error print message is : {}", msg);
	}
}
