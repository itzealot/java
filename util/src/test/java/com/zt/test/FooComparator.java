package com.zt.test;

import java.util.Comparator;

public class FooComparator implements Comparator<Foo> {

	@Override
	public int compare(Foo f1, Foo f2) {
		// num 从小到大排序
		return f1.getNum() - f2.getNum();

		// num 从大到小排序
		// return f2.getNum() - f1.getNum();
	}
}