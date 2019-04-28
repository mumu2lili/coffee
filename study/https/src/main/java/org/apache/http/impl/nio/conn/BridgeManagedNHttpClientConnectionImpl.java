package org.apache.http.impl.nio.conn;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.logging.Log;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.MessageConstraints;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.NHttpMessageParserFactory;
import org.apache.http.nio.NHttpMessageWriterFactory;
import org.apache.http.nio.conn.ManagedNHttpClientConnection;
import org.apache.http.nio.reactor.IOSession;
import org.apache.http.nio.util.ByteBufferAllocator;

public class BridgeManagedNHttpClientConnectionImpl extends ManagedNHttpClientConnectionImpl
		implements ManagedNHttpClientConnection {

	public BridgeManagedNHttpClientConnectionImpl(String id, Log log, Log headerlog, Log wirelog, IOSession iosession,
			int buffersize, int fragmentSizeHint, ByteBufferAllocator allocator, CharsetDecoder chardecoder,
			CharsetEncoder charencoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy,
			ContentLengthStrategy outgoingContentStrategy, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory,
			NHttpMessageParserFactory<HttpResponse> responseParserFactory) {
		super(id, log, headerlog, wirelog, iosession, buffersize, fragmentSizeHint, allocator, chardecoder, charencoder,
				constraints, incomingContentStrategy, outgoingContentStrategy, requestWriterFactory,
				responseParserFactory);

	}

	public boolean isRequestNotSendComplete() {
		return super.hasBufferedOutput;
	}

	public long getDataLengthTransferred() {
		return outTransportMetrics.getBytesTransferred();
	}

	public ContentEncoder getContentEncoder() {
		return this.contentEncoder;
	}
}
