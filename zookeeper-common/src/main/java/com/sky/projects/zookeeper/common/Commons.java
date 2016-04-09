package com.sky.projects.zookeeper.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.curator.utils.PathUtils;

public final class Commons {

	private Commons() {
	}

	/**
	 * 拼接多个路径为完整路径
	 * 
	 * @param paths
	 * @return
	 */
	public static String join(String... paths) {
		checkNotNull(paths, "paths can not be null");

		StringBuffer buffer = new StringBuffer();

		int length = paths.length;
		for (int i = 0; i < length; i++) {
			if (paths[i] != null && !"".equals(paths[i]) && !"/".equals(paths[i]))
				buffer.append(paths[i].trim());
		}
		String path = buffer.toString();

		path = "".equals(path) ? "/" : path;

		// 校验 path 是否是合法的 path
		checkPath(path);

		return path;
	}

	/**
	 * 获取父路径
	 * 
	 * @param path
	 * @return
	 */
	public static String parentPath(String path) {
		checkPath(path);

		return getParentPath(path);
	}

	private static String getParentPath(String path) {
		String base = "/";

		if (base.equals(path)) {
			return null;
		}

		int index = path.lastIndexOf('/');

		if (index == 0) {
			return base;
		}

		return path.substring(0, index);
	}

	/**
	 * 获取所有的祖先路径，包含本身
	 * 
	 * @param path
	 * @return
	 */
	public static Collection<String> ancestorPath(String path) {
		checkPath(path);

		String current = getParentPath(path);

		Collection<String> paths = new ArrayList<>();
		paths.add(path);

		while (!"/".equals(current) && current != null) {
			paths.add(current);

			current = getParentPath(current);
		}

		return paths;
	}

	private static void checkPath(String path) {
		PathUtils.validatePath(path);
	}
}
