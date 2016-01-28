package com.zt.test;

import java.util.Collection;

import com.zt.test.entity.Node;

/**
 * 节点缓存接口
 * 
 * @author zt
 *
 */
public interface ZkNodeCachable extends ZkCachable<String, Node> {

	/**
	 * 根据给予的节点获取父节点
	 * 
	 * @param t
	 * @return
	 */
	public Node getParent(Node node);

	/**
	 * 根据给予的节点获取所有的孩子节点
	 * 
	 * @param t
	 * @return
	 */
	public Collection<Node> getChildren(Node node);
}
