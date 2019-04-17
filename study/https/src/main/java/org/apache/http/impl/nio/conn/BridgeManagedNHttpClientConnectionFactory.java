package org.apache.http.impl.nio.conn;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.nio.codecs.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.nio.codecs.DefaultHttpResponseParserFactory;
import org.apache.http.nio.NHttpMessageParserFactory;
import org.apache.http.nio.NHttpMessageWriterFactory;
import org.apache.http.nio.conn.ManagedNHttpClientConnection;
import org.apache.http.nio.conn.NHttpConnectionFactory;
import org.apache.http.nio.reactor.IOEventDispatch;
import org.apache.http.nio.reactor.IOSession;
import org.apache.http.nio.util.ByteBufferAllocator;
import org.apache.http.nio.util.HeapByteBufferAllocator;

public class BridgeManagedNHttpClientConnectionFactory implements NHttpConnectionFactory<ManagedNHttpClientConnection> {
	private final Log headerlog = LogFactory.getLog("org.apache.http.headers");
	private final Log wirelog = LogFactory.getLog("org.apache.http.wire");
	private final Log log = LogFactory.getLog(ManagedNHttpClientConnectionImpl.class);

	private static final AtomicLong COUNTER = new AtomicLong();

	// 使用 bridge
	public static final BridgeManagedNHttpClientConnectionFactory INSTANCE = new BridgeManagedNHttpClientConnectionFactory();

	private final ByteBufferAllocator allocator;
	private final NHttpMessageWriterFactory<HttpRequest> requestWriterFactory;
	private final NHttpMessageParserFactory<HttpResponse> responseParserFactory;

	public BridgeManagedNHttpClientConnectionFactory(
	            final NHttpMessageWriterFactory<HttpRequest> requestWriterFactory,
	            final NHttpMessageParserFactory<HttpResponse> responseParserFactory,
	            final ByteBufferAllocator allocator) {
	        super();
	        this.requestWriterFactory = requestWriterFactory != null ? requestWriterFactory :
	            DefaultHttpRequestWriterFactory.INSTANCE;
	        this.responseParserFactory = responseParserFactory != null ? responseParserFactory :
	            DefaultHttpResponseParserFactory.INSTANCE;
	        this.allocator = allocator != null ? allocator : HeapByteBufferAllocator.INSTANCE;
	    }

	public BridgeManagedNHttpClientConnectionFactory() {
	        this(null, null, null);
	    }

	@Override
	public ManagedNHttpClientConnection create(final IOSession iosession, final ConnectionConfig config) {
		final String id = "http-outgoing-" + Long.toString(COUNTER.getAndIncrement());
		CharsetDecoder chardecoder = null;
		CharsetEncoder charencoder = null;
		final Charset charset = config.getCharset();
		final CodingErrorAction malformedInputAction = config.getMalformedInputAction() != null
				? config.getMalformedInputAction()
				: CodingErrorAction.REPORT;
		final CodingErrorAction unmappableInputAction = config.getUnmappableInputAction() != null
				? config.getUnmappableInputAction()
				: CodingErrorAction.REPORT;
		if (charset != null) {
			chardecoder = charset.newDecoder();
			chardecoder.onMalformedInput(malformedInputAction);
			chardecoder.onUnmappableCharacter(unmappableInputAction);
			charencoder = charset.newEncoder();
			charencoder.onMalformedInput(malformedInputAction);
			charencoder.onUnmappableCharacter(unmappableInputAction);
		}

		// 使用 bridge
		final ManagedNHttpClientConnection conn = new BridgeManagedNHttpClientConnectionImpl(id, this.log,
				this.headerlog,
				this.wirelog, iosession, config.getBufferSize(), config.getFragmentSizeHint(), this.allocator,
				chardecoder, charencoder, config.getMessageConstraints(), null, null, this.requestWriterFactory,
				this.responseParserFactory);
		iosession.setAttribute(IOEventDispatch.CONNECTION_KEY, conn);
		return conn;
	}

}
