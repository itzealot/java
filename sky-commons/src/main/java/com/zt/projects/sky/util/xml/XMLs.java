package com.zt.projects.sky.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.StringTokenizer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XML Util
 * 
 * @author zt
 *
 */
public class XMLs {

	/**
	 * To get xml String from the xml's path, the result will be same with the
	 * file's content.
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String from(String path) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		return document.asXML();
	}

	/**
	 * 将字符串转换为 xml 对应的 Document 对象
	 * 
	 * @param text
	 * @return
	 */
	public static Document parse(String text) {
		try {
			return DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			return null;
		}
	}

	/**
	 * To format the xml file by path
	 * 
	 * @param path
	 *            The xml file's path
	 * @return
	 * @throws Exception
	 */
	public static String formatBy(String path) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		return format(document);
	}

	/**
	 * 将格式化的 xml 字符串重置，即去掉所有格式化符号
	 * 
	 * @param content
	 * @return
	 */
	public static String reset(String content) {
		StringTokenizer tokenizer = new StringTokenizer(content);
		StringBuffer buffer = new StringBuffer();
		while (tokenizer.hasMoreElements()) {
			buffer.append(tokenizer.nextToken());
		}
		return buffer.toString();
	}

	/**
	 * To fromat the xml String
	 * 
	 * @param content
	 *            String
	 * @return
	 * @throws Exception
	 */
	public static String format(String content) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(content));
		return format(document);
	}

	/**
	 * To get OutputFormat object by indentSize and newLineAfterDeclaration
	 * 
	 * @param indentSize
	 *            int ：格式化空格数
	 * @param newLineAfterDeclaration
	 *            boolean ：与xml标记是否空行
	 * @return
	 */
	public static OutputFormat getOutputFormat(int indentSize, boolean newLineAfterDeclaration) {
		return getOutputFormat(indentSize, newLineAfterDeclaration, "UTF-8");
	}

	/**
	 * To get OutputFormat object by indentSize, newLineAfterDeclaration and
	 * encoding
	 * 
	 * @param indentSize
	 *            int ：格式化空格数
	 * @param newLineAfterDeclaration
	 *            boolean ：与xml标记是否空行
	 * @param encoding
	 *            编码格式
	 * 
	 * @return
	 */
	public static OutputFormat getOutputFormat(int indentSize, boolean newLineAfterDeclaration, String encoding) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setIndentSize(indentSize);
		format.setEncoding(encoding);
		format.setNewLineAfterDeclaration(newLineAfterDeclaration);
		return format;
	}

	/**
	 * To close Writer
	 * 
	 * @param writer
	 */
	public static void close(XMLWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * To format the document
	 * 
	 * @param document
	 *            Document
	 * @return
	 * @throws IOException
	 */
	public static String format(Document document) throws IOException {
		String result = "";
		XMLWriter writer = null;
		if (document != null) {
			try {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = getOutputFormat(4, false);
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				result = stringWriter.getBuffer().toString();
			} finally {
				close(writer);
			}
		}
		return result;
	}
}