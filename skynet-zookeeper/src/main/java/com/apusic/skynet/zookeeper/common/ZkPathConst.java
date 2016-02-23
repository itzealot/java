package com.apusic.skynet.zookeeper.common;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

public final class ZkPathConst {

	private ZkPathConst() {
	}

	// The ACL final
	public static final List<ACL> OPEN_ACL_UNSAFE = ZooDefs.Ids.OPEN_ACL_UNSAFE;
	public static final List<ACL> CREATOR_ALL_ACL = ZooDefs.Ids.CREATOR_ALL_ACL;
	public static final List<ACL> READ_ACL_UNSAFE = ZooDefs.Ids.READ_ACL_UNSAFE;

	// The CreateMode final
	public static final CreateMode PERSISTENT_MODE = CreateMode.PERSISTENT;
	public static final CreateMode PERSISTENT_SEQUENTIAL_MODE = CreateMode.PERSISTENT_SEQUENTIAL;
	public static final CreateMode EPHEMERAL_MODE = CreateMode.EPHEMERAL;
	public static final CreateMode EPHEMERAL_SEQUENTIAL_MODE = CreateMode.EPHEMERAL_SEQUENTIAL;

}
