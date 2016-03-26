package com.sky.projects.servlet.proxy.common;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public final class Https {
	/**
	 * To close CloseableHttpClient and CloseableHttpResponse
	 * 
	 * @param httpClient
	 * @param httpResponse
	 */
	public static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
		try {
			// there is nothing
		} finally {
			// To close the connection
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据响应对象(CloseableHttpResponse) response 返回结果信息
	 * 
	 * @param response
	 * @return
	 */
	public static String getContentFromResponse(CloseableHttpResponse response) {
		if (response == null) {
			return "";
		}
		// 读取内容
		HttpEntity entity = response.getEntity();
		try {
			return EntityUtils.toString(entity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
