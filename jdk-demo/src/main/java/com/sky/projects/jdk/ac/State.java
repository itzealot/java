package com.sky.projects.jdk.ac;

import java.util.HashSet;
import java.util.Set;

/**
 * A state represents an element in the Aho-Corasick tree.
 * 
 * 字典树对应的节点
 */
class State<T> {

	/**
	 * Arbitrarily chosen constant. If this state ends up getting deeper than
	 * THRESHOLD_TO_USE_SPARSE, then we switch over to a sparse edge
	 * representation. I did a few tests, and there's a local minima here. We
	 * may want to choose a more sophisticated strategy.
	 * 
	 * 层数阈值使用量
	 */
	private static final int THRESHOLD_TO_USE_SPARSE = 3;

	/** 节点深度 */
	private int depth;
	/** 子节点列表 */
	private EdgeList<T> edgeList;
	/** 失败指针 */
	private State<T> fail;
	/** 输出结果集 */
	private Set<T> outputs;

	public State(int depth) {
		this.depth = depth;

		// 根据depth选用不同的存储
		if (depth > THRESHOLD_TO_USE_SPARSE)
			this.edgeList = new SparseEdgeList<T>();
		else
			this.edgeList = new DenseEdgeList<T>();
		this.fail = null;
		// 此处代码不能修改为太大的初始化大小，否则大数据量会造成内存溢出
		this.outputs = new HashSet<T>(0);
	}

	/**
	 * 扩展单个字节为字典树
	 * 
	 * @param b
	 * @return
	 */
	public State<T> extend(byte b) {
		if (this.edgeList.get(b) != null)
			return this.edgeList.get(b);
		State<T> nextState = new State<T>(this.depth + 1);
		this.edgeList.put(b, nextState);
		return nextState;
	}

	/**
	 * 扩展字节数组为字典树
	 * 
	 * @param bytes
	 * @return
	 */
	public State<T> extendAll(byte[] bytes) {
		State<T> state = this;

		// 遍历字节数组并且循环向下扩展节点
		for (int i = 0; i < bytes.length; i++) {
			// 已经添加，则改变指向
			if (state.edgeList.get(bytes[i]) != null) {
				state = state.edgeList.get(bytes[i]);
			} else { // 扩展节点
				state = state.extend(bytes[i]);
			}
		}

		return state;
	}

	/**
	 * Returns the size of the tree rooted at this State. Note: do not call this
	 * if there are loops in the edgelist graph, such as those introduced by
	 * AhoCorasick.prepare().
	 * 
	 * 统计当前节点及子节点的大小
	 */
	public int size() {
		byte[] keys = edgeList.keys();
		int result = 1;
		for (int i = 0; i < keys.length; i++)
			result += edgeList.get(keys[i]).size();
		return result;
	}

	public State<T> get(byte b) {
		return this.edgeList.get(b);
	}

	public void put(byte b, State<T> s) {
		this.edgeList.put(b, s);
	}

	public byte[] keys() {
		return this.edgeList.keys();
	}

	public State<T> getFail() {
		return this.fail;
	}

	public void setFail(State<T> f) {
		this.fail = f;
	}

	public void addOutput(T o) {
		this.outputs.add(o);
	}

	public Set<T> getOutputs() {
		return this.outputs;
	}

	public void clear() {
		if (this.outputs != null) {
			this.outputs.clear();
		}

		if (this.edgeList != null) {
			this.edgeList.clear();
		}

		if (this.fail != null) {
			this.fail.clear();
		}

		this.edgeList = null;
		this.fail = null;
		this.outputs = null;
	}

}
