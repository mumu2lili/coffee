package com.piggy.coffee.https.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Consts;
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
public class NioHttpClientUtils {
	private static final Logger log = LoggerFactory.getLogger(NioHttpClientUtils.class);

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			if (StringUtils.isNotEmpty(param)) {
				urlNameString += "?" + param;
			}
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.error("发送GET请求出现异常！", e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
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
	public static String sendPost(String url, Map param) {
		long currentTimeMillis = System.currentTimeMillis();
		String result = "";
		try {
			HttpPost request = new HttpPost(url);
			// conn.setRequestProperty("Accept", "*/*");
			// conn.setRequestProperty("Connection", "Keep-Alive");

			StringBuffer stringBuffer = new StringBuffer();
			for (Object key : param.keySet()) {
				stringBuffer.append(key + "=" + param.get(key));
				stringBuffer.append("&");
			}
			String data = stringBuffer.toString();
			data = data.substring(0, data.length() - 1);

			log.debug("POST: " + url + " DATA: " + data);
			ContentType contentType = ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8);
			StringEntity entity = new StringEntity(data, contentType);
			request.setEntity(entity);

			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000) // 建立连接的超时时间,即三次握手的超时时间。
					.setSocketTimeout(10000) // 请求数据传输的超时时间。
					.setConnectionRequestTimeout(1000) // 从连接池获取连接的超时时间。
					.build();
			CloseableHttpAsyncClient httpclient = BridgeHttpAsyncClientBuilder.create()
					.setDefaultRequestConfig(requestConfig).setBodyLength(entity.getContentLength()).build();
			httpclient.start();
			Future<HttpResponse> future = httpclient.execute(request, new FutureCallback<HttpResponse>() {

				@Override
				public void failed(Exception ex) {
					if (ex instanceof ConnectionClosedException) {

					} else {
						log.error("请求发送失败");
					}
					close(httpclient);
				}

				@Override
				public void completed(HttpResponse resp) {
					close(httpclient);
				}

				@Override
				public void cancelled() {
					close(httpclient);
				}
			});
			// HttpResponse res = future.get();
			// result = EntityUtils.toString(res.getEntity());
			while (httpclient.isRunning()) {

			}
		} catch (Exception e) {
			log.error("发送 POST 请求出现异常！", e);
		}
		log.info("结果返回时间0{}", System.currentTimeMillis() - currentTimeMillis);
		return result;

	}

	private static void close(CloseableHttpAsyncClient httpclient) {
		try {
			httpclient.close();
		} catch (IOException e) {
			// ignore quietly
		}
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
	public static String sendPost(String url, Map param, int readTimeOut) {
		long currentTimeMillis = System.currentTimeMillis();
		String result = "";
		try {
			HttpPost request = new HttpPost(url);
			// conn.setRequestProperty("Accept", "*/*");
			// conn.setRequestProperty("Connection", "Keep-Alive");
			// conn.setReadTimeout(readTimeOut);

			StringBuffer stringBuffer = new StringBuffer();
			for (Object key : param.keySet()) {
				stringBuffer.append(key + "=" + param.get(key));
				stringBuffer.append("&");
			}
			String data = stringBuffer.toString();
			data = data.substring(0, data.length() - 1);

			log.debug("POST: " + url + " DATA: " + data);
			ContentType contentType = ContentType.create("application/x-www-form-urlencoded");
			StringEntity entity = new StringEntity(data, contentType);
			request.setEntity(entity);

			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000) // 建立连接的超时时间,即三次握手的超时时间。
					.setSocketTimeout(10000) // 请求数据传输的超时时间。
					.setConnectionRequestTimeout(1000) // 从连接池获取连接的超时时间。
					.build();
			CloseableHttpAsyncClient httpclient = BridgeHttpAsyncClientBuilder.create()
					.setDefaultRequestConfig(requestConfig).setBodyLength(entity.getContentLength()).build();
			httpclient.start();
			Future<HttpResponse> future = httpclient.execute(request, new FutureCallback<HttpResponse>() {

				@Override
				public void failed(Exception ex) {
					if (ex instanceof ConnectionClosedException) {

					} else {
						log.error("请求发送失败");
					}
					close(httpclient);
				}

				@Override
				public void completed(HttpResponse resp) {
					close(httpclient);
				}

				@Override
				public void cancelled() {
					close(httpclient);
				}
			});

			// future.get();
			while (httpclient.isRunning()) {

			}
		} catch (Exception e) {
			log.error("发送 POST 请求出现异常！", e);
		}
		log.info("结果返回时间0{}", System.currentTimeMillis() - currentTimeMillis);
		return result;

	}

	/**
	 * 向指定 URL 发送POST请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
