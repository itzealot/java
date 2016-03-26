package com.zt.projects.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestSoapInSpringBoot {

	/**
	 * The getAll soap message
	 * 
	 * @return
	 */
	public static String getAllSOAP() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
		buffer.append(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://impl.service.ws.gateway.projects.apusic.com/\">");
		buffer.append(" <soapenv:Header/>");
		buffer.append(" <soapenv:Body>");
		buffer.append(" <ser:getAll />");
		buffer.append(" </soapenv:Body>");
		buffer.append(" </soapenv:Envelope>");
		return buffer.toString();
	}

	@Test
	public void testGetAllSOAP() {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;

		try {
			httpclient = HttpClients.createDefault();

			HttpPost post = new HttpPost("http://localhost:8181/spring-ws-web/userService?wsdl");
			StringEntity entiy = new StringEntity(getAllSOAP(), "UTF-8");

			post.setEntity(entiy);
			post.setHeader("Content-Type", "text/xml; charset=UTF-8");

			response = httpclient.execute(post);

			// To deal with the status
			StatusLine line = response.getStatusLine();
			int statusCode = line.getStatusCode();
			if (statusCode == 200) {
				testGetContent(response);
			} else if (statusCode == 404) {
				System.out.println(404);
			} else if (statusCode == 500) {
				System.out.println(500);
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// To close the connection
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 测试响应的内容
	 * 
	 * @param response
	 * @throws Exception
	 */
	public static void testGetContent(CloseableHttpResponse response) throws Exception {
		// 读取内容
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity));
	}
}
