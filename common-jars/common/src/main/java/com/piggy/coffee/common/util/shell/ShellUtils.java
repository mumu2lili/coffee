package com.piggy.coffee.common.util.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ShellUtils {

	private static final Logger logger = LoggerFactory.getLogger(ShellUtils.class);

	private static volatile ExecutorService executor;

	/**
	 * 执行shell命令并获取输出
	 */
	public static String execute(String command) {
		return executeAndGetExitStatus(command).getOut();
	}

	/**
	 * 执行shell命令并获得输出及退出码，失败重试 共尝试retryTimes次
	 */
	public static ShellExeResult executeAndGetExitStatus(String command, int retryTimes) {
		ShellExeResult result = new ShellExeResult();

		for (int i = 0; i < retryTimes; i++) {
			result = executeAndGetExitStatus(command);
			if (result.getExitCode() != 0) {
				continue;
			} else {
				break;
			}
		}

		return result;
	}

	/**
	 * 执行命令并获得输出以及退出码
	 */
	public static ShellExeResult executeAndGetExitStatus(String command) {
		ShellExeResult result = new ShellExeResult();

		StringBuffer out = new StringBuffer();
		Integer exitStatus = -1;

		ProcessBuilder pb = new ProcessBuilder(new String[] { "/bin/sh", "-c", command });
		pb.redirectErrorStream(true);
		try {
			Process process = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
				out.append(System.getProperty("line.separator"));
			}
			exitStatus = process.waitFor();

		} catch (Exception e) {
			logger.info("executeAndGetExitStatus: {}", command);
			logger.error("executeAndGetExitStatus: ", e);
		}

		result.setOut(out.toString().trim());
		result.setExitCode(exitStatus);

		return result;
	}

	public static ShellExeResult executeAndGetExitStatus(String command, ShellExeCallBack callBack) {
		ShellExeResult result = new ShellExeResult();

		StringBuilder out = new StringBuilder();
		Integer exitStatus = -1;

		ProcessBuilder pb = new ProcessBuilder(new String[] { "/bin/sh", "-c", command });
		pb.redirectErrorStream(true);
		try {
			Process process = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
				out.append(System.getProperty("line.separator"));
				callBack.processLine(line);
			}
			exitStatus = process.waitFor();

		} catch (Exception e) {
			logger.info("executeAndGetExitStatus: {}", command);
			logger.error("executeAndGetExitStatus: ", e);
		}

		result.setOut(out.toString().trim());
		result.setExitCode(exitStatus);

		return result;
	}

	/**
	 * 执行shell命令，设定时间限制为${timeLimit} s
	 */
	public static ShellExeResult executeInLimitTime(String command, long timeLimit) throws TimeoutException {
		ShellExeResult result = new ShellExeResult();

		if (executor == null) {
			synchronized (ShellUtils.class) {
				if (executor == null) {
					executor = Executors.newCachedThreadPool();
				}
			}
		}

		try {
			Future<ShellExeResult> future = executor.submit(() -> executeAndGetExitStatus(command));
			result = future.get(timeLimit, TimeUnit.SECONDS);
		} catch (TimeoutException ex) {
			logger.debug("执行shell命令超时：command: {}", command);

			// 删除任务
			killProcess(command);

			throw new TimeoutException("执行命令超时");
		} catch (Exception e) {
			logger.debug("执行shell命令失败，未知错误：command: {}", command);
			logger.error("执行shell命令失败，未知错误", e);
		}

		return result;
	}

	/**
	 * 杀掉一个shell进程
	 *
	 * @param commandToKill
	 *            源命令
	 */
	public static void killProcess(String commandToKill) {
		// 原型：kill -9 `ps -ef | grep "haha.sh" |grep -v grep | awk -F' ' '{print $2}'`
		logger.debug("ExecuteShellCommand: killProcess: " + commandToKill);

		String command = "kill -9 `ps -ef | grep \"" + commandToKill + "\" | grep -v grep | awk -F' ' '{print $2}'`";

		try {
			Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
