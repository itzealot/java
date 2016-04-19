package com.sky.projects.dubbo;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<E> {
	private int mSize = 0;

	private List<E> mElements = new ArrayList<E>();

	public Stack() {
	}

	public void push(E ele) {
		if (mElements.size() > mSize)
			mElements.set(mSize, ele);

		mElements.add(ele);

		mSize++;
	}

	public E pop() {
		if (mSize == 0)
			throw new EmptyStackException();

		return mElements.set(--mSize, null);
	}

	public E peek() {
		if (mSize == 0)
			throw new EmptyStackException();

		return mElements.get(mSize - 1);
	}

	public E get(int index) {
		if (index >= mSize)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + mSize);

		return index < 0 ? mElements.get(index + mSize) : mElements.get(index);
	}

	public E set(int index, E value) {
		if (index >= mSize)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + mSize);

		return mElements.set(index < 0 ? index + mSize : index, value);
	}

	public E remove(int index) {
		if (index >= mSize)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + mSize);

		E ret = mElements.remove(index < 0 ? index + mSize : index);

		mSize--;

		return ret;
	}

	public int size() {
		return mSize;
	}

	public boolean isEmpty() {
		return mSize == 0;
	}

	public void clear() {
		mSize = 0;
		mElements.clear();
	}
}