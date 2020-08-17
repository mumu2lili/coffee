package com.clone.detection;

import java.io.File;
import java.security.MessageDigest;

import org.junit.Test;

public class TmpTest {


	@Test
	public void test() {
		File file = new File("C:\\Users\\mumu\\Desktop\\tmp\\a");
		File[] files = file.listFiles();
		System.out.println(files);
	}

	public String getMD5(String src) {
		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(src.getBytes());
			StringBuilder buffer = new StringBuilder();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
