package com.projects.sky.util.xml;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.projects.sky.util.xml.XMLs;

public class TestXMLs {
	String path = "C:\\Users\\a\\Desktop\\wsdl.asmx.xml";
	String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<persons><person id=\"123123\"><id>123123</id><name>sdasdasd</name></person>"
			+ "<person id=\"123123\"><id>123123</id><name>sdasdasd</name></person>"
			+ "<person id=\"123123\"><id>123123</id><name>sdasdasd</name></person>"
			+ "<person id=\"123123\"><id>123123</id><name>sdasdasd</name></person>" + "</persons>";

	@Test
	public void testFormatBy() throws Exception {
		System.out.println(XMLs.format(new File(path)));
	}

	@Test
	public void testFormat() throws Exception {
		System.out.println(XMLs.format(content));
	}

	@Test
	public void testParse() throws IOException {
		System.out.println(XMLs.format(XMLs.parse(content)));
	}

	@Test
	public void testFrom() throws Exception {
		System.out.println(XMLs.read(path));
	}

	@Test
	public void testReset() throws Exception {
		String source = XMLs.format(content);
		System.out.println(source);
		source = XMLs.reset(source);
		System.out.println(source);
	}
}
