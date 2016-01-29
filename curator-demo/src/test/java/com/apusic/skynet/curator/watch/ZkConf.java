package com.apusic.skynet.curator.watch;

public class ZkConf {
	private final String path;
	private final String uid;
	private final String pwd;
	private final int perms;

	public ZkConf(String path, String uid, String pwd, int perms) {
		super();
		this.path = path;
		this.uid = uid;
		this.pwd = pwd;
		this.perms = perms;
	}

	public String getPath() {
		return path;
	}

	public String getUid() {
		return uid;
	}

	public String getPwd() {
		return pwd;
	}

	public int getPerms() {
		return perms;
	}

}