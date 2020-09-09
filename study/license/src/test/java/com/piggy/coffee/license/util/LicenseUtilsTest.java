package com.piggy.coffee.license.util;

import java.security.cert.X509Certificate;

import org.junit.Test;

import com.piggy.coffee.license.mgr.LicenseMgr;

public class LicenseUtilsTest {

	@Test
	public void testVerifyCert() {
		String path = "D:\\work\\eclipse-workspace\\zs\\bridge.lic";
		X509Certificate cert = LicenseUtils.readLicense(path).getCert();

		String caCertData = LicenseMgr.getCaCertData();
		X509Certificate caCert = CertUtils.parseCert(caCertData);
		
		CertUtils.verifyCert(cert, caCert);

	}

}
