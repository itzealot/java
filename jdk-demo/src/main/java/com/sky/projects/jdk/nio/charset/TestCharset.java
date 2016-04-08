package com.sky.projects.jdk.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

import org.junit.Test;

public class TestCharset {
	@Test
	public void testEncoderAndDncoder() throws Exception {

		// 1. 通过编码字符例如"ISO-8859-1" 得到Charset 对象
		Charset latin1 = Charset.forName("ISO-8859-1"); // 只能表示的英文字符

		// 2. 根据 Charset 对象得到编码器与解码器
		CharsetEncoder encoder = latin1.newEncoder(); // 得到编码器
		CharsetDecoder decoder = latin1.newDecoder(); // 得到解码器

		// 3. 进行编码与解码操作，使用字符缓存区(CharBuffer)
		CharBuffer cb = CharBuffer.wrap("MLDN");

		// 对内容进行编码并存在缓冲区中
		ByteBuffer buf = encoder.encode(cb);
		System.out.println(decoder.decode(buf));
	}

	/**
	 * 获得全部可以的字符集 编码
	 * 
	 * Title: testGetAllCharsets.<br />
	 * Description: .<br />
	 */
	@Test
	public void testGetAllCharsets() {
		// 1. 得到全部可用的字符集
		SortedMap<String, Charset> all = null;
		all = Charset.availableCharsets();

		// 2. 遍历输出
		Iterator<Map.Entry<String, Charset>> iter = null;
		iter = all.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Charset> me = iter.next();
			System.out.println(me.getKey() + " --> " + me.getValue());
		}
	}
}
