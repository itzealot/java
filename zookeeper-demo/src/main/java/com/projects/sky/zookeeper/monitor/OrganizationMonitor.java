package com.projects.sky.zookeeper.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.common.PathConst;
import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.listener.MonitorCallBack;
import com.projects.sky.zookeeper.monitor.impl.CommonMonitorImpl;
import com.projects.sky.zookeeper.register.CommonRegister;
import com.projects.sky.zookeeper.register.impl.CommonRegisterImpl;

/**
 * Created by colin on 2015/11/26.
 *
 */
public class OrganizationMonitor implements MonitorCallBack {

	private final Logger log = LoggerFactory.getLogger(OrganizationMonitor.class);

	private ExecutorService pool;

	private CommonMonitor commonMonitor;

	private CommonRegister commonService;

	private Map<String, Organization> orgCache;

	public OrganizationMonitor(Map<String, Organization> orgCache) {
		log.info("====new organization monitor===");
		this.orgCache = orgCache;
		pool = Executors.newCachedThreadPool();
		commonMonitor = new CommonMonitorImpl(pool);
		commonService = new CommonRegisterImpl();
		createOrganizationChildrenMonitor();
		createOrganizationDataMonitor();
	}

	private void createOrganizationChildrenMonitor() {
		log.info("====create organization children monitor===");
		commonMonitor.createChilrendMoniotr(this, PathConst.ORG_IDX);
	}

	private void createOrganizationDataMonitor() {
		log.info("====create organization data monitor===");
		commonMonitor.createDataMonitor(this, PathConst.ORG_IDX);
	}

	/**
	 * 从缓存中删除/添加Organization对象
	 * 
	 * @param path
	 * @param node
	 */
	@Override
	public void monitorChildren(String path, List<String> node) {
		List<String> keys = new ArrayList<>(orgCache.keySet());
		if (keys.size() == node.size()) {
			return;
		} else if (keys.size() > node.size()) {
			if (keys.removeAll(node)) {
				for (String key : keys) {
					log.info("=========remove organization from cache {}=========", key);
					orgCache.remove(key);
				}
			}
		} else {
			if ((keys.size() == 0) || node.removeAll(keys)) {
				for (String code : node) {
					String orgPath = Commons.getPathByCode(code);
					Organization organization = commonService.<Organization> getData(PathConst.ORG, "/", orgPath);
					log.info("=========add organization to cache {}=========", organization);
					orgCache.put(code, organization);
				}
			}
		}
	}

	/**
	 * 更新Organization对象
	 * 
	 * @param path
	 * @param data
	 */
	@Override
	public void monitorData(String path, byte[] data) {
		String code = Commons.deserialize(data);
		if ("null".equals(code)) {
			return;
		}
		String orgPath = Commons.getPathByCode(code);
		Organization organization = commonService.<Organization> getData(PathConst.ORG, "/", orgPath);
		orgCache.put(code, organization);
		log.info("=========Update organization cache {}=========", organization);
	}

	@Override
	public void monitorExists(String path) {
		return;
	}
}
