package com.projects.sky.zookeeper.register;

import java.util.List;

import org.apache.zookeeper.CreateMode;

/**
 * CommonRegister
 * 
 * @author zt
 * @date 2016年1月5日
 */
public interface CommonRegister {
	/**
	 * 创建带有数据的节点
	 * 
	 * @param t
	 * @param mode
	 * @param path
	 * @return
	 */
	<T> String create(T t, CreateMode mode, String... path);

	/**
	 * 创建不带数据的节点
	 * 
	 * @param mode
	 * @param path
	 * @return
	 */
	<T> String create(CreateMode mode, String... path);

	/**
	 * 删除节点
	 * 
	 * @param path
	 */
	boolean delete(String... path);

	/**
	 * 获取节点数据
	 * 
	 * @param path
	 * @return
	 */
	<T> T getData(String... path);

	/**
	 * 设置节点数据
	 * 
	 * @param t
	 * @param path
	 */
	<T> void setData(T t, String... path);

	/**
	 * 查找所有子节点
	 * 
	 * @param path
	 * @return
	 */
	List<String> list(String... path);

	/**
	 * 节点是否存在
	 * 
	 * @param path
	 * @return
	 */
	boolean exists(String... path);

}
