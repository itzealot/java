package com.projects.sky.util.support;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import com.projects.sky.util.SkyAcceptable;

/**
 * 抽象的消息接收器
 * 
 * @author zealot
 *
 * @param <C>
 *            消息存储容器
 * @param <V>
 */
public abstract class SkyAbstractAccepter<C, V> implements SkyAcceptable<V> {

	// 接收器
	protected C container;

	public SkyAbstractAccepter(C container) {
		this.container = container;
	}

	@Override
	public void accept(List<V> messages) {
		checkNotNull(messages, "messages must not be null.");

		for (V message : messages) {
			this.accept(message);
		}
	}

}
