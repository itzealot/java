package com.sky.projects.zookeeper;

import java.io.Serializable;
import java.util.List;

/**
 * 将zookeeper的路径操作封装成独立的对象
 *
 * @author zt
 *
 */
public interface ZkPath {

	/**
	 * 过滤Path，选择出符合条件的Path
	 * 
	 * @author zt
	 *
	 */
	@FunctionalInterface
	static public interface PathFilter {
		boolean accpet(String path);
	}

	/**
	 * Path关联的事件回调
	 * 
	 * @author zt
	 *
	 */
	@FunctionalInterface
	static public interface ZkWatch {
		void accept(ZkEventType type, ZkPath path, boolean isOnline);
	}

	/**
	 * Path关联的事件类型
	 */
	static public enum ZkEventType {
		None, NodeCreated, NodeDeleted, NodeDataChanged, NodeChildrenChanged;
	}

	/**
	 * 节点的访问权限
	 */
	static public enum ZkACL {
		OPEN_ACL_UNSAFE, CREATOR_ALL_ACL, READ_ACL_UNSAFE;
	}

	/**
	 * 获取目录名称
	 *
	 * @return
	 */
	public String getName();

	/**
	 * 获取目录关联的根目录
	 *
	 * @return
	 */
	public ZkRoot getRoot();

	/**
	 * 判断是否根目录
	 *
	 * @return
	 */
	public boolean isRoot();

	/**
	 * 获取上级目录对象
	 *
	 * @return
	 */
	public ZkPath getParent();

	/**
	 * 获取上级目录的路径
	 *
	 * @return
	 */
	public String getParentPath();

	/**
	 * 获取当前目录的路径
	 *
	 * @return
	 */
	public String getPath();

	/**
	 * 检查当前目录是否存在
	 *
	 * @return
	 */
	public boolean exists();

	/**
	 * 删除当前目录
	 *
	 * @return
	 */
	public boolean delete();

	/**
	 * 获取当前目录下的所有子目录
	 *
	 * @return
	 */
	public List<ZkPath> children();

	/**
	 * 按照目录名称过滤，返回过滤之后的所有子目录
	 *
	 * @param filter
	 * @return
	 */
	public List<ZkPath> children(PathFilter filter);

	/**
	 * 创建一个目录
	 *
	 * @return 创建成功则true，否则false
	 */
	public boolean mkdir();

	/**
	 * 创建目录的同时创建父目录
	 *
	 * @return 创建成功则true，否则false
	 */
	public boolean mkdirs();

	/**
	 * 在当前目录下创建子目录
	 *
	 * @param path
	 * @return
	 */
	public ZkPath create(String path);

	/**
	 * 基于当前的 ZkPathImpl 对象创建一个带数据的子目录节点
	 *
	 * @param path
	 * @param data
	 * @return
	 */
	public <T extends Serializable> ZkPath create(String path, T data);

	/**
	 * 基于当前的 ZkPathImpl 对象，如果isEphemeral 为 true 则创建临时子目录节点；否则创建永久子节点
	 *
	 * @param path
	 * @param isEphemeral
	 *            true or false; 是否是临时的节点
	 * @return
	 */
	public ZkPath create(String path, boolean isEphemeral);

	/**
	 * 基于当前的 ZkPathImpl 对象， 如果 isEphemeral 为 true 则创建带数据的临时子目录节点；否则创建永久子目录节点
	 *
	 * @param path
	 * @param isEphemeral
	 * @param data
	 * @return
	 */
	public <T extends Serializable> ZkPath create(String path, boolean isEphemeral, T data);

	/**
	 * 创建带访问权限限制的子路径，并附加数据
	 *
	 * @param path
	 * @param isEphemeral
	 * @param data
	 * @param acl
	 * @return
	 */
	public <T extends Serializable> ZkPath create(String path, boolean isEphemeral, T data, ZkACL acl);

	/**
	 * 创建带序列子节点，名字自动生成
	 *
	 * @param isEphemeral
	 * @return
	 */
	public ZkPath createSequential(boolean isEphemeral);

	/**
	 * 创建带序列子节点，同时附加数据，名字自动生成
	 *
	 * @param isEphemeral
	 * @param data
	 * @return
	 */
	public <T extends Serializable> ZkPath createSequential(boolean isEphemeral, T data);

	/**
	 * 创建带序列子目录，同时附加数据和授权访问。
	 *
	 * @param path
	 * @param isEphemeral
	 * @param data
	 * @param acl
	 * @param watch
	 * @return
	 */
	public <T extends Serializable> ZkPath createSequential(String path, boolean isEphemeral, T data, ZkACL acl);

	/**
	 * 获取数据
	 *
	 * @return
	 */
	public <T extends Serializable> T getData();

	/**
	 * 设置数据
	 *
	 * @param data
	 */
	public <T extends Serializable> void setData(T data);

	/**
	 * 保存数据到 Zookpeer
	 */
	public void saveData();

	/**
	 * 从 Zookpeer 中加载数据
	 */
	public void loadData();

	/**
	 * 设置 Watch
	 * 
	 * @param watch
	 */
	public void setWatch(ZkWatch watch);

	/**
	 * 获取 Watch
	 * 
	 * @param watch
	 */
	public ZkWatch getWatch();
}
