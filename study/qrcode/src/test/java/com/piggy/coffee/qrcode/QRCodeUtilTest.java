package com.piggy.coffee.qrcode;

import org.junit.Test;

public class QRCodeUtilTest {

	@Test
	public void test() throws Exception {

		// 生成二维码
		String text = "https://www.baidu.com/";
		String imagePath = System.getProperty("user.dir") + "/data/1.png";
		String destPath = System.getProperty("user.dir") + "/data/output/";
		QRCodeUtil.encode(text, imagePath, destPath, true);

	}
}
