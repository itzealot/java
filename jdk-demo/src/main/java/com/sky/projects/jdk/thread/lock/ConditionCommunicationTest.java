package com.sky.projects.jdk.thread.lock;

/**
 * Lock与Condition 配合使用进行线程间的通信，类似于synchronized与{@link Object#wait},
 * {@link Object#notify}, {@link Object#notifyAll}方法
 * 
 * @since 1.8 支持lambda表达式
 * 
 * @author zealot
 *
 */
public class ConditionCommunicationTest {

	public static void main(String[] args) {
		final ConditionCommunication communication = new ConditionCommunication();

		new Thread(() -> {
			while (true) {
				communication.doPrintWhenTrue(10);
			}
		}).start();

		new Thread(() -> {
			while (true) {
				communication.doPrintWhenFalse(20);
			}
		}).start();
	}
}