package com.zt.design.j2ee.interceptorfilter;

/**
 * 过滤管理器
 * 
 * @author zealot
 *
 */
public class FilterManager {

	FilterChain filterChain;

	public FilterManager(Target target) {
		filterChain = new FilterChain();

		filterChain.setTarget(target);
	}

	public void setFilter(Filter filter) {
		filterChain.addFilter(filter);
	}

	public void filterRequest(String request) {
		filterChain.execute(request);
	}
}
