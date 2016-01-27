package com.zt.test.api.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.Test;

public class TestZipRar {

	/**
	 * 使用GZIPOutputStream 进行压缩
	 */
	@Test
	public void testGZIPWriteIntoRar() {
		BufferedOutputStream outputStream = null;
		try {
			// 1. 根据 文件路径 创建 FileOutputStream
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\zengtao\\Desktop\\Java网络编程\\test.rar");

			// 2. 根据FileOutputStream 创建 GZIPOutputStream
			GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
			// 3. 创建根据GZIPOutputStream 创建 BufferedOutputStream
			outputStream = new BufferedOutputStream(gzipOutputStream);

			// 创建test.rar且写入信息
			String message = "I love you";
			outputStream.write(message.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用GZIPInputStream 输入流进行解压并输出数据
	 */
	@Test
	public void testGZIPReadFromRar() {
		BufferedReader reader = null;
		GZIPInputStream inputStream = null;
		try {
			// 1. 根据文件路径创建 FileInputStream
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zengtao\\Desktop\\Java网络编程\\test.rar");

			// 2. 根据FileInputStream 创建 GZIPInputStream
			inputStream = new GZIPInputStream(fileInputStream);

			// 3. 根据GZIPInputStream 创建 InputStreamReader
			InputStreamReader in = new InputStreamReader(inputStream);

			// 4. 根据InputStreamReader 创建 BufferedReader
			reader = new BufferedReader(in);
			String result = "";
			while ((result = reader.readLine()) != null) {
				System.out.println(result);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
