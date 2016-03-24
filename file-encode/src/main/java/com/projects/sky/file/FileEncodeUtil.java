package com.projects.sky.file;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.File;
import java.nio.charset.Charset;

/**
 * 文件格式检测
 * 
 * @author zt
 */
public class FileEncodeUtil {

	/**
	 * 根据文件名得到文件编码格式
	 * 
	 * @param fileName
	 *            the file's name
	 * @return
	 */
	public static Charset getFileCharset(String fileName) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/*
		 * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
		 * 指示是否显示探测过程的详细信息，为false不显示。
		 */
		detector.add(new ParsingDetector(false));

		/*
		 * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
		 * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
		 * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
		 */
		detector.add(JChardetFacade.getInstance());

		// ASCIIDetector用于ASCII编码测定
		detector.add(ASCIIDetector.getInstance());

		// UnicodeDetector用于Unicode家族编码的测定
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		File f = new File(fileName);

		try {
			charset = detector.detectCodepage(f.toURI().toURL());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return charset;
	}
}