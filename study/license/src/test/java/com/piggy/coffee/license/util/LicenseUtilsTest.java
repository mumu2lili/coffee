package com.piggy.coffee.license.util;

import java.io.File;
import java.security.cert.X509Certificate;

import org.junit.Test;

public class LicenseUtilsTest {

	@Test
	public void testVerifyCert() {
		String path = "D:\\work\\eclipse-workspace\\zs\\bridge.lic";
		String caCertPath = "D:\\work\\eclipse-workspace\\zs\\bridge.pem";
		X509Certificate cert = LicenseUtils.readLicense(path).getCert();
		X509Certificate caCert = CertUtils.readCertFromPemFile(new File(caCertPath));
		CertUtils.verifyCert(cert, caCert);

	}

}
