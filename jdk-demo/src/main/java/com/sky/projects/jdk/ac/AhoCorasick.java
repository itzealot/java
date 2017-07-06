package com.sky.projects.jdk.ac;

import java.util.Iterator;

/**
 * jon:
 * 
 * The main modifications from the original is to make this fully type-parameterized. 
 */

/**
 * <p>
 * An implementation of the Aho-Corasick string searching automaton. This
 * implementation of the
 * <a href="http://portal.acm.org/citation.cfm?id=360855&dl=ACM&coll=GUIDE"
 * target="_blank">Aho-Corasick</a> algorithm is optimized to work with bytes.
 * </p>
 * 
 * <p>
 * Example usage: <code><pre>
       AhoCorasick tree = new AhoCorasick();
       tree.add("hello".getBytes(), "hello");
       tree.add("world".getBytes(), "world");
       tree.prepare();

       Iterator searcher = tree.search("hello world".getBytes());
       while (searcher.hasNext()) {
           SearchResult result = searcher.next();
           System.out.println(result.getOutputs());
           System.out.println("Found at index: " + result.getLastIndex());
       }
   </pre></code>
 * </p>
 * 
 * <h2>Recent changes</h2>
 * <ul>
 * 
 * <li>Per user request from Carsten Kruege, I've changed the signature of
 * State.getOutputs() and SearchResults.getOutputs() to Sets rather than
 * Lists.</li>
 * 
 * </ul>
 * 
 * jon: tweaked to be type generic
 * 
 * KMP算法是单模式串的字符匹配算法，AC自动机是多模式串的字符匹配算法
 */
public class AhoCorasick<T> {

	/** 字典树的根节点 */
	private State<T> root;
	/** 是否完成字段叔的构建标志 */
	private volatile boolean prepared;

	public AhoCorasick() {
		this.root = new State<T>(0);
		this.prepared = false;
	}

	/**
	 * Adds a new keyword with the given output. During search, if the keyword
	 * is matched, output will be one of the yielded elements in
	 * SearchResults.getOutputs().
	 * 
	 * 新增关键字及对应的输出结果
	 */
	public void add(byte[] keyword, T output) {
		if (this.prepared)
			throw new IllegalStateException("can't add keywords after prepare() is called");
		State<T> lastState = this.root.extendAll(keyword);

		// 叶子节点保存输出结果
		lastState.addOutput(output);
	}

	/**
	 * Prepares the automaton for searching. This must be called before any
	 * searching().
	 * 
	 * 构建完成字段树，构造失败指针
	 */
	public void prepare() {
		if (!this.prepared) {
			this.prepareFailTransitions();
			this.prepared = true;
		}
	}

	/**
	 * 是否完成构建字段树
	 * 
	 * @return
	 */
	public boolean isPrpared() {
		return this.prepared;
	}

	/**
	 * Starts a new search, and returns an Iterator of SearchResults.
	 * 
	 * 开始搜索
	 */
	public Iterator<SearchResult<T>> search(byte[] bytes) {
		return new Searcher<T>(this, this.startSearch(bytes));
	}

	/**
	 * DANGER DANGER: dense algorithm code ahead. Very order dependent.
	 * Initializes the fail transitions of all states except for the root.
	 * 
	 * 构造下失败指针(层序遍历，使用队列)
	 * 
	 * 设这个节点上的字母为C，沿着他父亲的失败指针走，直到走到一个节点，他的儿子中也有字母为C的节点，然后把当前节点的失败指针指向那个字母也为C的儿子。如果一直走到了root都没找到，那就把失败指针指向root。
	 */
	private void prepareFailTransitions() {
		Queue<T> q = new Queue<T>();
		for (int i = 0; i < 256; i++)
			if (this.root.get((byte) i) != null) {
				this.root.get((byte) i).setFail(this.root);
				q.add(this.root.get((byte) i));
			}
		this.prepareRoot();
		while (!q.isEmpty()) {
			State<T> state = q.pop();
			byte[] keys = state.keys();
			for (int i = 0; i < keys.length; i++) {
				State<T> r = state;
				byte a = keys[i];
				State<T> s = r.get(a);
				q.add(s);
				r = r.getFail();
				while (r.get(a) == null)
					r = r.getFail();
				s.setFail(r.get(a));
				s.getOutputs().addAll(r.get(a).getOutputs());
			}
		}
	}

	/**
	 * Sets all the out transitions of the root to itself, if no transition yet
	 * exists at this point.
	 * 
	 * 初始化根节点
	 */
	private void prepareRoot() {
		for (int i = 0; i < 256; i++)
			if (this.root.get((byte) i) == null)
				this.root.put((byte) i, this.root);
	}

	/**
	 * Returns the root of the tree. Package protected, since the user probably
	 * shouldn't touch this.
	 */
	State<T> getRoot() {
		return this.root;
	}

	/**
	 * Begins a new search using the raw interface. Package protected.
	 */
	SearchResult<T> startSearch(byte[] bytes) {
		if (!this.prepared)
			throw new IllegalStateException("can't start search until prepare()");
		return continueSearch(new SearchResult<T>(this.root, bytes, 0));
	}

	/**
	 * Continues the search, given the initial state described by the
	 * lastResult. Package protected.
	 * 
	 * 匹配过程分两种情况：
	 * (1)当前字符匹配，表示从当前节点沿着树边有一条路径可以到达目标字符，此时只需沿该路径走向下一个节点继续匹配即可，目标字符串指针移向下个字符继续匹配；
	 * (2)当前字符不匹配，则去当前节点失败指针所指向的字符继续匹配，匹配过程随着指针指向root结束。
	 */
	SearchResult<T> continueSearch(SearchResult<T> lastResult) {
		byte[] bytes = lastResult.bytes;
		State<T> state = lastResult.lastMatchedState;
		for (int i = lastResult.lastIndex; i < bytes.length; i++) {
			byte b = bytes[i];

			if (state == null) {
				return null;
			}

			while (state.get(b) == null) {
				state = state.getFail();

				if (state == null) {
					return null;
				}
			}
			state = state.get(b);
			if (state.getOutputs() != null && state.getOutputs().size() > 0)
				return new SearchResult<T>(state, bytes, i + 1);
		}
		return null;
	}

	/**
	 * clear temp data，此处只是清除根节点下的引用，若需要清除整棵树的引用，需要遍历整棵树，利用Stack结构进行
	 */
	public void clear() {
		this.prepared = false;

		// clear
		this.root.clear();
		
		// clear fail reference
		State<T> next = this.root.getFail();
		while (next != null) {
			State<T> temp = next;
			next = next.getFail();
			temp.clear();
		}
	}

	/***
	 * 递归删除构建的数，此方法如果栈深度不够，可能会抛出异常，建议设置 -Xss 大小，如:-Xss5M
	 * 
	 * 该方法好像存在问题
	 * 
	 * @param state
	 */
	@Deprecated
	public void clear0(State<T> state) {
		if (state == null) {
			return;
		}

		// 遍历子节点，递归删除子节点空间
		for (byte i = 0; i < 256; i++) {
			State<T> child = state.get(i);
			if (child != null) {
				clear0(child); // 递归删除子节点
			}
		}

		// 清除节点空间
		state.clear();
	}
}
