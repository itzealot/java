package com.projects.sky.util.xml;

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
 * XML工具类
 * 
 * @author zt
 *
 */
public final class XMLs {

	/**
	 * 根据 xml 文件路径获取获取内容
	 * 
	 * @param path
	 *            the xml file path
	 * @return
	 * @throws Exception
	 */
	public static String read(String path) throws Exception {
		return read(new File(path));
	}

	/**
	 * 根据 xml 文件路径获取获取内容
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String read(File file) throws Exception {
		return new SAXReader().read(file).asXML();
	}

	/**
	 * 从文件中读取xml 内容并格式化
	 * 
	 * @param file
	 *            The xml file
	 * @return
	 * @throws Exception
	 */
	public static String format(File file) throws Exception {
		return format(new SAXReader().read(file));
	}

	/**
	 * To format the xml content String
	 * 
	 * 格式化 xml String
	 * 
	 * @param content
	 *            xml String
	 * @return 返回格式化后的 xml String
	 * @throws Exception
	 */
	public static String format(String content) throws Exception {
		return format(new SAXReader().read(new StringReader(content)));
	}

	/**
	 * To format the document
	 * 
	 * 格式化 Document 对象
	 * 
	 * @param document
	 *            Document
	 * @return
	 * @throws IOException
	 */
	public static String format(Document document) throws IOException {

		String result = "";
		XMLWriter writer = null;

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

		return result;
	}

	/**
	 * 将xml 字符串转换为 xml 对应的 Document 对象
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
	 * To get OutputFormat object by indentSize and newLineAfterDeclaration
	 * 
	 * 根据参数获取 OutputFormat 对象
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
	 * 根据参数获取OutputFormat 对象
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

	private static void close(XMLWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
			} finally {
				writer = null;
			}
		}
	}

	private XMLs() {
	}
}