package com.piggy.coffee.k8s;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piggy.coffee.common.util.shell.ShellExeCallBack;
import com.piggy.coffee.common.util.shell.ShellExeResult;
import com.piggy.coffee.common.util.shell.ShellUtils;

public class ShellExecTest extends ClientTest {
	private static Logger logger = LoggerFactory.getLogger(ShellExecTest.class);

	@Test
	public void test() throws InterruptedException {

		this.test("/opt/a.sh");

		// Thread.sleep(1000 * 5);
	}

	private void test(String shFile) {

		int timeLimit = 10;
		String podName = "hello";

		String command = " timeout " + timeLimit + " kubectl exec " + podName + " timeout " + timeLimit + " bash "
				+ shFile;
		LineCallBack callBack = new LineCallBack();
		ShellExeResult result = ShellUtils.executeAndGetExitStatus(command, callBack);
		logger.info(result.getOut());
		logger.info("exitCode:" + result.getExitCode());

	}

	private static class LineCallBack implements ShellExeCallBack {

		@Override
		public void processLine(String line) {
			if (line.startsWith("testcase:")) {
				logger.info(line);
			}
		}

	}

}
