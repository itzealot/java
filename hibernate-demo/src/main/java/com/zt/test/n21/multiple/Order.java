package com.zt.test.n21.multiple;

/**
 * Customer : Order = 1 : n.
 * 
 * 一个客户可以有多个订单，但是一个订单只属于一个客户，所以在多的一方持有少的一方的一个实例。
 * 
 * @author zengtao
 *
 */
public class Order {
	private Integer orderId;
	private String orderName;
	private Customer customer;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
