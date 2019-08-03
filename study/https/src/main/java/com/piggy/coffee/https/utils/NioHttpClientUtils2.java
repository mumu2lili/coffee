package com.piggy.coffee.https.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.http.ConnectionClosedException;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.BridgeHttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guange on 30/10/2016.
 */
public class NioHttpClientUtils2 {
	private static final Logger log = LoggerFactory.getLogger(NioHttpClientUtils2.class);

	public static CloseableHttpAsyncClient getClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000) // 建立连接的超时时间,即三次握手的超时时间。
				.setSocketTimeout(10000) // 请求数据传输的超时时间。
				.setConnectionRequestTimeout(1000) // 从连接池获取连接的超时时间。
				.build();
		CloseableHttpAsyncClient httpclient = BridgeHttpAsyncClientBuilder.create()
				.setDefaultRequestConfig(requestConfig).setMaxConnPerRoute(6).setMaxConnTotal(60).setBodyLength(0)
				.build();

		httpclient.start();

		return httpclient;

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(CloseableHttpAsyncClient httpclient, String url, Map param) {
		long currentTimeMillis = System.currentTimeMillis();
		String result = "";
		try {
			HttpPost request = new HttpPost(url);
			// conn.setRequestProperty("Accept", "*/*");
			// conn.setRequestProperty("Connection", "Keep-Alive");

			StringBuilder sb = new StringBuilder();
			for (Object key : param.keySet()) {
				sb.append(key + "=" + param.get(key));
				sb.append("&");
			}
			String data = sb.toString();
			data = data.substring(0, data.length() - 1);

			log.debug("POST: " + url + " DATA: " + data);
			ContentType contentType = ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8);
			StringEntity entity = new StringEntity(data, contentType);
			request.setEntity(entity);

			httpclient.execute(request, new FutureCallback<HttpResponse>() {

				@Override
				public void failed(Exception ex) {
					if (ex instanceof ConnectionClosedException) {

					} else {
						log.error("请求发送失败", ex);
					}
				}

				@Override
				public void completed(HttpResponse resp) {

				}

				@Override
				public void cancelled() {

				}
			});
		} catch (Exception e) {
			log.error("发送 POST 请求出现异常！", e);
		}
		log.info("结果返回时间0{}", System.currentTimeMillis() - currentTimeMillis);
		return result;

	}

	public static void close(CloseableHttpAsyncClient httpclient) {
		try {
			httpclient.close();
		} catch (IOException e) {
			// ignore quietly
		}
	}

}
