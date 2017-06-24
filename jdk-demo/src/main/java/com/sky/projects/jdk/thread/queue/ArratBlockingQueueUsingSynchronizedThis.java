package com.sky.projects.jdk.thread.queue;

/**
 * 使用 synchronized 搭配{@link Object#wait}与{@link Object#notify} 实现
 * 
 * @author zealot
 *
 * @param <T>
 */
public class ArratBlockingQueueUsingSynchronizedThis<T> implements BlockingQueue<T> {

	// 循环队列
	private final Object[] datas;
	private final int capacity;
	private int nextPutIndex;
	private int nextTakeIndex;
	private int size;

	public ArratBlockingQueueUsingSynchronizedThis() {
		this(32);
	}

	public ArratBlockingQueueUsingSynchronizedThis(int capacity) {
		super();
		this.capacity = capacity;
		this.datas = new Object[this.capacity];
	}

	@Override
	public synchronized T peek() {
		while (isEmpty()) { // 队列为空则消费者阻塞
			try {
				this.wait(); // 此时锁为当前对象，使用 this.wait
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		return dequeue();
	}

	@SuppressWarnings("unchecked")
	private T dequeue() {
		T e = (T) datas[nextTakeIndex];
		datas[nextTakeIndex] = null;
		size--;
		System.out.println(Thread.currentThread().getName() + " finish peek a data=" + e + ", size=" + size);
		nextTakeIndex = (nextTakeIndex + 1) % capacity;
		this.notify(); // 唤醒一个等待进程，此时锁为当前对象
		return e;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public synchronized void put(T e) throws InterruptedException {
		while (isFull()) { // 队列已满则生产者阻塞
			this.wait(); // 此时锁为当前对象，使用 this.wait
		}

		enqueue(e);
	}

	private void enqueue(T e) {
		datas[nextPutIndex] = e;
		size++;
		if (e == null || e.toString().isEmpty()) {
			throw new RuntimeException("error data in queue.");
		}
		System.out.println(Thread.currentThread().getName() + " finish put a data=" + e + ", size=" + size);
		nextPutIndex = (nextPutIndex + 1) % capacity;
		this.notify(); // 唤醒一个等待进程
	}

	private boolean isFull() {
		return size == capacity;
	}

}
