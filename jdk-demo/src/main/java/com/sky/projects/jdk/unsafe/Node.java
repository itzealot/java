package com.sky.projects.jdk.unsafe;

/**
 * Unsafe => 用于内存分配
 * 
 * @see java.util.concurrent.ConcurrentLinkedQueue.Node<E>
 * @see java.util.concurrent.locks.LockSupport
 * @param <E>
 * @author zealot
 */
@SuppressWarnings("restriction")
public class Node<E> {
	volatile E item; // 元素名称
	volatile Node<E> next; // 后继节点

	/**
	 * Constructs a new node. Uses relaxed write because item can only be seen
	 * after publication via casNext.
	 */
	Node(E item) {
		UNSAFE.putObject(this, itemOffset, item);
	}

	boolean casItem(E cmp, E val) {
		return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
	}

	void lazySetNext(Node<E> val) {
		UNSAFE.putOrderedObject(this, nextOffset, val);
	}

	boolean casNext(Node<E> cmp, Node<E> val) {
		return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
	}

	// Unsafe 不允许直接调用，需要通过反射获取
	private static final sun.misc.Unsafe UNSAFE = UnsafeHelper.unsafe();

	// 属性 item 偏移常量
	private static final long itemOffset;

	// 属性 next 偏移常量
	private static final long nextOffset;

	static {
		try {
			Class<?> k = Node.class;
			// 获取对象的属性偏移量
			itemOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("item"));
			nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
		} catch (Exception e) { // 需要抛出 RuntimeException 阻止 Jvm 正常运行
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		Node<String> a = new Node<>("asdas");
		System.out.println(a);
	}

}