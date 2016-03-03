package com.apusic.demo.model;

import java.io.Serializable;

/**
 * 角色实体
 * 
 * @author zt
 *
 */
public class Role implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8278668004252288005L;

	private long id;

	private String name;

	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
	}
}
