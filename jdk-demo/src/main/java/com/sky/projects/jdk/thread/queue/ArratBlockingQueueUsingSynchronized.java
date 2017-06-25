package com.sky.projects.jdk.thread.queue;

/**
 * 使用 synchronized 搭配{@link Object#wait}与{@link Object#notify} 实现
 * 
 * @author zealot
 *
 * @param <T>
 */
public class ArratBlockingQueueUsingSynchronized<T> implements BlockingQueue<T> {

	private final Object[] datas;
	private final int capacity;
	private int nextPutIndex;
	private int nextTakeIndex;
	private int size;

	public ArratBlockingQueueUsingSynchronized() {
		this(32);
	}

	public ArratBlockingQueueUsingSynchronized(int capacity) {
		super();
		this.capacity = capacity;
		this.datas = new Object[this.capacity];
	}

	@Override
	public T peek() {
		synchronized (datas) {// 共享数据是 datas，必须使用其作为锁
			while (isEmpty()) { // 队列为空则消费者阻塞
				try {
					datas.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

			return dequeue();
		}
	}

	@SuppressWarnings("unchecked")
	private T dequeue() {
		T e = (T) datas[nextTakeIndex];
		datas[nextTakeIndex] = null;
		size--;
		System.out.println(Thread.currentThread().getName() + " finish peek a data=" + e + ", size=" + size);
		nextTakeIndex = (nextTakeIndex + 1) % capacity;
		datas.notify(); // 唤醒一个等待进程
		return e;
	}

	private boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void put(T e) throws InterruptedException {
		synchronized (datas) {// 共享数据是 datas，必须使用其作为锁
			while (isFull()) { // 队列已满则生产者阻塞
				datas.wait();
			}

			enqueue(e);
		}
	}

	private void enqueue(T e) {
		datas[nextPutIndex] = e;
		size++;
		if (e == null || e.toString().isEmpty()) {
			throw new RuntimeException("error data in queue.");
		}
		System.out.println(Thread.currentThread().getName() + " finish put a data=" + e + ", size=" + size);
		nextPutIndex = (nextPutIndex + 1) % capacity;
		datas.notify(); // 唤醒一个等待进程
	}

	private boolean isFull() {
		return size == capacity;
	}

}
