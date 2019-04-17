package com.piggy.coffee.https.utils;

import java.io.IOException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioHttpClient {
	private static final Logger log = LoggerFactory.getLogger(NioHttpClient.class);

	final static PoolingHttpClientConnectionManager CONN_MGR;
	final static org.apache.http.client.HttpClient CLIENT;

	static {
		SSLConnectionSocketFactory sf = null;

		try {
			// 信任所有
			SSLContext sslc = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
			sf = new SSLConnectionSocketFactory(sslc);
		} catch (Exception e) {
			log.error("Create socket factory failed.", e);
		}

		final Registry sfr = RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", null != sf ? sf : SSLConnectionSocketFactory.getSocketFactory()).build();

		CONN_MGR = new PoolingHttpClientConnectionManager(sfr);
		CONN_MGR.setDefaultMaxPerRoute(2048);
		// CONN_MGR.setMaxTotal(512);
		// CONN_MGR.setValidateAfterInactivity(1000);

		CLIENT = HttpClientBuilder.create()
				// 这里主要用来避免使用http链接请求实际为https的地址
				.setSSLSocketFactory(sf).setConnectionManager(CONN_MGR).build();
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet("https://127.0.0.1:8443/hello");
		CLIENT.execute(request);

	}
}
