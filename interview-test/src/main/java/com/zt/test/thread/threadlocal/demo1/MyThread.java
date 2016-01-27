package com.zt.test.thread.threadlocal.demo1;

/**
 * Thread Bean Object
 * 
 * @author a
 *
 */
public class MyThread {
	private long id;
	private String name;

	public MyThread() {
		super();
	}

	public MyThread(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyThread [id=" + id + ", name=" + name + "]";
	}

}
