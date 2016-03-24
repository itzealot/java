package com.projects.sky.zookeeper.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.entity.Organization;

/**
 * JAXB解析xml
 * 
 * 注解 @XmlAccessorType ：属性转为标签
 * 
 * 注解 @XmlRootElement ：根标签
 * 
 * 注解 @XmlType ：指定输出顺序
 * 
 * 注解 @XmlElement ： 标签名称
 * 
 * 注解 @XmlElementWrapper ： 在 List 外加父节点
 * 
 * @author zt 2016年1月7日
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Org-Init")
@XmlType(propOrder = { "center", "orgs" })
public class OrganizationWrapper {

	@XmlElement(name = "Node")
	private Node center;

	@XmlElementWrapper(name = "Orgs")
	@XmlElement(name = "Organization")
	private List<Organization> orgs;

	public OrganizationWrapper() {
		super();
		orgs = new ArrayList<>();
	}

	public Node getCenter() {
		return center;
	}

	public void setCenter(Node center) {
		this.center = center;
	}

	public List<Organization> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}

	@Override
	public String toString() {
		return "OrganizationWrapper [center=" + center + ", orgs.size=" + orgs.size() + "]";
	}

}
