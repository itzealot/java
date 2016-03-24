package com.projects.sky.zookeeper.register;

import java.util.List;
import java.util.Map;

import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.common.PathConst;
import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.listener.CacheListener;
import com.projects.sky.zookeeper.register.impl.CommonRegisterImpl;

/**
 * Created by a on 2015/11/25.
 */
public class OrganizationRegister implements CacheListener<Organization> {

	private final Logger log = LoggerFactory.getLogger(OrganizationRegister.class);

	// private static BlockingQueue<Organization> organizationBlockingQueue;

	// private ExecutorService executorService;

	private CommonRegister commonService;

	public OrganizationRegister() {
		// organizationBlockingQueue = new LinkedBlockingQueue<>();
		// executorService = Executors.newCachedThreadPool();
		commonService = new CommonRegisterImpl();
	}

	@Override
	public void init(Map<String, Organization> cache) {
		List<String> children = commonService.list(PathConst.ORG_IDX);
		for (String child : children) {
			log.info("init organization cache ======>>{}", child);
			Organization organization = commonService.<Organization> getData(PathConst.ORG, "/",
					Commons.getPathByCode(child));
			cache.put(child, organization);
		}
	}

	@Override
	public void remoteDelete(Organization organization) {
		String code = organization.getCode();
		String path = Commons.getPathByCode(code);
		commonService.delete(PathConst.ORG, "/", path, "/C");
		commonService.delete(PathConst.ORG, "/", path);
		commonService.delete(PathConst.ORG_IDX, "/", code);
		log.info("delete organization from zookeeper======>>{}", organization);
	}

	@Override
	public void remoteUpdate(Organization organization) {
		String code = organization.getCode();
		String path = Commons.getPathByCode(code);
		commonService.setData(organization, PathConst.ORG, "/", path);
		commonService.setData(code, PathConst.ORG_IDX);
		log.info("update organization to zookeeper======>>{}", organization);
	}

	@Override
	public void remoteAdd(Organization organization) {
		String code = organization.getCode();
		String path = Commons.getPathByCode(code);
		commonService.create(organization, CreateMode.PERSISTENT, PathConst.ORG, "/", path);
		commonService.create(CreateMode.PERSISTENT, PathConst.ORG, "/", path, "/C");
		commonService.create(code, CreateMode.PERSISTENT, PathConst.ORG_IDX, "/", code);
		log.info("add organization to zookeeper======>>{}", organization);
	}

}
