/*
 * Copyright 2015-2016 Dark Phoenixs (Open-Source Organization).
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
package com.sky.projects.pool;

import java.io.Serializable;

/**
 * 连接池接口
 * 
 * @author zealot
 *
 * @param <T>
 */
public interface ConnectionPool<T> extends Serializable {

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public T getConnection() throws ConnectionException;

	/**
	 * 还回连接到池中
	 * 
	 * @param conn
	 */
	public void returnConnection(T conn) throws ConnectionException;

	/**
	 * 废弃连接
	 * 
	 * @param conn
	 */
	public void invalidateConnection(T conn) throws ConnectionException;
}
