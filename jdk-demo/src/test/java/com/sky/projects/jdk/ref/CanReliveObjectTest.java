package com.sky.projects.jdk.ref;

import com.sky.projects.jdk.thread.Threads;

import junit.framework.TestCase;

public class CanReliveObjectTest extends TestCase {

	public void testCanReliveObject() {
		CanReliveObject obj = new CanReliveObject();
		CanReliveObject obj2 = new CanReliveObject();

		obj = null;

		gc(obj, "first gc");
		gc(obj, "second gc");

		obj2.toString();
	}

	private void gc(CanReliveObject obj, String msg) {
		Threads.sleep(1000);
		System.out.println("retry:" + (CanReliveObject.getObj() == null));
		System.gc();

		System.out.println(msg);
		if (obj == null) {
			System.out.println("obj is null");
		} else {
			System.out.println("CanReliveObject:" + obj);
		}
	}

}
