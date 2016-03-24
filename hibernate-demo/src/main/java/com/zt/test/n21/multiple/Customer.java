package com.zt.test.n21.multiple;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer customerId;
	private String customerName;

	/**
	 * 两点注意： 1.声明集合类型时，需使用接口类型，因为Hibernate在获取集合类型时，
	 * 返回的是Hibernate内置的集合类型，而不是JavaSE一个标准的集合实现。 2.需要把集合进行出售，可以防止发生空指针异常
	 */
	private Set<Order> order = new HashSet<Order>();

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}
}
