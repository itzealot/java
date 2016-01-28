package com.zt.test;

import java.util.Collection;

import com.zt.test.entity.Node;
import com.zt.test.entity.Service;

/**
 * 服务缓存接口
 * 
 * @author zt
 *
 */
public interface ZkServiceCachable extends ZkCachable<String, Service> {

	/**
	 * 根据节点获取可见范围内的服务
	 * 
	 * @param node
	 * @return
	 */
	public Collection<Service> getVisibleServicesByNode(Node node);

}
