package com.piggy.coffee.tmp;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Assert;
import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {
		Assert.assertEquals(false, false);

		String str = "hello world";

		String md5 = getMD5(str);

		System.out.println(md5);

		md5 = DigestUtils.md5Hex(str);

		System.out.println(md5);
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
