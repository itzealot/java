package com.surfilter.mass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.surfilter.mass.mac.RedisMacTransfer;

/**
 * 单线程执行 Redis mac key 程序入口
 * 
 * @author zt
 *
 */
public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		LOG.debug("deal mac start..........");

		RedisMacTransfer transfer = new RedisMacTransfer();
		transfer.execute();

		long end = System.currentTimeMillis();
		LOG.debug("deal mac end..........");

		LOG.debug("the deal time is : " + (end - start) + " ms");
	}
}
