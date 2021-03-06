package com.apusic.sicp.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageQueryParameter implements Serializable {

	public static final int DEFAULT_PAGE_SIZE = 20;
	protected int pageNo = 1;
	protected int pageSize = DEFAULT_PAGE_SIZE;

	public PageQueryParameter(int pageNo, int pageSize) {
		super();
		if (pageNo > 0)
			this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	private PageQueryParameter() {

	}

	public static final PageQueryParameter createDefaultParameter() {
		return new PageQueryParameter();
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo > 0)
			this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return (pageNo - 1) * pageSize;
	}

}
