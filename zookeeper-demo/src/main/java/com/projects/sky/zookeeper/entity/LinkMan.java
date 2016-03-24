package com.projects.sky.zookeeper.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "name", "tel", "mobile", "email", "addr", "description" })
public class LinkMan implements Serializable {
	private static final long serialVersionUID = -3510971884877892726L;

	public LinkMan() {
	}

	public LinkMan(String name, String tel, String email, String addr, String description) {
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.addr = addr;
		this.description = description;
	}

	@XmlElement
	private String name;
	@XmlElement
	private String tel;
	@XmlElement
	private String mobile;
	@XmlElement
	private String email;
	@XmlElement
	private String addr;
	@XmlElement
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LinkMan other = (LinkMan) obj;
		return (this.getName() == other.getName() && this.getMobile() == other.getMobile()
				&& this.getTel() == other.getTel() && this.getEmail() == other.getEmail());
	}

}
