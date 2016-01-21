package com.apusic.skynet.zookeeper.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apusic.skynet.zookeeper.api.ZkPath;
import com.apusic.skynet.zookeeper.api.ZkRoot;
import com.google.common.collect.Lists;

public class ZkConnectionCommons {
	private static final Logger log = LoggerFactory.getLogger(ZkConnectionCommons.class);

	/**
	 * 是否存在 ZkPath 对象
	 * 
	 * @param path
	 * @return
	 */
	public static boolean exists(ZkPath path) {
		try {
			return get(path).checkExists().forPath(path.getPath()) != null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("path is exist error, path is: {}, {}", path.getPath(), e);
		}

		return false;
	}

	/**
	 * 通过 ZkPath对象 获取数据
	 * 
	 * @param path
	 * @return
	 */
	public static <T extends Serializable> T getData(ZkPath path) {
		return getDataUsingWatcher(path, null);
	}

	/**
	 * 通过 ZkPath对象 获取数据，并使用 CuratorWatcher
	 * 
	 * @param path
	 * @param watcher
	 * 
	 * @return
	 */
	public static <T extends Serializable> T getDataUsingWatcher(ZkPath path, CuratorWatcher watcher) {
		if (!exists(path)) {
			return null;
		}

		try {
			if (watcher == null) {
				return Commons.read(get(path).getData().forPath(path.getPath()));
			}

			return Commons.read(get(path).getData().usingWatcher(watcher).forPath(path.getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("get Data Using Watcher error, path is {}, {}", path.getPath(), e);
		}

		return null;
	}

	/**
	 * 给 ZkPath 对象设置数据
	 * 
	 * @param path
	 * @param data
	 */
	public static <T extends Serializable> void setData(ZkPath path, T data) {
		createAndSet(path, data);
	}

	/**
	 * 创建 ZkPath 对象并设置数据
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public static <T extends Serializable> ZkPath createAndSet(ZkPath path, T data) {
		if (!exists(path)) {
			makedir(path);
		}

		checkNotNull(data, "data can not be null");

		try {
			get(path).setData().forPath(path.getPath(), Commons.serialize(data));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("setData error, path is: {}, data is: {}, {}", path.getPath(), data, e);
		}
		return path;
	}

	/**
	 * 递归删除 ZkPath对象
	 * 
	 * @param path
	 */
	public static void delete(ZkPath path) {
		if (!exists(path)) {
			return;
		}

		try {
			get(path).delete().deletingChildrenIfNeeded().forPath(path.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("delete path error, path is: {}, {}", path.getPath(), e);
		}
	}

	/**
	 * 创建 ZkPath 目录
	 * 
	 * @param path
	 * @return
	 */
	public static ZkPath makedir(ZkPath path) {
		if (exists(path)) {
			return path;
		}

		try {
			get(path).create().creatingParentsIfNeeded().forPath(path.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("makedir error, path is: {}, {}", path.getPath(), e);
		}
		return path;
	}

	/**
	 * To create with Mode {@link CreateMode}
	 * 
	 * @param path
	 * @param mode
	 * @return
	 */
	public static ZkPath createWithMode(ZkPath path, CreateMode mode) {
		if (exists(path)) {
			return path;
		}

		try {
			get(path).create().creatingParentsIfNeeded().withMode(mode).forPath(path.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("makedir error, path is: {}, {}", path.getPath(), e);
		}

		return path;
	}

	/**
	 * To create with ACL {@link ACL}
	 * 
	 * @param path
	 * @param mode
	 * @return
	 */
	public static ZkPath createWithACL(ZkPath path, List<ACL> aclList) {
		if (exists(path)) {
			return path;
		}

		try {
			get(path).create().creatingParentsIfNeeded().withACL(aclList).forPath(path.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("makedir error, path is: {}, {}", path.getPath(), e);
		}

		return path;
	}

	/**
	 * To create with Mode {@link CreateMode} and ACL {@link ACL}
	 * 
	 * @param path
	 * @param mode
	 * @return
	 */
	public static ZkPath createWithModeAndACL(ZkPath path, CreateMode mode, List<ACL> aclList) {
		if (exists(path)) {
			return path;
		}

		try {
			get(path).create().creatingParentsIfNeeded().withMode(mode).withACL(aclList).forPath(path.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("makedir error, path is: {}, {}", path.getPath(), e);
		}

		return path;
	}

	/**
	 * 获取 ZkPath 对象所有的孩子目录名称并存入 {@link List<String>} 中
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> children(ZkPath path) {
		if (!exists(path)) {
			return Lists.newArrayList();
		}

		try {
			return get(path).getChildren().forPath(path.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("get childrens error, path is: {}, {}", path.getPath(), e);
		}

		return Lists.newArrayList();
	}

	private static CuratorFramework get(ZkPath path) {
		checkNotNull(path, "ZkPath object can not be null");
		checkNotNull(path.getPath(), "ZkPath object's path can not be null");
		checkNotNull(path.getConnection(), "ZkPath object's connection can not be null");

		return path.getConnection().getClient();
	}

	public static ZkRoot getRoot(ZkPath path) {
		return path.getConnection().getRoot();
	}
}
