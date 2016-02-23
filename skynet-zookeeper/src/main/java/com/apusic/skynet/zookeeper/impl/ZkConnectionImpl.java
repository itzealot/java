package com.apusic.skynet.zookeeper.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;

import com.apusic.skynet.zookeeper.ZkPath;
import com.apusic.skynet.zookeeper.ZkPath.PathFilter;
import com.apusic.skynet.zookeeper.common.Commons;
import com.apusic.skynet.zookeeper.ZkRoot;
import com.google.common.collect.Lists;

/**
 * ZkConnection
 *
 * @author zt
 *
 */
public final class ZkConnectionImpl extends AbstractZkConnection {

	CuratorFramework client;

	public ZkConnectionImpl(CuratorFramework client) {
		this.client = client;
	}

	@Override
	public void close() {
		if (client != null) {
			client.close();
		}
	}

	@Override
	public ZkRoot createRoot() {
		return new ZkRootImpl(this);
	}

	@Override
	public boolean exists(ZkPathImpl path) {
		try {
			return client.checkExists().forPath(path.getPath()) != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(ZkPathImpl path) {

		if (!exists(path)) {
			return false;
		}

		try {
			client.delete().forPath(path.getPath());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ZkPath> children(ZkPathImpl path) {

		List<ZkPath> paths = Lists.newArrayList();

		try {
			List<String> childrens = client.getChildren().forPath(path.getPath());
			String base = path.getPath();
			if (Commons.ROOT_PATH.equals(base)) {
				base = Commons.BASE;
			}
			for (String str : childrens) {
				String p = base + Commons.ROOT_PATH + str;
				paths.add(new ZkPathImpl(this, p));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paths;
	}

	@Override
	public List<ZkPath> children(ZkPathImpl path, PathFilter filter) {

		List<ZkPath> childrens = children(path);
		List<ZkPath> paths = Lists.newArrayList();

		for (ZkPath p : childrens) {
			if (filter.accpet(p.getPath())) {
				paths.add(p);
			}
		}

		return paths;
	}

	@Override
	public boolean mkdir(ZkPathImpl path) {
		try {
			client.create().forPath(path.getPath());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean mkdirs(ZkPathImpl path) {
		try {
			client.create().creatingParentsIfNeeded().forPath(path.getPath());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public <T extends Serializable> T getData(ZkPathImpl path) {
		if (!exists(path)) {
			return null;
		}

		try {
			return Commons.read(client.getData().forPath(path.getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public <T extends Serializable> ZkPath setData(ZkPathImpl path, T data) {
		// 不存在则先创建
		if (!exists(path)) {
			mkdirs(path);
		}

		try {
			client.setData().forPath(path.getPath(), Commons.serialize(data));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ZkPathImpl(this, path.getPath());
	}

	@Override
	public CuratorFramework getClient() {
		return client;
	}

}
