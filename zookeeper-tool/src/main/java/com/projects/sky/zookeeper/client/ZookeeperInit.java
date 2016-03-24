package com.projects.sky.zookeeper.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;

import com.projects.sky.zookeeper.common.PathConst;
import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.register.CommonRegister;
import com.projects.sky.zookeeper.register.NodeRegister;
import com.projects.sky.zookeeper.register.OrganizationRegister;
import com.projects.sky.zookeeper.register.impl.CommonRegisterImpl;

/**
 * Created by a on 2015/11/26.
 */
public class ZookeeperInit {
	private static List<Organization> orgs;

	private static List<Node> nodes;

	static {
		orgs = new ArrayList<>();
		Organization org = new Organization("公安部", "00", 1);
		orgs.add(org);
		org = new Organization("广东省公安厅", "00-C-001", 1);
		orgs.add(org);
		org = new Organization("广西省公安厅", "00-C-002", 0);
		orgs.add(org);
		org = new Organization("云南省公安厅", "00-C-003", 0);
		orgs.add(org);
		org = new Organization("贵州省公安厅", "00-C-004", 0);
		orgs.add(org);
		org = new Organization("四川省公安厅", "00-C-005", 0);
		orgs.add(org);
		org = new Organization("湖南省公安厅", "00-C-006", 1);
		orgs.add(org);
		org = new Organization("长沙市公安局", "00-C-006-C-0000", 1);
		orgs.add(org);
		org = new Organization("深圳市公安局", "00-C-001-C-0000", 1);
		orgs.add(org);
		org = new Organization("惠州市公安局", "00-C-001-C-0001", 0);
		orgs.add(org);
		org = new Organization("广州市公安局", "00-C-001-C-0002", 1);
		orgs.add(org);
		org = new Organization("湛江市公安局", "00-C-001-C-0003", 0);
		orgs.add(org);
		org = new Organization("深圳市南山区公安局", "00-C-001-C-0001-C-0001", 0);
		orgs.add(org);
		org = new Organization("深圳市宝安区公安局", "00-C-001-C-0001-C-0002", 0);
		orgs.add(org);
		org = new Organization("深圳市罗湖区公安局", "00-C-001-C-0001-C-0003", 0);
		orgs.add(org);
		org = new Organization("深圳市光明区公安局", "00-C-001-C-0001-C-0005", 0);
		orgs.add(org);

		nodes = new ArrayList<>();
		Node node = new Node("公安部", "00", "中心节点");
		nodes.add(node);
		node = new Node("广东省公安厅", "00-C-001", "省级节点");
		nodes.add(node);
		node = new Node("广州市公安局", "00-C-001-C-0002", "市级节点");
		nodes.add(node);
		node = new Node("深圳市公安局", "00-C-001-C-0000", "市级节点");
		nodes.add(node);
		node = new Node("湖南省公安厅", "00-C-006", "省级节点");
		nodes.add(node);
		node = new Node("长沙市公安厅", "00-C-006-C-0000", "市级节点");
		nodes.add(node);

	}

	public static void init() {
		CommonRegister service = new CommonRegisterImpl();
		service.create("null", CreateMode.PERSISTENT, PathConst.ROOT);
		service.create("null", CreateMode.PERSISTENT, PathConst.ORG);
		service.create("null", CreateMode.PERSISTENT, PathConst.ORG_IDX);
		service.create("null", CreateMode.PERSISTENT, PathConst.NODE_IDX);

		OrganizationRegister register = new OrganizationRegister();
		for (Organization org : orgs) {
			register.remoteAdd(org);
		}

		NodeRegister nodeRegister = new NodeRegister();
		for (Node node : nodes) {
			nodeRegister.remoteAdd(node);
		}
	}

	public static void main(String... arg) {
		OrganizationRegister register = new OrganizationRegister();
		for (Organization org : orgs) {
			register.remoteUpdate(org);
		}
	}

}
