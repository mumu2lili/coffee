package com.piggy.coffee.license.util;

import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.piggy.coffee.common.util.json.JsonUtils;
import com.piggy.coffee.license.model.BridgeGrantInfo;
import com.piggy.coffee.license.model.BridgeLicense;

public class LicenseUtils {

	public static BridgeLicense readLicense(String path) {
		// 许可全部内容
		File file = new File(path);
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("读取许可失败", e);
		}

		BridgeLicense lic = new BridgeLicense();
		// 1.证书部分。从第一行开始，至倒数第二行解析为证书
		// 1.1 构造证书数据
		int lastIndex = lines.size() - 1;
		StringBuilder sb = new StringBuilder().append("-----BEGIN CERTIFICATE-----").append(CertUtils.SIGN);
		for (int i = 0; i < lastIndex; i++) {
			String line = lines.get(i);
			sb.append(line).append(CertUtils.SIGN);
		}
		sb.append("-----END CERTIFICATE-----").toString();

		// 1.2解析证书
		X509Certificate cert = CertUtils.parseCert(sb.toString());
		lic.setCert(cert);

		// 2.解析 BridgeGrantInfo
		// 最后一行为BridgeGrantInfo
		String last = lines.get(lastIndex);
		last = CertUtils.decrypt(cert.getPublicKey(), last);
		BridgeGrantInfo info = JsonUtils.toBean(last, BridgeGrantInfo.class);
		lic.setInfo(info);

		return lic;
	}

	// 证书为 pem格式
	public static void createLicense(String certPath, PrivateKey privateKey, BridgeGrantInfo info) {
		// 证书部分，要去掉begin 和 end
		File file = new File(certPath);
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("读取许可失败", e);
		}
		lines.remove(0); // 去掉 begin
		lines.remove(lines.size() - 1); // 去掉 end

		// BridgeGrantInfo 部分， 须私钥加密
		String json = JsonUtils.toJson(info);
		String content = CertUtils.encrypt(privateKey, json);

		// 添加到末尾
		lines.add(content);

		// 写许可文件
		String licPath = file.getParentFile().getAbsolutePath();
		licPath = licPath + File.separator + "bridge.lic";
		File licFile = new File(licPath);
		try {
			FileUtils.writeLines(licFile, lines);
		} catch (IOException e) {
			throw new RuntimeException("写许可文件失败", e);
		}
	}
}
