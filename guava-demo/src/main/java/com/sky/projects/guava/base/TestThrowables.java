package com.sky.projects.guava.base;

import java.io.IOException;
import org.junit.Test;
import com.google.common.base.Throwables;

/**
 * 1. RuntimeException propagate(Throwable) .<br />
 * ---如果 Throwable t 是Error或RuntimeException，直接抛出；<br />
 * ---否则把Throwable包装成RuntimeException抛出 返回类型是RuntimeException .<br />
 * 
 * 2.void propagateIfInstanceOf( Throwable, Class<X extends Exception>) throws X
 * ---Throwable 类型为X才抛出
 * 
 * 3.void propagateIfPossible(Throwable) .<br />
 * ---Throwable 类型为 Error 或 RuntimeException 才抛出<br />
 * 
 * 4. void propagateIfPossible( Throwable, Class<X extends Throwable>) throws X.<br />
 * ---Throwable 类型为X, Error或RuntimeException才抛出
 * 
 * 5. List<Throwable> getCausalChain(Throwable throwable).<br />
 * ---以list的方式得到throwable的异常链
 * 
 * 6. Throwable getRootCause(Throwable throwable).<br />
 * ---返回最底层的异常
 * 
 * 7. String getStackTraceAsString(Throwable throwable).<br />
 * ---返回printStackTrace string 类型的结果
 * 
 * Title: TestThrowables.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月20日
 */
public class TestThrowables {

	@Test
	public void testThrowables() {
		try {
			// 抛出异常
			throw new Exception();
		} catch (Throwable t) {

			// To get the stack trace as string
			String ss = Throwables.getStackTraceAsString(t);
			System.out.println("Exception : \n" + ss);
		}
	}

	@Test
	public void call() throws IOException {
		try {
			throw new IOException();
		} catch (Throwable t) {

			// 异常为 IOException 才抛出(即此处只处理 IOException 异常)
			Throwables.propagateIfInstanceOf(t, IOException.class);

			// 抛出异常(详情见上面描述)
			Throwables.propagate(t);
		}
	}

	@Test
	public void testPropagateIfPossible() throws Exception {
		try {
			throw new Exception();
		} catch (Throwable t) {
			// Throwable t 类型为X(Exception), Error或RuntimeException才抛出
			Throwables.propagateIfPossible(t, Exception.class);

			// 抛出异常(详情见上面描述)
			Throwables.propagate(t);
		}
	}

}