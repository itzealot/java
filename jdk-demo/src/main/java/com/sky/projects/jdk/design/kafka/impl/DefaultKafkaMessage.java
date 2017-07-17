package com.sky.projects.jdk.design.kafka.impl;

import java.util.concurrent.atomic.AtomicLong;

import com.sky.projects.jdk.design.kafka.KafkaMessage;


/**
 * DefaultKafkaMessage
 * 
 * @author zealot
 */
public class DefaultKafkaMessage implements KafkaMessage {

	private static final AtomicLong AL = new AtomicLong(1);

	// 用于生成序列号标识
	private final long sequentialId; // sequentialId
	private final long timespan; // timespan
	private final String dataType; // dataType
	private final String[] contents; // contents

	/**
	 * @param type
	 * @param contents
	 * @throws IllegalArgumentException when contents is null or empty
	 */
	public DefaultKafkaMessage(String type, String[] contents) throws IllegalArgumentException {
		if (contents == null || contents.length == 0) {
			throw new IllegalArgumentException("contents can't be null or empty");
		}

		this.sequentialId = AL.getAndIncrement();
		this.timespan = System.currentTimeMillis();
		this.dataType = type;
		this.contents = contents;
	}

	@Override
	public long getSequentialId() {
		return sequentialId;
	}

	@Override
	public long getTimespan() {
		return timespan;
	}

	@Override
	public String getDataType() {
		return dataType;
	}

	@Override
	public String[] getValues() {
		return contents;
	}

	@Override
	public String getMessage() {
		char fieldSpliter = getFieldSpliter();

		// sequentialId|timespan|dataType
		StringBuilder builder = new StringBuilder(256).append(sequentialId).append(fieldSpliter).append(timespan)
				.append(fieldSpliter).append(KafkaMessage.tropeSpliter(getDataType(), this));

		// message contents
		for (int i = 0, len = contents.length; i < len; i++) {
			builder.append(fieldSpliter).append(KafkaMessage.tropeSpliter(contents[i], this));
		}

		// message spliter
		builder.append(getMessageSpliter());

		return builder.toString();
	}

}
