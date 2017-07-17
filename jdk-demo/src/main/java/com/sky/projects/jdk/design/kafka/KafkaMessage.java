package com.sky.projects.jdk.design.kafka;

public interface KafkaMessage {

	long getSequentialId();

	long getTimespan();

	String getDataType();

	String[] getValues();

	/**
	 * 获取需要发送的消息
	 * 
	 * @return
	 */
	String getMessage();

	default char getMessageSpliter() {
		return '\002';
	}

	default char tropeWhenHasMessageSpliter() {
		return '\003';
	}

	default char getFieldSpliter() {
		return '|';
	}

	default char tropeWhenHasFieldSpliter() {
		return '\004';
	}

	static String tropeWhenHasFieldSpliter(String str, KafkaMessage message) {
		return str == null || str.isEmpty() ? str
				: str.replace(message.getFieldSpliter(), message.tropeWhenHasFieldSpliter());
	}

	static String tropeWhenHasMessageSpliter(String str, KafkaMessage message) {
		return str == null || str.isEmpty() ? str
				: str.replace(message.getMessageSpliter(), message.tropeWhenHasMessageSpliter());
	}

	static String tropeSpliter(String str, KafkaMessage message) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return tropeWhenHasMessageSpliter(tropeWhenHasFieldSpliter(str, message), message);
	}
}
