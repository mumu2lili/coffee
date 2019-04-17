package org.apache.http.impl.nio.client;

import java.io.IOException;

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

	public BridgeInternalIODispatch(NHttpClientEventHandler handler, long entityLength,
			CloseableHttpAsyncClient client) {
		super(handler);
		this.bodyLength = entityLength;
		this.client = client;
	}

	@Override
	protected void onOutputReady(DefaultNHttpClientConnection conn) {
		super.onOutputReady(conn);
		if (conn instanceof BridgeManagedNHttpClientConnectionImpl) {
			BridgeManagedNHttpClientConnectionImpl c = (BridgeManagedNHttpClientConnectionImpl) conn;
			if (c.isRequestNotSendComplete()) {
				// do nothing
			} else {
				long length = c.getDataLengthTransferred();
				log.info("请求已经发送完毕, 总长度是 " + length);
				if (length >= this.bodyLength) {
					try {
						// conn.close();
						client.close();
					} catch (IOException e) {
						log.error("nio 关闭异常", e);
					}
				}
			}
		}

	}

}
