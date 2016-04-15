package com.sky.projects.redis.pipeline;

public class Result {
	private String mac;
	private String counts;

	public Result(String mac, String counts) {
		this.mac = mac;
		this.counts = counts;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		return mac + "\t\t" + counts;
	}
}
