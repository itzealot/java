package com.sky.projects.html;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DocumentDownload {
	public String url = "https://s.1688.com/selloffer/--1031620.html";
	public String url2 = "https://s.1688.com/selloffer/--1031620.html?beginPage=?&offset=?";

	public String append(int page) {
		if (page <= 0) {
			return url;
		}

		return url + "?beginPage=" + page;
	}

	public static void main(String[] args) {
		Document doc = null;

		DocumentDownload load = new DocumentDownload();

		try {
			doc = Jsoup.connect(load.url).get();

			Elements eles = doc.getElementsByClass("sm-offer-companyName");

			for (Element e : eles) {
				String result = e.toString();
				System.out.println(result);
				getElement(result);
				System.out.println();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ElementInfo getElement(String source) {

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ElementInfo info = new ElementInfo();

		int index = source.indexOf("href=\"");

		String s = source.substring(index + 6);
		int index2 = s.indexOf("\"");

		String href = "";

		href = s.substring(0, index2);

		System.out.println("href = " + href);

		String title = "";

		index = source.indexOf("title=\"");
		s = source.substring(index + 7);

		index2 = s.indexOf("\"");

		title = s.substring(0, index2);

		System.out.println("title = " + title);

		info.setHref(href);
		info.setTitle(title);

		return info;
	}
}
