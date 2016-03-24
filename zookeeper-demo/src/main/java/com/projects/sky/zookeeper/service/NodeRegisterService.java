package com.projects.sky.zookeeper.service;

import java.util.Collection;

import com.projects.sky.zookeeper.entity.Node;

/**
 * 
 * 
 * @author zengtao
 * @date 2016年1月5日
 */
public interface NodeRegisterService {
	/**
	 * 获取所有节点
	 * 
	 * @return
	 */
	Collection<Node> findAll();

	/**
	 * 注册节点
	 * 
	 * @param node
	 * @return
	 */
	void add(Node node);

	/**
	 * 获取下级节点
	 * 
	 * @param node
	 * @return
	 */
	Collection<Node> getChildren(Node node);

	/**
	 * 获取上级节点
	 * 
	 * @param node
	 * @return
	 */
	Node getParent(Node node);

	/**
	 * 获取code对应的节点
	 * 
	 * @param code
	 * @return
	 */
	Node getNode(String code);

	/**
	 * 删除指定的节点，如果节点存在服务或机器则删除失败
	 * 
	 * @param node
	 */
	void deleteNode(Node node);

	/**
	 * 更新节点信息
	 * 
	 * @param node
	 */
	void updateNode(Node node);
}
