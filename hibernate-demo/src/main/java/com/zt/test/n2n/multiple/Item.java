package com.zt.test.n2n.multiple;

import java.util.HashSet;
import java.util.Set;

public class Item {
	private Integer id;
	private String name;
	private Set<Category> categorys = new HashSet<Category>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

}
