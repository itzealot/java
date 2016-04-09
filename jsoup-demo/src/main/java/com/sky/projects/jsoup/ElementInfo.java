package com.sky.projects.jsoup;

public class ElementInfo {
	private String title;
	private String href;

	public ElementInfo() {
		super();
	}

	public ElementInfo(String title, String href) {
		super();
		this.title = title;
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
