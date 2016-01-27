package com.zt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 单向1对多 Order12NSingle
 * 
 * @author zt
 *
 */
@Entity
@Table(name = "T_Order12NSingle")
public class Order12NSingle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2944979367811887189L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "remark")
	private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Order12NSingle [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}

}
