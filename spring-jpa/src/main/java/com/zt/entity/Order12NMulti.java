package com.zt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 双向1对多 Order12NMulti.<br />
 * JoinColumn中name属性CUSTOMER_ID必须一致.<br />
 * 
 * @author zt
 *
 */
@Entity
@Table(name = "T_Order12NMulti")
public class Order12NMulti implements Serializable {
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
	 * 映射双n-1的关联关系.<br />
	 * 1). 使用 @ManyToOne 注解.<br />
	 * 2). 使用 @JoinColumn 来映射外键.<br />
	 */
	@JoinColumn(name = "customer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer12NMulti customer;

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

	public Customer12NMulti getCustomer() {
		return customer;
	}

	public void setCustomer(Customer12NMulti customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order12NMulti [id=" + id + ", name=" + name + ", remark=" + remark + ", customer=" + customer + "]";
	}

}
