package com.piggy.coffee.license;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;

/**
 * CreateLicense
 * 
 * @author melina
 */
public class CertReader {

	public static X509Certificate readCertFromPemFile(File pemFile) {
		PEMParser pemParser = null;
		try {
			pemParser = new PEMParser(new FileReader(pemFile));
			Object object = pemParser.readObject();
			X509CertificateHolder holder = (X509CertificateHolder) object;

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf
					.generateCertificate(new ByteArrayInputStream(holder.getEncoded()));
			return cert;

		} catch (Exception e) {
			throw new RuntimeException("读取许可失败");
		} finally {
			if (pemParser != null) {
				try {
					pemParser.close();
				} catch (IOException e) {
					// ingnore quietly
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		String path = "D:\\work\\eclipse-workspace\\zs\\hnu.pem";
		String caCertPath = "D:\\work\\eclipse-workspace\\zs\\bridge.pem";
		X509Certificate cert = readCertFromPemFile(new File(path));
		X509Certificate caCert = readCertFromPemFile(new File(caCertPath));
		LicenseVerifyUtils.verifyCert(cert, caCert);

		
	}
}