package com.zt.projects.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * 测试 REST 请求
 * 
 * @author a
 *
 */
public class TestRest {
	private String url = "http://localhost:8080/spring-ws-web/userServices";
	private String url2 = "http://localhost:8080/spring-ws-web/userServices/2";

	@Test
	public void testRestGetAll() {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;

		try {
			httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
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

	@Test
	public void testRestGet() {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;

		try {
			httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url2);
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
	public void testGetContent(CloseableHttpResponse response) throws Exception {
		// 读取内容
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity));
	}
}
