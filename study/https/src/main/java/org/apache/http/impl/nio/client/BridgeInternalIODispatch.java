package org.apache.http.impl.nio.client;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.nio.DefaultNHttpClientConnection;
import org.apache.http.impl.nio.conn.BridgeManagedNHttpClientConnectionImpl;
import org.apache.http.nio.NHttpClientEventHandler;

public class BridgeInternalIODispatch extends InternalIODispatch {
	private final Log log = LogFactory.getLog(getClass());

	/**
	 * 请求体长度
	 */
	private long bodyLength = -1;
	private CloseableHttpAsyncClient client;
	private AtomicInteger times = new AtomicInteger(0);

	public BridgeInternalIODispatch(NHttpClientEventHandler handler, long entityLength,
			CloseableHttpAsyncClient client) {
		super(handler);
		this.bodyLength = entityLength;
		this.client = client;
	}

	@Override
	protected void onOutputReady(DefaultNHttpClientConnection conn) {
		int count = 0;
		if (conn instanceof BridgeManagedNHttpClientConnectionImpl) {
			BridgeManagedNHttpClientConnectionImpl c = (BridgeManagedNHttpClientConnectionImpl) conn;
			if (c.isRequestNotSendComplete()) {// 需要发送数据
				count = times.incrementAndGet();
			} else { // 收到server 确认
				count = times.decrementAndGet();
			}
		}

		super.onOutputReady(conn);
		if (conn instanceof BridgeManagedNHttpClientConnectionImpl) {
			BridgeManagedNHttpClientConnectionImpl c = (BridgeManagedNHttpClientConnectionImpl) conn;
			if (c.isRequestNotSendComplete()) {
				// do nothing
			} else {
				long length = c.getDataLengthTransferred();
				if (length >= this.bodyLength) {
					if (count % 2 == 1) {
						log.info("请求已经发送完毕, 总长度是 " + length);
					} else {
						log.info("请求已经发送完毕, 总长度是 " + length + ",收到服务的确认,关闭连接");
						try {
							conn.close();
							client.close();
						} catch (IOException e) {
							log.error("nio 关闭异常", e);
						}
					}
				}
			}
		}

	}

}
