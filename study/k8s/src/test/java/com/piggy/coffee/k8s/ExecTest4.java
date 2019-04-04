package com.piggy.coffee.k8s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import okhttp3.Response;

public class ExecTest4 extends ClientTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		this.test("/root/a.sh");
	}

	@Test
	public void test2() {
		this.test("/root/b.sh");
	}

	private void test(String scriptPth) {
		try {

			ShellExecTimeManager manager = new ShellExecTimeManager();
			manager.init();
			// 要不得
			ExecWatch watch = client.pods().inNamespace("default").withName("mypoda").redirectingInput()
					.redirectingOutput().redirectingError()
					// .withTTY()
					.usingListener(new SimpleListener())
					.exec("sh", "timeout 100 " + scriptPth);
			manager.putExecTime(watch, 10000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(watch.getOutput()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				log.info(line);
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
				// Thread.sleep(5000);
			}

			watch.close();

		} catch (IOException e) {// 执行繁忙
			log.error(e.getMessage(), e);
		} catch (Exception e) {// TODO 可能因为读取不及时
			log.error(e.getMessage(), e);
		}

	}

	private class SimpleListener implements ExecListener {

		@Override
		public void onOpen(Response response) {
			log.debug("open {}", response.message());
		}

		@Override
		public void onFailure(Throwable t, Response response) {
			log.error(response.message(), t);
		}

		@Override
		public void onClose(int code, String reason) {
			log.debug("close code {} reason {}", code, reason);
		}
	}

}
