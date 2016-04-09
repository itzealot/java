package com.sky.projects.zookeeper.cache;

import java.util.Collection;

import com.sky.projects.zookeeper.entity.Node;
import com.sky.projects.zookeeper.entity.Service;

/**
 * 服务缓存接口
 * 
 * @author zt
 *
 */
public interface ZkServiceCachable {

	/**
	 * 根据节点获取可见范围内的服务
	 * 
	 * @param node
	 * @return
	 */
	public Collection<Service> getVisibleServicesByNode(Node node);

}
