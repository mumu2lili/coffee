package com.piggy.coffee.https.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

import org.apache.http.ConnectionClosedException;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.piggy.coffee.common.constant.ApiResultCsts;
import com.piggy.coffee.common.model.ApiResult;
import com.piggy.coffee.common.util.json.JsonUtils;

public final class NioHttpClientUtils3 {
	private static final Logger log = LoggerFactory.getLogger(NioHttpClientUtils3.class);

	public static CloseableHttpAsyncClient getClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000) // 建立连接的超时时间,即三次握手的超时时间。
				.setSocketTimeout(10000) // 请求数据传输的超时时间。
				.setConnectionRequestTimeout(1000) // 从连接池获取连接的超时时间。
				.build();
		CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientBuilder.create()
				.setDefaultRequestConfig(requestConfig).setMaxConnTotal(200).setMaxConnPerRoute(200).build();

		httpAsyncClient.start();

		return httpAsyncClient;

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数
	 */
	public static ApiResult<String> sendPost(CloseableHttpAsyncClient httpclient, String url, String param) {
		ApiResult<String> result = new ApiResult<>();
		long currentTimeMillis = System.currentTimeMillis();
		try {
			ContentType contentType = ContentType.create("application/json", Consts.UTF_8);
			StringEntity entity = new StringEntity(param, contentType);

			HttpPost request = new HttpPost(url);
			request.setEntity(entity);

			Future<HttpResponse> future = httpclient.execute(request, new FutureCallback<HttpResponse>() {
				@Override
				public void failed(Exception ex) {
					if (ex instanceof ConnectionClosedException) {

					} else {
						log.error("结果返回时间{}", System.currentTimeMillis() - currentTimeMillis);
						log.error("请求发送失败，数据:" + param, ex);
					}
				}

				@Override
				public void completed(HttpResponse resp) {
					log.info("结果返回时间{}", System.currentTimeMillis() - currentTimeMillis);
				}

				@Override
				public void cancelled() {
				}
			});
			HttpResponse response = future.get();
			result = getApiResult(response);
		} catch (Exception e) {
			result.setCode(ApiResultCsts.FAIL);
			log.error("发送 POST 请求出现异常！数据:" + param, e);
		}
		return result;
	}

	private static ApiResult<String> getApiResult(HttpResponse response) {
		ApiResult<String> result = new ApiResult<>();
		try {
			BufferedReader streamReader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			StringBuilder respomseStrBuilder = new StringBuilder();
			String inputStr = "";
			while ((inputStr = streamReader.readLine()) != null) {
				respomseStrBuilder.append(inputStr);
			}
			log.debug("发送 POST 请求,返回结果: {}", respomseStrBuilder);

			ApiResult<String> r = JsonUtils.toBean(respomseStrBuilder.toString(),
					new TypeReference<ApiResult<String>>() {
					});
			return r;
		} catch (Exception e) {
			result.setCode(ApiResultCsts.FAIL);
			log.error("发送 POST 请求,返回结果异常: {}", response, e);
		}
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
