package com.zt.test.base;

import org.junit.Test;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * method, explanation, Exception
 * 
 * 1. checkArgument(boolean) : .<br />
 * 检查boolean是否为true，用来检查传递给方法的参数。 * IllegalArgumentException.<br />
 * 
 * 2. checkNotNull(T) : 检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull. *
 * NullPointerException.<br />
 * 
 * 3. checkState(boolean) : 用来检查对象的某些状态。 IllegalStateException.<br />
 * 
 * 4. checkElementIndex(int index, int size) 检查index作为索引值对某个列表、字符串或数组是否有效.<br />
 * index>=0 && index<size * IndexOutOfBoundsException.<br />
 * 
 * 5. checkPositionIndex(int index, int size) 检查index作为位置值对某个列表、字符串或数组是否有效.<br />
 * index>=0 && index<=size * IndexOutOfBoundsException.<br />
 * 
 * 6. checkPositionIndexes(int start, int end, int size) 检查[start,
 * end]表示的位置范围对某个列表、字符串或数组是否有效 * IndexOutOfBoundsException
 * 
 * Title: TestPreconditions.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月8日
 */
public class TestPreconditions {
	@Test
	public void testCheckNotNull() {
		// 检查 value 是否为 null ,是则抛出 NullPointerException
		System.out.println(checkNotNull(4));
		checkNotNull(null);
	}
}
