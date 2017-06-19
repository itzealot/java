package com.sky.projects.jdk.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import com.sky.projects.jdk.thread.Threads;

/**
 * JvmOption:-Xms10M -Xmx10M
 * 
 * @author zealot
 *
 */
public class ReferenceTest {

	public static void main(String[] args) {
		// softReference();
		// weakReference();
		phantomReference();
	}

	/**
	 * 软引用
	 */
	public static void softReference() {
		RefObject object = new RefObject();
		RefObject object2 = new RefObject();

		// ReferenceQueue
		ReferenceQueue<RefObject> queue = new ReferenceQueue<>();

		// 如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中
		// 根据强引用创建软引用
		SoftReference<RefObject> softReference = new SoftReference<RefObject>(object, queue);
		SoftReference<RefObject> softReference2 = new SoftReference<RefObject>(object2, queue);
		object = null;
		object2 = null;

		new Thread(new CheckRefQueue<>(queue)).start();

		System.out.println("before soft ref:" + softReference.get());
		System.out.println("before soft ref2:" + softReference2.get());

		// allocate 8M
		byte[] bytes = new byte[6 * 1024 * 1024];
		Threads.sleep(1000);
		bytes.toString();
		bytes = null;
		System.gc();
		System.out.println("after soft ref:" + softReference.get());
		System.out.println("after soft ref2:" + softReference2.get());
	}

	/**
	 * 弱引用
	 */
	public static void weakReference() {
		RefObject object = new RefObject();
		RefObject object2 = new RefObject();

		// ReferenceQueue
		ReferenceQueue<RefObject> queue = new ReferenceQueue<>();

		// 如果弱引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中
		WeakReference<RefObject> softReference = new WeakReference<RefObject>(object, queue);
		WeakReference<RefObject> softReference2 = new WeakReference<RefObject>(object2, queue);
		object = null;
		object2 = null;

		CheckRefQueue<RefObject> target = new CheckRefQueue<>(queue);
		new Thread(target).start();

		System.out.println("before weak ref:" + softReference.get());
		System.out.println("before weak ref2:" + softReference2.get());

		// allocate 8M
		byte[] bytes = new byte[6 * 1024 * 1024];

		Threads.sleep(1000);

		bytes.toString();
		bytes = null;
		System.gc();
		System.out.println("after weak ref:" + softReference.get());
		System.out.println("after weak ref2:" + softReference2.get());

		target.shutdown();
	}

	public static void phantomReference() {
		RefObject object = new RefObject();
		RefObject object2 = new RefObject();

		// ReferenceQueue
		ReferenceQueue<RefObject> queue = new ReferenceQueue<>();

		// 如果弱引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中
		PhantomReference<RefObject> phantomReference = new PhantomReference<RefObject>(object, queue);
		PhantomReference<RefObject> phantomReference2 = new PhantomReference<RefObject>(object2, queue);
		object = null;
		object2 = null;

		CheckRefQueue<RefObject> target = new CheckRefQueue<>(queue);
		new Thread(target).start();

		System.out.println("before phantom ref:" + phantomReference.get());
		System.out.println("before phantom ref2:" + phantomReference2.get());

		// allocate 8M
		byte[] bytes = new byte[6 * 1024 * 1024];

		Threads.sleep(1000);

		bytes.toString();
		bytes = null;
		System.gc();
		System.out.println("after phantom ref:" + phantomReference.get());
		System.out.println("after phantom ref2:" + phantomReference2.get());
		target.shutdown();
	}

}
