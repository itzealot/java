package com.projects.sky.zookeeper.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;

import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.common.PathConst;
import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.service.InitService;
import com.projects.sky.zookeeper.wrapper.OrganizationWrapper;
import com.projects.sky.zookeeper.xml.XmlUtils;
import com.projects.sky.zookeeper.xml.Zookeepers;

public class InitServiceImpl implements InitService {

	private Zookeepers util;

	public InitServiceImpl(ZooKeeper zooKeeper) {
		util = new Zookeepers(zooKeeper);
	}

	@Override
	public void initRoot() {
		util.create("null", CreateMode.PERSISTENT, PathConst.ROOT);
		util.create("null", CreateMode.PERSISTENT, PathConst.ORG);
		util.create("null", CreateMode.PERSISTENT, PathConst.ORG_IDX);
		util.create("null", CreateMode.PERSISTENT, PathConst.NODE_IDX);
	}

	@Override
	public void init(String xml) {
		OrganizationWrapper wrapper = XmlUtils.unmarshallerFromXml(OrganizationWrapper.class, xml);
		if (wrapper == null) {
			return;
		}

		List<Organization> orgs = wrapper.getOrgs();
		for (Organization org : orgs) {
			String code = org.getCode();
			String path = Commons.getPathByCode(code);
			util.create(org, CreateMode.PERSISTENT, PathConst.ORG, "/", path);
			util.create(CreateMode.PERSISTENT, PathConst.ORG, "/", path, "/C");
			util.create(code, CreateMode.PERSISTENT, PathConst.ORG_IDX, "/", code);
			System.out.printf("add organization ======>>%s", org);
		}
		Node node = wrapper.getCenter();
		String code = node.getCode();
		String path = Commons.getPathByCode(code);
		util.create(node, CreateMode.PERSISTENT, PathConst.ORG, "/", path, "/N");
		util.create(CreateMode.PERSISTENT, PathConst.ORG, "/", path, "/N/S");
		util.create(CreateMode.PERSISTENT, PathConst.ORG, "/", path, "/N/M");
		util.create(code, CreateMode.PERSISTENT, PathConst.NODE_IDX, "/", code);
		System.out.printf("add node ======>>%s", node);
	}

	@Override
	public void clean() {
		walkDelete(PathConst.ORG);
		walkDelete(PathConst.ORG_IDX);
		walkDelete(PathConst.NODE_IDX);
	}

	@Override
	public void view() {
		List<String> nodes = walkView(PathConst.ROOT);
		for (String str : nodes) {
			System.out.println(str);
		}
	}

	@Override
	public void delete(String xml) {

	}

	@Override
	public void backup(String xml) {
		List<Organization> orgs = walkOrganization();
		OrganizationWrapper wrapper = new OrganizationWrapper();
		wrapper.setOrgs(orgs);
		Node center = util.<Node> getData(PathConst.ORG, "/00/N");
		wrapper.setCenter(center);
		XmlUtils.marshallerToXml(wrapper, xml);
	}

	private List<Organization> walkOrganization() {
		List<Organization> orgs = new ArrayList<Organization>();
		Stack<String> stack = new Stack<>();
		List<String> children = util.list(PathConst.ORG);
		for (String child : children) {
			stack.push(child);
		}
		while (!stack.isEmpty()) {
			String node = stack.pop();
			children = util.list(PathConst.ORG, "/", node, "/C");
			if (children.isEmpty()) {
				Organization organization = util.<Organization> getData(PathConst.ORG, "/", node);
				orgs.add(organization);
				System.out.printf("Leaf->%s\n", organization);
			} else {
				for (String child : children) {
					stack.push(node + "/C/" + child);
				}
				Organization organization = util.<Organization> getData(PathConst.ORG, "/", node);
				orgs.add(organization);
				System.out.printf("Node->%s \n", organization);
			}
		}
		return orgs;
	}

	private void walkDelete(String root) {
		Stack<String> stack = new Stack<>();
		List<String> children = util.list(root);
		for (String child : children) {
			String str = Commons.joinPath(root, "/", child);
			stack.push(str);
		}

		while (!stack.empty()) {
			String node = stack.pop();
			children = util.list(node);
			if (children.isEmpty()) {
				util.delete(node);
				System.out.printf("delete node ======>>%s\n", node);
			} else {
				stack.push(node);
				for (String child : children) {
					String str = Commons.joinPath(node, "/", child);
					stack.push(str);
				}
			}
		}
	}

	private List<String> walkView(String root) {
		List<String> list = new ArrayList<>();
		Queue<String> queue = new LinkedList<String>();
		List<String> children = util.list(root);
		for (String child : children) {
			String str = Commons.joinPath(root, "/", child);
			queue.add(str);
		}
		while (!queue.isEmpty()) {
			String node = queue.poll();
			children = util.list(node);
			if (children.isEmpty()) {
				list.add(node);
				System.out.printf(" ======>>%s\n", node);
			} else {
				for (String child : children) {
					String str = Commons.joinPath(node, "/", child);
					queue.add(str);
				}
			}
		}
		return list;
	}
}
