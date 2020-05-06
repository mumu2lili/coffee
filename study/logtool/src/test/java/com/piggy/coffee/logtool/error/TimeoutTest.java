package com.piggy.coffee.logtool.error;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

import com.piggy.coffee.common.util.Base64Util;
import com.piggy.coffee.common.util.shell.ShellExeResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeoutTest {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static String executeCommand(String command, File file) throws IOException {
		StringBuffer output = new StringBuffer();
		Process p = null;
		if (!file.exists()){
			file.mkdir();
		}
		try {
			p =Runtime.getRuntime().exec(command, null, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try (InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream(), "GBK");
			 BufferedReader reader = new BufferedReader(inputStreamReader);){
			String line = "";
			while (true) {
				try {
					if (!((line = reader.readLine()) != null)) break;
				} catch (IOException e) {
					e.printStackTrace();
				}
				output.append(line + "\n");
			}
		}
		System.out.println(output.toString());
		return output.toString();

	}

	@Test
	public void getCode() {
		try {
			try(BufferedReader reader = new BufferedReader(new FileReader(new File("gitUrl.txt")))) {
				String gitUrl ;
				while ((gitUrl = reader.readLine())!=null) {
					System.out.println(gitUrl);
					String command = "git clone "+gitUrl;
					String output = null;
					try {
						output = executeCommand(command, new File("F:/overtime"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(output);
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testTimeout() throws IOException {

		Map<String, EvaluatingAssayParam> reqMap = new HashMap<>();
		String logFile = null;
		logFile = "F:\\智擎coffee\\死循环\\超时代码\\bridge.2020-05-04.log";
		this.extractReq(reqMap, logFile);
		logFile = "F:\\智擎coffee\\死循环\\超时代码\\bridge.2020-05-04.log";
		this.extractReq(reqMap, logFile);
		Map<LocalDateTime, EvaluatingAssayParam> timeoutMap = new TreeMap<>();
		logFile = "F:\\智擎coffee\\死循环\\超时代码\\bridge.2020-05-04.log";
		this.extractTimeout(timeoutMap, reqMap, logFile);
		logFile = "F:\\智擎coffee\\死循环\\超时代码\\bridge.2020-05-04.log";
		this.extractTimeout(timeoutMap, reqMap, logFile);

		try(PrintWriter pw = new PrintWriter(new File("gitUrl.txt"))){


		// log.info("超时！！！！！！！");
		for (Iterator<Entry<LocalDateTime, EvaluatingAssayParam>> iter = timeoutMap.entrySet().iterator(); iter
				.hasNext();) {
			Entry<LocalDateTime, EvaluatingAssayParam> entry = iter.next();
			EvaluatingAssayParam param = entry.getValue();
			parse(param);
			pw.println(param.gitUrl);
//			log.info(
//					"{\"tpiID\":\"{}\",\"podType\":{},\"instanceChallenge\":\"{}\",\"timeLimit\":{},\"evaluateStartTime\":\"{}\",\"evaluateEndTime\":\"{}\",\"gitUrl\":\"{}\"}",
//					param.tpiID, param.podType, param.instanceChallenge, param.timeLimit, param.evaluateStartTime,
//					param.evaluateEndTime, param.gitUrl);
		}

		}
		// log.info("超时数量：" + timeoutMap.size());
	}

	private void extractReq(Map<String, EvaluatingAssayParam> reqMap, String logFile) throws IOException {

		Files.lines(Paths.get(logFile), StandardCharsets.UTF_8).forEach(new Consumer<String>() {

			@Override
			public void accept(String line) {
				String str = "\\[start\\]评测：tpiID:";
				if (line.matches(".*" + str + ".*")) {
					int start = line.indexOf("[") + 1;
					int end = line.indexOf("]");
					String seqKey = line.substring(start, end);
					EvaluatingAssayParam param = new EvaluatingAssayParam();
					param.req = line;
					reqMap.put(seqKey, param);
				}
			}
		});
	}

	private void extractTimeout(Map<LocalDateTime, EvaluatingAssayParam> timeoutMap,
								Map<String, EvaluatingAssayParam> reqMap, String logFile) throws IOException {

		Files.lines(Paths.get(logFile), StandardCharsets.UTF_8).forEach(new Consumer<String>() {

			@Override
			public void accept(String line) {
				String str = "5Luj56CB6K-E5rWL6LaF5pe277yB";
				if (line.matches(".*" + str + ".*")) {
					int begin = line.indexOf("[") + 1;
					int end = line.indexOf("]");
					String seqKey = line.substring(begin, end);
					EvaluatingAssayParam param = reqMap.get(seqKey);
					if (param == null) {
						return;
					}
					String timeStr = "2020-" + line.substring(0, 14);
					timeStr = timeStr.replace(" ", "T");
					LocalDateTime time = LocalDateTime.parse(timeStr);
					param.res = line;
					timeoutMap.put(time, param);
				}
			}
		});
	}

	private void parse(EvaluatingAssayParam param) {
		/*
		 * req
		 */
		String tmp = param.req;

		int begin = tmp.indexOf("tpiID: ") + "tpiID: ".length();
		tmp = tmp.substring(begin);
		int end = tmp.indexOf(",");
		param.tpiID = tmp.substring(0, end);

		begin = tmp.indexOf("tpiGitURL: ") + "tpiGitURL: ".length();
		tmp = tmp.substring(begin);
		end = tmp.indexOf(",");
		param.gitUrl = Base64Util.decode(tmp.substring(0, end)).replace("http://172.16.94.154:9000", "https://git.educoder.net");

		begin = tmp.indexOf("instanceChallenge: ") + "instanceChallenge: ".length();
		tmp = tmp.substring(begin);
		end = tmp.indexOf(",");
		param.instanceChallenge = tmp.substring(0, end);

		begin = tmp.indexOf("timeLimit: ") + "timeLimit: ".length();
		tmp = tmp.substring(begin);
		end = tmp.indexOf(",");
		param.timeLimit = Integer.valueOf(tmp.substring(0, end));

		begin = tmp.indexOf("podType: ") + "podType: ".length();
		tmp = tmp.substring(begin);
		end = tmp.indexOf(",");
		param.podType = Integer.valueOf(tmp.substring(0, end));

		/*
		 * res
		 */
		tmp = param.res;

		begin = tmp.indexOf("evaluateEndTime\\\":\\\"") + "evaluateEndTime\\\":\\\"".length();
		tmp = tmp.substring(begin);
		end = tmp.indexOf("\\");
		String time = tmp.substring(0, end);
		param.evaluateEndTime = time.replace("T", " ").substring(0, time.length() - 4);

		begin = tmp.indexOf("evaluateStartTime\\\":\\\"") + "evaluateStartTime\\\":\\\"".length();
		tmp = tmp.substring(begin);
		end = tmp.indexOf("\\");
		time = tmp.substring(0, end);
		param.evaluateStartTime = time.replace("T", " ").substring(0, time.length() - 4);
	}

}
