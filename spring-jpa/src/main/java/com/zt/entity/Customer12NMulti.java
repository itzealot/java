package com.zt.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 双向1对多 Customer12NMulti.<br />
 * JoinColumn中name属性CUSTOMER_ID必须一致.<br />
 * 
 * @author zt
 *
 */
@Entity
@Table(name = "T_Customer12NMulti")
public class Customer12NMulti implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7304389877110353828L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "remark")
	private String remark;

	/**
	 * 使用 @OneToMany 映射双向 1-n 的关联关系.<br/>
	 * 1). 使用 @JoinColumn 来映射外键列的名称.<br/>
	 * 2). 使用 fetch 属性来修改默认的加载策略.<br/>
	 * 3). 使用 cascade 属性来修改默认的删除策略.<br/>
	 * 4). 在 1 的一端使用 mappedBy 属性，则 @OneToMany 就不能再使用 @JoinColumn 属性，即 指向不维护
	 * 关联关系的一端(1 的一方).<br/>
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE }, mappedBy = "customer")
	private Set<Order12NMulti> orders = new HashSet<>();

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

	public Set<Order12NMulti> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order12NMulti> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer12NSingle [id=" + id + ", name=" + name + ", remark=" + remark + ", orders=" + orders + "]";
	}

}
