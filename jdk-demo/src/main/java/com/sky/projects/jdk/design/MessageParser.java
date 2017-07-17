package com.sky.projects.jdk.design;

/**
 * MessageParser(ThreadSafe)
 * 
 * @author zealot
 */
public interface MessageParser {

	/**
	 * 获取标签数组，用于分类
	 * 
	 * @return
	 */
	String[] getLabels(String[] datas);

	/**
	 * get id
	 * 
	 * @return
	 */
	String getId(String[] datas);

	/**
	 * get json
	 * 
	 * @param datas
	 * @return
	 */
	String json(String[] datas);

	/**
	 * map values
	 * 
	 * @return
	 */
	String[] mapValues(String[] datas);

	/**
	 * data type
	 * 
	 * @return
	 */
	String getDataType(String[] datas);

}
