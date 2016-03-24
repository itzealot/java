package com.projects.sky.zookeeper.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Node", propOrder = { "id", "name", "orgName", "code", "orgCode", "description", "linkMan" })
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private Long id;

	@XmlElement(required = true)
	private String code;

	@XmlElement(required = true)
	private String name;

	@XmlElement
	private String description;

	@XmlElement(name = "LinkMan")
	private LinkMan linkMan;

	@XmlElement
	private String orgCode; // 组织机构代码

	@XmlElement
	private String orgName; // 组织机构名称

	@XmlTransient
	private Set<Cluster> clusters = new HashSet<Cluster>();

	/**
	 * 懒加载，如果需要用到organization，查询的时候请先调用。
	 */
	@XmlTransient
	private Organization organization;

	/**
	 * 节点注册凭据，由上级部门通过线下渠道授予
	 */
	@XmlTransient
	private String installKey;

	public Node(String name, String code, String description) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
	}

	public Node() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LinkMan getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Set<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(Set<Cluster> clusters) {
		this.clusters = clusters;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getInstallKey() {
		return installKey;
	}

	public void setInstallKey(String installKey) {
		this.installKey = installKey;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", linkMan="
				+ linkMan + "]";
	}

}
