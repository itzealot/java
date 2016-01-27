package com.sky.projects.ws.demo.model;

public class User extends BaseModel<Long> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3696065558536421005L;

	private Long id;
	private String name;
	private String password;
	private String remark;

	public User() {
		super();
	}

	public User(Long id, String name, String password, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.remark = remark;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", remark=" + remark + "]";
	}

}
