package com.apusic.demo.model;

import java.io.Serializable;

/**
 * 用户实体
 * 
 * @author zt
 *
 */
public class User implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6685512927738742500L;
	private long id;

	private String name;

	private String passowrd;

	private String status;

	private Role role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", passowrd='" + passowrd + '\'' + ", status='"
				+ status + '\'' + ", role=" + role + '}';
	}
}
