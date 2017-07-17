/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sky.projects.jdk.loadbalance;

import java.util.Map;

/**
 * Invocation. (API, Prototype, NonThreadSafe)
 * 
 * 调用信息
 * 
 * @serial Don't change the class name and package name.
 * @see com.alibaba.dubbo.rpc.Invoker#invoke(Invocation)
 * @see com.alibaba.dubbo.rpc.RpcInvocation
 * @author qian.lei
 * @author william.liangf
 */
public interface Invocation {

	/**
	 * get method name.获取调用的方法名称
	 * 
	 * @serial
	 * @return method name.
	 */
	String getMethodName();

	/**
	 * get parameter types.获取调用的参数类型
	 * 
	 * @serial
	 * @return parameter types.
	 */
	Class<?>[] getParameterTypes();

	/**
	 * get arguments.获取调用的参数列表
	 * 
	 * @serial
	 * @return arguments.
	 */
	Object[] getArguments();

	/**
	 * get attachments.获取附属信息Map
	 * 
	 * @serial
	 * @return attachments.
	 */
	Map<String, String> getAttachments();

	/**
	 * get attachment by key.
	 * 
	 * @serial
	 * @return attachment value.
	 */
	String getAttachment(String key);

	/**
	 * get attachment by key with default value.
	 * 
	 * @serial
	 * @return attachment value.
	 */
	String getAttachment(String key, String defaultValue);

}