package com.zt.util.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOM4XmlUtil {

	/**
	 * 
	 * @param path
	 * @param className
	 * @param attributeNames
	 * @throws Exception
	 */
	public static void parse(String path, String className,
			String... attributeNames) throws Exception {
		// 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器） 得到工厂实例
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// 2:获得具体的dom解析器 （通过工厂实例创建）
		DocumentBuilder db = dbf.newDocumentBuilder();

		// 3: 解析一个xml文档，获得Document对象（根结点）
		Document document = db.parse(new File(path));

		// 4.To get NodeList elements by Tag Name
		NodeList list = document.getElementsByTagName(className);

		// For each the NodeList
		int length = list.getLength();
		for (int i = 0; i < length; i++) {
			System.out.println("The " + (i + 1) + "st " + className + ":");
			// To get element
			Element element = (Element) list.item(i);

			for (int j = 0; j < attributeNames.length; j++) {
				String content = element
						.getElementsByTagName(attributeNames[j]).item(0)
						.getFirstChild().getNodeValue();

				System.out.println(attributeNames[j] + ": " + content);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\zengtao\\Desktop\\person.txt";
		
		parse(path, "Person", "name", "sex", "age");
	}
}
