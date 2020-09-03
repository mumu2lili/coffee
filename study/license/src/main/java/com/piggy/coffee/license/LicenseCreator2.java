package com.piggy.coffee.license;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;

/**
 * CreateLicense
 * 
 * @author melina
 */
public class LicenseCreator2 {

	public static void readKeyPair(File pemFile, char[] password) throws Exception {
		PEMParser pemParser = new PEMParser(new FileReader(pemFile));
		Object object = pemParser.readObject();
		pemParser.close();


		X509CertificateHolder x = (X509CertificateHolder) object;

		
		
		 CertificateFactory cf = null;
	        PublicKey publicKey = null;
	        try {
	            cf = CertificateFactory.getInstance("X.509");
	            //DataInputStream  di = new DataInputStream("");
	            X509Certificate cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(x.getEncoded()));
	            publicKey = cert.getPublicKey();
	            System.out.println("ok");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	  


	
	}

	public PublicKey initKeyPair(String pemFilePath, String password) {
	/*	byte[] data = "PEM".getBytes();
		Security.addProvider(new BouncyCastleProvider());
		KeyPair kp;*/
		try {
			 readKeyPair(new File(pemFilePath), password.toCharArray());

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	
	public static void main(String[] args) {
		LicenseCreator2 lc = new LicenseCreator2();
		lc.initKeyPair("D:\\work\\eclipse-workspace\\zs\\hnu.pem", "123456");
	}
}