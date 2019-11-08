package com.piggy.coffee.k8s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.DoneablePod;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import io.fabric8.kubernetes.client.dsl.PodResource;
import okhttp3.Response;

public class ExecTest302 extends ClientTest {
	private Logger logger = LoggerFactory.getLogger(ExecTest302.class);

	@Test
	public void test() throws InterruptedException {

		this.test("/opt/a.sh");

		// Thread.sleep(1000 * 5);
	}

	private void test(String scriptPth) {
		try {

			ShellExecTimeManager manager = new ShellExecTimeManager();
			manager.init();

			PipedInputStream in = new PipedInputStream();
			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos = new PipedOutputStream(pis);
			PodResource<Pod, DoneablePod> podResource = client.pods().inNamespace("default").withName("hello");
			ExecWatch watch = podResource.readingInput(in).writingOutput(pos).withTTY()
					.usingListener(new SimpleListener(pos, "hello")).exec("sh", scriptPth);

			// manager.putExecTime(watch, 10);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pis));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				logger.info(line);
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
				// Thread.sleep(5000);
			}

			watch.close();

		} catch (IOException e) {// 执行繁忙
			logger.error("执行繁忙", e);
		} catch (Exception e) {// TODO 可能因为读取不及时
			logger.error("读取不及时", e);
		}

	}

	private class SimpleListener implements ExecListener {
		private PipedOutputStream pos;
		private String podName;

		public SimpleListener(PipedOutputStream pos, String podName) {
			super();
			this.pos = pos;
			this.podName = podName;
		}

		@Override
		public void onOpen(Response response) {
			String msg = response == null ? "" : response.message();
			logger.debug("pod [{}] open websocket success msg [{}]", podName, msg);
		}

		@Override
		public void onFailure(Throwable t, Response response) {
			String msg = response == null ? "" : response.message();
			msg = String.format("pod [{}] open websocket fail msg [{}]", podName, msg);
			logger.error(msg, t);
		}

		/**
		 * server close websocket
		 */
		@Override
		public void onClose(int code, String reason) {
			try {
				pos.flush();
			} catch (IOException e) {
				String msg = String.format("pod [{}] close connection code [{}] reason [{}] flush pos", podName, code,
						reason);
				logger.error(msg, e);
			} finally {
				try {
					pos.close();
					logger.debug("pod [{}] 执行结束，websocket关闭， msg [{}]", podName, reason);
				} catch (IOException e) {
					String msg = String.format(
							"pod [{}] close connection code [{}] reason [{}] close pos，使其连接的inputStream结束", podName,
							code, reason);
					logger.error(msg, e);
				}
			}
		}
	}

}
