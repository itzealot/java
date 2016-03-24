package com.projects.sky.zookeeper.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.ZkPath;
import com.projects.sky.zookeeper.ZkPath.ZkEventType;
import com.projects.sky.zookeeper.ZkPath.ZkWatch;

/**
 * ZkWatch 实现类
 * 
 * @author zt
 *
 */
public class ZkWatchImpl implements ZkWatch {

	private Logger logger = LoggerFactory.getLogger(ZkWatchImpl.class);

	@Override
	public void accept(ZkEventType type, ZkPath path, boolean isOnline) {

		// 获取数据
		path.loadData();
		Serializable data = path.getData();
		System.out.println("====================data is: " + data + "====================");

		switch (type) {
		case NodeDataChanged:
			logger.info("....................NodeDataChanged....................");
			System.out.println("....................NodeDataChanged....................");
			break;
		case NodeChildrenChanged:
			logger.info("....................NodeChildrenChanged....................");
			System.out.println("....................NodeChildrenChanged....................");

			break;
		case NodeDeleted:
			logger.info("....................NodeDeleted....................");
			System.out.println("....................NodeDeleted....................");

			break;
		case NodeCreated:
			logger.info("....................NodeCreated....................");
			System.out.println("....................NodeCreated....................");

			break;
		default:// None
			break;
		}
	}

}
