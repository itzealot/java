package com.sky.projects.servlet.proxy.http.impl;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.sky.projects.servlet.proxy.common.Https;
import com.sky.projects.servlet.proxy.http.HttpClientHandler;

/**
 * Http客户端处理器，用于处理远程调用的实现类
 * 
 * @author zt
 *
 */
public class HttpClientHandlerImpl implements HttpClientHandler {

	private String id = "1";
	private String url = "http://localhost:8080/";
	private String protocol = "SOAP";

	public HttpClientHandlerImpl() {
		super();
	}

	public HttpClientHandlerImpl(String id, String url, String protocol) {
		super();
		this.id = id;
		this.url = url;
		this.protocol = protocol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String doHandler() {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		// 返回信息
		String message = "";
		try {
			// 1. get CloseableHttpClient object
			httpClient = HttpClients.createDefault();

			// 2. get HttpPost object
			HttpPost post = new HttpPost(url);

			// 为 SOAP 协议
			if ("SOAP".compareToIgnoreCase(protocol) == 0) {
				StringEntity entiy = new StringEntity(getSOAP(), "UTF-8");

				post.setEntity(entiy);
				post.setHeader("Content-Type", "text/xml; charset=UTF-8");
			} else {// 为REST 协议

			}

			/*
			 * 3. To get CloseableHttpResponse object by executing the HttpPost
			 * object by HttpClient object's method
			 */
			httpResponse = httpClient.execute(post);

			// To deal with the status
			StatusLine line = httpResponse.getStatusLine();
			int statusCode = line.getStatusCode();
			if (statusCode == 200) {
				// 响应成功则打印信息
				message = Https.getContentFrom(httpResponse);
			} else if (statusCode == 404) {
				message = "404";
			} else if (statusCode == 500) {
				message = "500";
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			Https.close(httpClient, httpResponse);
		}

		System.out.println("response message: " + message);

		return message;
	}

	public String getSOAP() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
		buffer.append(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://impl.service.ws.gateway.projects.apusic.com/\">");
		buffer.append(" <soapenv:Header/>");
		buffer.append(" <soapenv:Body>");
		buffer.append(" <ser:get>");
		buffer.append(" <arg0>1</arg0>");
		buffer.append(" </ser:get>");
		buffer.append(" </soapenv:Body>");
		buffer.append(" </soapenv:Envelope>");

		return buffer.toString();
	}
}
