package com.piggy.coffee.license.util;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.junit.Test;

import com.piggy.coffee.license.mgr.LicenseMgr;
import com.piggy.coffee.license.model.BridgeGrantInfo;
import com.piggy.coffee.license.model.BridgeHost;

public class LicenseUtilsTest {

	@Test
	public void testCreateLicense() {
		String path = "D:\\work\\eclipse-workspace\\zs\\hnu.pem";
		
		String storePath = "D:\\work\\eclipse-workspace\\zs\\bridge.jks";
		PrivateKey privateKey = KeyUtils.getPrivateKey("PKCS12", storePath, "123456", "hnu", "123456");
		
		BridgeGrantInfo info = new BridgeGrantInfo();
		BridgeHost host = new BridgeHost();
		host.setIp("192.168.2.49");
		host.setMac("98-3B-8F-82-9B-55");
		info.addBridgeHost(host);
		
		LicenseUtils.createLicense(path, privateKey, info);

	}

	@Test
	public void testVerifyCert() {
		String path = "D:\\work\\eclipse-workspace\\zs\\bridge.lic";
		X509Certificate cert = LicenseUtils.readLicense(path).getCert();

		String caCertData = LicenseMgr.getCaCertData();
		X509Certificate caCert = CertUtils.parseCert(caCertData);

		CertUtils.verifyCert(cert, caCert);

	}

}
