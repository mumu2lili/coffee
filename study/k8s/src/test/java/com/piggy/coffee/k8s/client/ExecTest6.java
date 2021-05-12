package com.piggy.coffee.k8s.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piggy.coffee.k8s.ShellExecTimeManager;

import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import okhttp3.Response;

public class ExecTest6 extends ClientTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		this.test("/data/workspace/myshixun_3689/evaluate.sh");
	}

	private void test(String scriptPth) {
		try {

			ShellExecTimeManager manager = new ShellExecTimeManager();
			manager.init();

			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos = new PipedOutputStream(pis);

			String ins = "\"IA==\"";
			ins = "5aum5ailCjM1MDAK5aWzCjQ1LjUK5pyI55CD5bm/5a+S5a6rCuWQpg==";
			ExecWatch watch = client.pods().inNamespace("default").withName("mypodb")
					.readingInput(new PipedInputStream()).writingOutput(pos).withTTY()
					.usingListener(new SimpleListener(pos))
					.exec("bash", "-c", "bash " + scriptPth + " " + 3 + " " + ins);

			manager.putExecTime(watch, 10);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pis));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				log.info(line);
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
				// Thread.sleep(5000);
			}

			watch.close();
			pis.close();

		} catch (IOException e) {// 执行繁忙
			log.error(e.getMessage(), e);
		} catch (Exception e) {// TODO 可能因为读取不及时
			log.error(e.getMessage(), e);
		}

	}

	private class SimpleListener implements ExecListener {

		private PipedOutputStream pos;

		public SimpleListener(PipedOutputStream lock) {
			super();
			this.pos = lock;
		}

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

			try {
				pos.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			log.debug("close code {} reason {}", code, reason);
		}
	}

}
