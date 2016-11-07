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

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.sky.projects.pool.ConnectionException;
import com.sky.projects.pool.ConnectionFactory;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

/**
 * Kafka 连接工厂
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
class KafkaConnectionFactory implements ConnectionFactory<Producer<byte[], byte[]>> {

	/** config */
	private final ProducerConfig config;

	public KafkaConnectionFactory(final ProducerConfig config) {
		this.config = config;
	}

	public KafkaConnectionFactory(final String brokers, final String type, final String acks, final String codec,
			final String batch) {
		Properties props = new Properties();

		props.setProperty(KafkaConfig.BROKERS_LIST_PROPERTY, brokers);
		props.setProperty(KafkaConfig.PRODUCER_TYPE_PROPERTY, type);
		props.setProperty(KafkaConfig.REQUEST_ACKS_PROPERTY, acks);
		props.setProperty(KafkaConfig.COMPRESSION_CODEC_PROPERTY, codec);
		props.setProperty(KafkaConfig.BATCH_NUMBER_PROPERTY, batch);

		this.config = new ProducerConfig(props);
	}

	public KafkaConnectionFactory(final Properties properties) {
		String brokers = properties.getProperty(KafkaConfig.BROKERS_LIST_PROPERTY);
		if (brokers == null)
			throw new ConnectionException("[" + KafkaConfig.BROKERS_LIST_PROPERTY + "] is required !");

		this.config = new ProducerConfig(properties);
	}

	@Override
	public PooledObject<Producer<byte[], byte[]>> makeObject() throws Exception {
		return new DefaultPooledObject<Producer<byte[], byte[]>>(this.createConnection());
	}

	@Override
	public void destroyObject(PooledObject<Producer<byte[], byte[]>> p) throws Exception {
		Producer<byte[], byte[]> producer = p.getObject();
		if (null != producer)
			producer.close();
	}

	@Override
	public boolean validateObject(PooledObject<Producer<byte[], byte[]>> p) {
		return null != p.getObject();
	}

	@Override
	public void activateObject(PooledObject<Producer<byte[], byte[]>> p) throws Exception {
		// TODO
	}

	@Override
	public void passivateObject(PooledObject<Producer<byte[], byte[]>> p) throws Exception {
		// TODO
	}

	@Override
	public Producer<byte[], byte[]> createConnection() throws Exception {
		return new Producer<byte[], byte[]>(config);
	}
}
