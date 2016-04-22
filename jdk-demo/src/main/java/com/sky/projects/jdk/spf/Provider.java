package com.sky.projects.jdk.spf;

import java.io.Serializable;

/**
 * 服务提供者框架的提供者注册API，由系统来注册实现
 * 
 * @author zt
 *
 */
public interface Provider extends Serializable {
	Service newService();
}
