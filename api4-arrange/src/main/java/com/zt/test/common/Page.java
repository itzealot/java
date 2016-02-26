package com.zt.test.common;

import java.util.ArrayList;
import java.util.List;

public class Page extends PageQueryParameter {
	private static final long serialVersionUID = 8513585862557274553L;

	/**
	 * 总记录条数
	 */
	private int total;
	/**
	 * 当前页记录
	 */
	private List<?> records = new ArrayList<Object>();;

	public Page(PageQueryParameter pageQueryParameter) {
		this(pageQueryParameter.getPageNo(), pageQueryParameter.getPageSize());
	}

	public Page(int pageNo, int pageSize) {
		super(pageNo, pageSize);
	}

	public static final Page EMPTY_PAGE = new Page(0, 0);

	/**
	 * 取得总页数
	 *
	 * @return
	 */
	public int getPages() {
		return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
	}

	/**
	 * 上一页页码
	 *
	 * @return
	 */
	public int prev() {
		if (total == 0) {
			return 1;
		}
		return pageNo == 1 ? 1 : pageNo - 1;
	}

	/**
	 * 下一页页码
	 *
	 * @return
	 */
	public int next() {
		if (total == 0) {
			return 1;
		}
		return pageNo + 1 > getPages() ? getPages() : pageNo + 1;
	}

	/**
	 * 最后一页页码
	 *
	 * @return
	 */
	public int last() {
		if (total == 0) {
			return 1;
		}
		return getPages();
	}

	/**
	 * 取得总记录条数
	 *
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	public int getLastIndex() {
		return this.getFirstIndex() + records.size();
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getRecords() {
		return (List<T>) records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}
}
