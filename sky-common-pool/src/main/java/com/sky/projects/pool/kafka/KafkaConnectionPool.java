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
package com.sky.projects.pool.kafka;

import java.util.Properties;

import com.sky.projects.pool.ConnectionException;
import com.sky.projects.pool.ConnectionPool;
import com.sky.projects.pool.PoolBase;
import com.sky.projects.pool.PoolConfig;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

/**
 * Kakfa 连接池
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
public class KafkaConnectionPool extends PoolBase<Producer<byte[], byte[]>>
		implements ConnectionPool<Producer<byte[], byte[]>> {

	public KafkaConnectionPool() {
		this(KafkaConfig.DEFAULT_BROKERS);
	}

	public KafkaConnectionPool(final String brokers) {
		this(new PoolConfig(), brokers);
	}

	public KafkaConnectionPool(final Properties props) {
		this(new PoolConfig(), new ProducerConfig(props));
	}

	public KafkaConnectionPool(final ProducerConfig config) {
		this(new PoolConfig(), config);
	}

	public KafkaConnectionPool(final PoolConfig poolConfig, final Properties props) {
		this(poolConfig, new ProducerConfig(props));
	}

	public KafkaConnectionPool(final PoolConfig poolConfig, final String brokers) {
		this(poolConfig, brokers, KafkaConfig.DEFAULT_TYPE, KafkaConfig.DEFAULT_ACKS, KafkaConfig.DEFAULT_CODEC,
				KafkaConfig.DEFAULT_BATCH);
	}

	public KafkaConnectionPool(final PoolConfig poolConfig, final String brokers, final String type) {
		this(poolConfig, brokers, type, KafkaConfig.DEFAULT_ACKS, KafkaConfig.DEFAULT_CODEC, KafkaConfig.DEFAULT_BATCH);
	}

	public KafkaConnectionPool(final PoolConfig poolConfig, final ProducerConfig config) {
		super(poolConfig, new KafkaConnectionFactory(config));
	}

	public KafkaConnectionPool(final PoolConfig poolConfig, final String brokers, final String type, final String acks,
			final String codec, final String batch) {
		super(poolConfig, new KafkaConnectionFactory(brokers, type, acks, codec, batch));
	}

	@Override
	public Producer<byte[], byte[]> getConnection() throws ConnectionException {
		return super.getResource();
	}

	@Override
	public void returnConnection(Producer<byte[], byte[]> conn) throws ConnectionException {
		super.returnResource(conn);
	}

	@Override
	public void invalidateConnection(Producer<byte[], byte[]> conn) throws ConnectionException {
		super.invalidateResource(conn);
	}
}
