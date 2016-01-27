package com.apusic.skynet.curator.watch;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.curator.framework.imps.DefaultACLProvider;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

class PathDefaultACLProvider extends DefaultACLProvider {
	private final Map<String, List<ACL>> aclMap = Maps.newHashMap();

	public PathDefaultACLProvider(List<ZkConf> conf) throws NoSuchAlgorithmException {
		for (ZkConf zkConf : conf) {

			String idPassword = zkConf.getUid() + ':' + zkConf.getPwd();
			Id id = new Id("digest", DigestAuthenticationProvider.generateDigest(idPassword));
			ACL acl = new ACL(zkConf.getPerms(), id);
			aclMap.put(zkConf.getPath(), Lists.newArrayList(acl));
		}
	}

	@Override
	public List<ACL> getDefaultAcl() {
		return super.getDefaultAcl();
	}

	@Override
	public List<ACL> getAclForPath(String path) {
		return aclMap.get(path);
	}

}