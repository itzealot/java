package com.apusic.skynet.zookeeper.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorTempFramework;

import com.apusic.skynet.zookeeper.ZkPath;
import com.apusic.skynet.zookeeper.ZkPath.PathFilter;
import com.apusic.skynet.zookeeper.ZkRoot;

public class ZkTempConnectionImpl extends AbstractZkConnection {

	CuratorTempFramework tempClient;

	public ZkTempConnectionImpl(CuratorTempFramework curatorTempFramework) {
		tempClient = curatorTempFramework;
	}

	public void close() {
		if (tempClient != null) {
			tempClient.close();
		}
	}

	public ZkRoot createRoot() {
		return null;
	}

	@Override
	public boolean exists(ZkPathImpl path) {
		return false;
	}

	@Override
	public boolean delete(ZkPathImpl path) {
		try {
			tempClient.inTransaction().delete().forPath(path.getPath()).and().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ZkPath> children(ZkPathImpl path) {
		return null;
	}

	@Override
	public List<ZkPath> children(ZkPathImpl path, PathFilter filter) {
		return null;
	}

	@Override
	public boolean mkdir(ZkPathImpl path) {
		return false;
	}

	@Override
	public boolean mkdirs(ZkPathImpl path) {
		return false;
	}

	@Override
	public <T extends Serializable> T getData(ZkPathImpl path) {
		return null;
	}

	@Override
	public <T extends Serializable> ZkPath setData(ZkPathImpl path, T data) {
		return null;
	}

	@Override
	public CuratorFramework getClient() {
		return null;
	}

}
