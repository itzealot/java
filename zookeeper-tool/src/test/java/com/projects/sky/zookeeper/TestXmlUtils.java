package com.projects.sky.zookeeper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.wrapper.OrganizationWrapper;
import com.projects.sky.zookeeper.xml.XmlUtils;

public class TestXmlUtils {

	private OrganizationWrapper orgWrapper;

	@Before
	public void init() {
		List<Organization> orgs = new ArrayList<>();
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

		orgWrapper = new OrganizationWrapper();
		orgWrapper.setOrgs(orgs);

		Node node = new Node("公安部", "00", "中心节点");
		node.setId(1L);
		orgWrapper.setCenter(node);
	}

	@Test
	public void testMarshallerToString() {
		String orgStr = XmlUtils.marshallerToString(orgWrapper);
		System.out.println(orgStr);
	}

	@Test
	public void testMarshallerToXml() {
		try {
			XmlUtils.marshallerToXml(orgWrapper, "config.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
