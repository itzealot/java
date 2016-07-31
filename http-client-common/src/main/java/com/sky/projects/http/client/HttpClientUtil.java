package com.sky.projects.http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient Util
 * 
 * @author zealot
 *
 */
public final class HttpClientUtil {
	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	private static final String EMPTY_STR = "";
	private static final String UTF_8 = "UTF-8";

	static {
		// 整个连接池最大连接数
		cm.setMaxTotal(50);
		// 每路由最大连接数，默认值是2
		cm.setDefaultMaxPerRoute(5);
	}

	/**
	 * 通过连接池获取 HttpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {
		return HttpClients.custom().setConnectionManager(cm).build();
	}

	public static String httpGetRequest(String url) {
		return resultOf(new HttpGet(url));
	}

	public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
		return resultOf(
				new HttpGet(new URIBuilder().setPath(url).setParameters(covertParams2NameValuePairs(params)).build()));
	}

	public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
			throws URISyntaxException {
		HttpGet httpGet = new HttpGet(
				new URIBuilder().setPath(url).setParameters(covertParams2NameValuePairs(params)).build());

		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), param.getValue().toString());
		}

		return resultOf(httpGet);
	}

	public static String httpPostRequest(String url) {
		return resultOf(new HttpPost(url));
	}

	public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(covertParams2NameValuePairs(params), UTF_8));
		return resultOf(httpPost);
	}

	/**
	 * Http Post request
	 * 
	 * @param url
	 * @param headers
	 *            the keys and values will be put into Http Header
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost post = new HttpPost(url);

		// add headers
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			post.addHeader(param.getKey(), param.getValue().toString());
		}

		post.setEntity(new UrlEncodedFormEntity(covertParams2NameValuePairs(params), UTF_8));

		return resultOf(post);
	}

	private static ArrayList<NameValuePair> covertParams2NameValuePairs(Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();

		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
		}

		return pairs;
	}

	/**
	 * 处理 Http 请求
	 * 
	 * @param request
	 * @return
	 */
	private static String resultOf(HttpRequestBase request) {
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;

		try {
			response = httpClient.execute(request);

			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// long len = entity.getContentLength();// -1 表示长度未知
				String result = EntityUtils.toString(entity);

				// httpClient.close();
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO
				}
			}
		}

		return EMPTY_STR;
	}

	private HttpClientUtil() {
	}

}