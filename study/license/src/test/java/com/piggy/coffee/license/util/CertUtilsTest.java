package com.piggy.coffee.license.util;

import java.io.File;
import java.security.cert.X509Certificate;

import org.junit.Test;

import com.piggy.coffee.license.mgr.LicenseMgr;

public class CertUtilsTest {

	@Test
	public void testVerifyCert() {

		String path = "D:\\work\\eclipse-workspace\\zs\\hnu.pem";
		String caCertPath = "D:\\work\\eclipse-workspace\\zs\\bridge.pem";
		X509Certificate cert = CertUtils.getCertFromPemFile(new File(path));
		X509Certificate caCert = CertUtils.getCertFromPemFile(new File(caCertPath));
		CertUtils.verifyCert(cert, caCert);
	}

	@Test
	public void testVerifyCert2() {

		String path = "D:\\work\\eclipse-workspace\\zs\\hnu.pem";
		X509Certificate cert = CertUtils.getCertFromPemFile(new File(path));

		String caCertData = LicenseMgr.getCaCertData();
		X509Certificate caCert = CertUtils.parseCert(caCertData);

		CertUtils.verifyCert(cert, caCert);
	}
}
