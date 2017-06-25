package com.sky.projects.jdk.thread.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 ReentrantLock {@link ReentrantLock#lock}与{@link ReentrantLock#unlock} ，搭配
 * Condition {@link Condition#await}与{@link Condition#signal}
 * 
 * @author zealot
 *
 * @param <T>
 */
public class ArratBlockingQueueUsingLock<T> implements BlockingQueue<T> {

	/** ReentrantLock for modify */
	private final ReentrantLock lock;

	/** Condition for waiting puts */
	private Condition notFull;

	/** Condition for waiting takes */
	private Condition notEmpty;

	private final Object[] datas;
	private final int capacity;
	private int nextPutIndex;
	private int nextTakeIndex;
	private int size;

	public ArratBlockingQueueUsingLock() {
		this(32);
	}

	public ArratBlockingQueueUsingLock(int capacity) {
		super();
		this.capacity = capacity;
		this.lock = new ReentrantLock();
		this.notEmpty = lock.newCondition();
		this.notFull = lock.newCondition();
		this.datas = new Object[this.capacity];
	}

	@Override
	public T peek() {
		try {
			lock.lockInterruptibly();

			while (isEmpty()) { // 为空则阻塞
				notEmpty.await();
			}

			return dequeue();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	private T dequeue() {
		T e = (T) datas[nextTakeIndex];
		datas[nextTakeIndex] = null;
		size--;
		System.out.println(Thread.currentThread().getName() + " finish peek a data=" + e + ", size=" + size);
		nextTakeIndex = (nextTakeIndex + 1) % capacity;
		notFull.signal(); // put了一条数据，唤醒一个生产者
		return e;
	}

	private boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void put(T e) throws InterruptedException {
		try {
			lock.lockInterruptibly();

			while (isFull()) { // is full
				notFull.await();
			}

			enqueue(e);
		} finally {
			lock.unlock();
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
		notEmpty.signal(); // 唤醒一个
	}

	private boolean isFull() {
		return size == capacity;
	}

}
