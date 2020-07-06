package com.piggy.coffee.tmp;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Assert;
import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {
    	System.out.println(Integer.MAX_VALUE);
		long t = System.currentTimeMillis();
	        // 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
	        /********** Begin **********/
	        // 第一步：获取键盘三次输入的值
	        double a = 3;
	        double b = 4;
	        double c = 5;
	        // 第二步：根据三角形面积公式求取三角形面积
	        double p = 0.5*(a+b+c);
	        double s= Math.sqrt(p*(p-a)*(p-b)*(p-c));
	        // 第三步：格式化输出三角形的面积
	        System.out.printf("A三角形的面积为：%.2f",s);
	        /********** End **********/
	    	System.out.println();
	    	System.out.println(System.currentTimeMillis() - t);
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
