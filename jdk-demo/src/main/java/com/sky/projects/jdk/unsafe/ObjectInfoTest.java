package com.sky.projects.jdk.unsafe;

import sun.misc.Unsafe;

/**
 * code copy from {@link http://blog.csdn.net/zhxdick/article/details/52003123}
 * 
 * @author zealot
 */
@SuppressWarnings("restriction")
public final class ObjectInfoTest {

	private static final Unsafe unsafe = UnsafeHelper.unsafe();

	/**
	 * 获取对象起始位置偏移量
	 * 
	 * @param unsafe
	 * @param object
	 * @return
	 */
	public static long getObjectAddress(sun.misc.Unsafe unsafe, Object object) {
		// 获取对象的偏移地址，需要将目标对象设为辅助数组的第一个元素(也是唯一的元素)
		// 由于这是一个复杂类型元素(不是基本数据类型)，它的地址存储在数组的第一个元素。
		// 然后，获取辅助数组的基本偏移量。数组的基本偏移量是指数组对象的起始地址与数组第一个元素之间的偏移量。
		Object helperArray[] = new Object[] { object };
		long baseOffset = unsafe.arrayBaseOffset(Object[].class);
		return unsafe.getLong(helperArray, baseOffset);
	}

	// 对象解析
	private final static ClassIntrospector CI = new ClassIntrospector();

	/**
	 * 获取任意对象的大小
	 * 
	 * @param object
	 * @return
	 */
	public static long getObjectSize(Object object) {
		try {
			return CI.introspect(object).getDeepSize();
		} catch (IllegalAccessException e) {
			return -1;
		}
	}

	/**
	 * 
	 * @param unsafe
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static long getObjectFieldSize(sun.misc.Unsafe unsafe, Object obj, String fieldName) {
		try {
			return unsafe.objectFieldOffset(obj.getClass().getDeclaredField(fieldName));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 通过这两个类计算一个Object的大小，通过Unsafe的copyMemory方法来拷贝.<br>
	 * <p>
	 * param Object src: src Object.<br>
	 * param long srcOffset: offset.<br>
	 * param Object dst: dst Object.<br>
	 * param long dstObjectAddressOffset: dst Object address.<br>
	 * param long srcObjectSize: src Object size.<br>
	 * </p>
	 * 
	 * public native void copyMemory(Object src, long srcOffset, Object dst,
	 * long dstObjectAddressOffset, long srcObjectSize)
	 */
	static void testCopyUsingUnsafe() {
		SampleClass obj = new SampleClass();
		obj.setAge(999);
		obj.setTimespan(999999999L);

		long objectSize = getObjectSize(obj);
		System.out.println("objectSize:" + objectSize);

		long ageOffset = getObjectFieldSize(unsafe, obj, "age");
		long timespanOffset = getObjectFieldSize(unsafe, obj, "timespan");
		long objectAddress = getObjectAddress(unsafe, obj);

		System.out.println("age:" + unsafe.getInt(objectAddress + ageOffset));
		System.out.println("timespan:" + unsafe.getInt(objectAddress + timespanOffset));

		SampleClass copy = new SampleClass();

		long copyAddress = getObjectAddress(unsafe, copy);

		// copy object according to destination address
		unsafe.copyMemory(obj, 0, null, copyAddress, objectSize);

		System.out.println(copy.getAge());
		System.out.println(copy.getTimespan());
	}

	/**
	 * C++中有malloc，realloc和free方法来操作内存，在Unsafe类中对应为(堆外分配):<br>
	 * 分配var1字节大小的内存，返回起始地址偏移量.<br>
	 * public native long allocateMemory(long var1);
	 * 
	 * 重新给var1起始地址的内存分配长度为var3字节大小的内存，返回新的内存起始地址偏移量.<br>
	 * public native long reallocateMemory(long var1, long var3);
	 * 
	 * 释放起始地址为var1的内存.<br>
	 * public native void freeMemory(long var1);
	 */
	static void testUnsafeAllocate() {
		// 分配 1B 大小的内存，地址为 address
		long address = unsafe.allocateMemory(1L);

		// 根据地址放置一个 value
		unsafe.putByte(address, (byte) 100);

		// 根据地址获取 value
		byte shortValue = unsafe.getByte(address);
		System.out.println("address:" + address + ", value:" + shortValue);

		// 重新分配一个long
		long addressNew = unsafe.reallocateMemory(address, 8L);
		unsafe.putLong(addressNew, 1024L);
		long longValue = unsafe.getLong(addressNew);
		System.out.println("addressNew:" + addressNew + ", value:" + longValue);

		// Free掉,这个数据可能脏掉
		unsafe.freeMemory(addressNew);
		longValue = unsafe.getLong(addressNew);
		System.out.println("addressNew:" + addressNew + ", value:" + longValue);
	}

	/**
	 * 比较obj的offset处内存位置中的值和期望的值，如果相同则更新。此更新是不可中断的.<br>
	 * param obj : 需要更新的对象.<br>
	 * param offset : obj中整型field的偏移量.<br>
	 * param expect : 希望field中存在的值.<br>
	 * param update : 如果期望值expect与field的当前值相同，设置filed的值为这个新值.<br>
	 * return 如果field的值被更改返回true.<br>
	 * 
	 * public native boolean compareAndSwapInt(Object obj, long offset, int
	 * expect, int update);
	 */
	static void testCas() {

	}

	public static void main(String[] args) {
		// testCopyUsingUnsafe();
		testUnsafeAllocate();
	}

	static class SampleClass {
		private int age;
		private long timespan;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public long getTimespan() {
			return timespan;
		}

		public void setTimespan(long timespan) {
			this.timespan = timespan;
		}

	}

}
