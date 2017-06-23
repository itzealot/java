package com.sky.projects.jdk.lambda;

/**
 * 接口中含有异常
 * 
 * @author zealot
 *
 */
@FunctionalInterface
interface InterfaceWithException {

	void apply(int i) throws Exception;
}