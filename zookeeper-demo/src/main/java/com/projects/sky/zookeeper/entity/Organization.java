package com.projects.sky.zookeeper.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organization", propOrder = { "id", "name", "code", "status", "description" })
public class Organization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer ORG_UNREGIST = 0;
	public static final Integer ORG_REGIST = 1;

	@XmlElement
	private Long id;

	@XmlElement(required = true)
	private String name;

	@XmlElement(required = true)
	private String code;

	@XmlElement
	private String description;

	@XmlElement(required = true)
	private Integer status;

	public Organization() {
		super();
	}

	public Organization(String name, String code, Integer status) {
		super();
		this.name = name;
		this.code = code;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", code=" + code + ", description=" + description
				+ ", status=" + status + "]";
	}

}
