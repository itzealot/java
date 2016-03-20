package com.zt.design.j2ee.interceptorfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链
 * 
 * @author zengtao
 *
 */
public class FilterChain {

	// 使用 List<Filter>存储 过滤器链
	private List<Filter> filters = new ArrayList<Filter>();

	private Target target;

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	public void execute(String request) {
		for (Filter filter : filters) {
			filter.execute(request);
		}

		target.execute(request);
	}

	public void setTarget(Target target) {
		this.target = target;
	}
}
