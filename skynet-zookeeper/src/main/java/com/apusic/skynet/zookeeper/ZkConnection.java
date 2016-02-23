package com.apusic.skynet.zookeeper;

/**
 * 负责维护并持有zookeeper的会话。
 * 
 * @author jiangjianbo
 *
 */
public interface ZkConnection extends AutoCloseable {

	public ZkRoot getRoot();

	public void beginTransaction();

	public boolean inTransaction();

	public void commitTransaction();

	public void setBackground(boolean backgroundMode);

	public boolean getBackground();
}
