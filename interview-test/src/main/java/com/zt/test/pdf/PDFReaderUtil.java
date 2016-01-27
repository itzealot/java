package com.zt.test.pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReaderUtil {

	/**
	 * To write info from String src
	 * 
	 * @param src
	 *            the pdf's file name
	 * @param dir
	 *            the dir's file name
	 */
	public static void wirteIntoFile(String src, String dir) {
		try {
			// 加载src 代表的PDF文件
			PDDocument doc = PDDocument.load(src);

			// 使用 PDFTextStripper 提取文本
			PDFTextStripper stripper = new PDFTextStripper();

			// 返回提取的文字字符串
			String content = stripper.getText(doc);

			System.out.println(content);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
