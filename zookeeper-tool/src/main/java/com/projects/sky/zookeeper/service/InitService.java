package com.projects.sky.zookeeper.service;

public interface InitService {
	/**
	 * 从xml配置文件初始化组织机构
	 */
	void init(String xml);

	/**
	 * 清除注册表上所有的数据
	 */
	void clean();

	/**
	 * 查看注册表上的数据
	 */
	void view();

	/**
	 * 删除指定的数据
	 */
	void delete(String xml);

	/**
	 * 备份整个注册表
	 */
	void backup(String xml);

	/**
	 * 初始化根节点
	 */
	void initRoot();

}
