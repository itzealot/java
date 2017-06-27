package com.sky.projects.jdk.ac;

import java.io.Serializable;

/**
 * Simple interface for mapping bytes to States.
 * 
 * 子节点操作接口
 */
interface EdgeList<T> extends Serializable {

	/**
	 * 根据字节在当前节点的直接子节点查找，找到返回指向该节点的指针，否则返回 null
	 * 
	 * @param ch
	 * @return
	 */
	State<T> get(byte ch);

	/**
	 * 向当前节点添加加字节写入直接子节点列表中，并保存传入的节点指针
	 * 
	 * @param ch
	 * @param state
	 */
	void put(byte ch, State<T> state);

	/**
	 * 获取基于当前节点所有直接子节点存储字节组合成的字节数组
	 * 
	 * @return
	 */
	byte[] keys();

	/**
	 * clear temp reference
	 */
	void clear();
}
