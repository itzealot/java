package com.sky.projects.spark.auth;

import java.io.Serializable;

import org.apache.spark.streaming.api.java.JavaDStream;

/**
 * 认证数据抽取接口
 * 
 * @author zt
 *
 */
public interface Authable extends Serializable {

	/**
	 * 抽取认证数据并返回
	 * 
	 * @param source
	 * @return
	 */
	public JavaDStream<String[]> extractAuthData(JavaDStream<String[]> source);
}
