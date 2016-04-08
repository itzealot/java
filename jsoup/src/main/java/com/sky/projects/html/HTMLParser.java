package com.sky.projects.html;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Java Program to parse/read HTML documents from File using Jsoup library.
 * Jsoup is an open source library which allows Java developer to parse HTML
 * files and extract elements, manipulate data, change style using DOM, CSS and
 * JQuery like method.
 *
 * @author zealot
 */
public class HTMLParser {
	private String HTMLString = "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>JSoup Example</title>" + "</head>"
			+ "<body>" + "<table><tr><td><h1>HelloWorld</h1></tr>" + "</table>" + "</body>" + "</html>";

	@Test
	public void testParseHTMLString() {
		// Parse HTML String using JSoup library

		// To get Document by Jsoup.parse(String HTMLString) method
		Document html = Jsoup.parse(HTMLString);

		// To get the html's title
		String title = html.title();
		System.out.println("After parsing, Title : " + title);

		// To get the head
		Element head = html.head();
		System.out.println("head's html : " + head.html());

		// To get the body
		Element body = html.body();
		System.out.println("body : \n" + body);

		// To get the element's text by tag
		String h1 = html.body().getElementsByTag("h1").text();
		System.out.println("Afte parsing, Heading : " + h1);
	}

	/**
	 * 1. 假如你有一个HTML片断 (比如. 一个 div 包含一对 p 标签; 一个不完整的HTML文档)
	 * 想对它进行解析。这个HTML片断可以是用户提交的一条评论或在一个CMS页面<br />
	 * 
	 * 2. 明确将用户输入作为 body片段处理，以确保用户所提供的任何糟糕的HTML都将被解析成body元素。<br />
	 *
	 * 3. Document.body() 方法能够取得文档body元素的所有子元素，与 doc.getElementsByTag("body")相同。
	 * 
	 * 4. 假如你可以让用户输入HTML内容，那么要小心避免跨站脚本攻击。利用基于 Whitelist 的清除器和 clean(String
	 * bodyHtml, Whitelist whitelist)方法来清除用户输入的恶意内容。
	 */
	@Test
	public void testParseHtmlSlice() {

		String html = "<div><p>Lorem ipsum.</p>";
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		System.out.println(body);
	}

	/**
	 * 1. connect(String url) 方法创建一个新的 Connection, 和 get()
	 * 取得和解析一个HTML文件。如果从该URL获取HTML时发生错误，便会抛出 IOException，应适当处理。
	 * 
	 * 2. Connection 接口还提供一个方法链来解决特殊请求，具体如下： Document doc =
	 * Jsoup.connect("http://example.com").data("query", "Java")
	 * .userAgent("Mozilla").cookie("auth", "token").timeout(3000).post();
	 * 
	 * 2.1 这个方法只支持Web URLs (http和https 协议)<br />
	 * 
	 * 
	 */
	@Test
	public void testParseURL() {
		String title = null;
		// JSoup Example 2 - Reading HTML page from URL
		Document doc = null;
		try {
			doc = Jsoup.connect("http://example.com/").get();
			title = doc.title();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Jsoup Can read HTML page from URL, title : " + title);
	}

	/**
	 * 1. 从一个文件加载一个文档(在本机硬盘上有一个HTML文件，需要对它进行解析从中抽取数据或进行修改。)
	 * 
	 * 2. baseUri 参数用于解决文件中URLs是相对路径的问题。如果不需要可以传入一个空的字符串。
	 * 
	 * 3. 另外还有一个方法parse(File in, String charsetName) ，它使用文件的路径做为 baseUri。
	 * 这个方法适用于如果被解析文件位于网站的本地文件系统，且相关链接也指向该文件系统。
	 */
	@Test
	public void testParseFile() {
		Document htmlFile = null;
		String title = null;
		File input = new File("input.html");
		System.out.println(input.getAbsolutePath());

		try {
			htmlFile = Jsoup.parse(input, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (htmlFile == null) {
			return;
		}
		// right
		title = htmlFile.title();
		Element div = htmlFile.getElementById("login");
		String cssClass = div.className(); // getting class form HTML element

		System.out.println("Jsoup can also parse HTML file directly");
		System.out.println("title : " + title);
		System.out.println("class of div tag : " + cssClass);
	}
}