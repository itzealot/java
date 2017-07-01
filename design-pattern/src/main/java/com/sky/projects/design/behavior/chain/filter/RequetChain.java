package com.sky.projects.design.behavior.chain.filter;

public class RequetChain<T> implements Chain<T> {

	private Request request;
	private Chain<T> next;
	private Filter<T> filter;

	@Override
	public void doFilter(Filter<T> filter, T param) {
		if (filter != null && filter.filter(request, param)) {
			if (next != null)
				next.doFilter(next.getFilter(), param);
		}
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public Chain<T> getChain() {
		return next;
	}

	@Override
	public void setChain(Chain<T> chain) {
		this.next = chain;
	}

	@Override
	public Filter<T> getFilter() {
		return filter;
	}

	@Override
	public void getFilter(Filter<T> filter) {
		this.filter = filter;
	}

}
