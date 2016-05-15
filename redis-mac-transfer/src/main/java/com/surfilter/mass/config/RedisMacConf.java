package com.surfilter.mass.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisMacConf {

	private static final Logger LOG = LoggerFactory.getLogger(RedisMacConf.class);

	private static RedisMacConf instance = new RedisMacConf();

	private String host = "localhost";
	private int port = 6379;
	private int oldMacPrefixLen = 5;
	private int newMacPrefixLen = 9;
	private int poolSize = 10;
	private String dealMacPartern = "m_*";

	private RedisMacConf() {
		MassConfiguration configuration = new MassConfiguration();

		host = configuration.get("redis.host");
		dealMacPartern = configuration.get("deal.mac.partern");
		port = configuration.getInt("redis.port");
		oldMacPrefixLen = configuration.getInt("old.mac.key.prefix.length");
		newMacPrefixLen = configuration.getInt("new.mac.key.prefix.length");
		poolSize = configuration.getInt("deal.mac.pool.size");

		LOG.debug("host: {}, port: {}, oldMacPrefixLen: {}, newMacPrefixLen: {}, poolSize: {}, dealMacPartern: {}", host, port,
				oldMacPrefixLen, newMacPrefixLen, poolSize, dealMacPartern);
	}

	public static RedisMacConf getInstance() {
		return instance;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public int getOldMacPrefixLen() {
		return oldMacPrefixLen;
	}

	public int getNewMacPrefixLen() {
		return newMacPrefixLen;
	}

	public int getPoolSize() {
		return poolSize;
	}
	
	public String getDealMacPartern() {
		return dealMacPartern;
	}
}
