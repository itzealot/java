package com.zt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 单向多对1 OrderN21Single
 * 
 * @author zt
 *
 */
@Entity
@Table(name = "T_OrderN21Single")
public class OrderN21Single implements Serializable {
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

	/**
	 * 映射单向n-1的关联关系.<br />
	 * 1). 使用 @ManyToOne 注解.<br />
	 * 2). 使用 @JoinColumn 来映射外键.<br />
	 */
	@JoinColumn(name = "customer_id")
	@ManyToOne
	private CustomerN21Single customer;

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

	public CustomerN21Single getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerN21Single customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "OrderN21Single [id=" + id + ", name=" + name + ", remark=" + remark + ", customer=" + customer + "]";
	}

}
