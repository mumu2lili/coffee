package com.piggy.coffee.license.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.Cipher;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;

/**
 * 
 * @author mumu
 *
 */
public class CertUtils {
	// 加密算法
	public static final String ENCRYPT_ALGORITHM = "SHA256withRSA";
	// 签名算法
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	public static final String SIGN = System.getProperty("line.separator");

	public static X509Certificate getCertFromPemFile(File pemFile) {
		Reader reader = null;
		try {
			reader = new FileReader(pemFile);
			return parseCert(reader);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("读取证书失败");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ingnore quietly
				}
			}
		}

	}

	// 解析证书
	public static X509Certificate parseCert(String certData) {
		Reader reader = new StringReader(certData);
		return parseCert(reader);

	}

	// 解析证书
	public static X509Certificate parseCert(Reader reader) {
		PEMParser pemParser = null;
		try {
			pemParser = new PEMParser(reader);
			Object object = pemParser.readObject();
			X509CertificateHolder holder = (X509CertificateHolder) object;

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf
					.generateCertificate(new ByteArrayInputStream(holder.getEncoded()));
			return cert;

		} catch (Exception e) {
			throw new RuntimeException("解析证书失败");
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

	public static String encrypt(PrivateKey privateKey, String plaintext) {
		try {

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			byte[] data = cipher.doFinal(plaintext.getBytes("UTF-8"));
			data = Base64.getEncoder().encode(data);
			return new String(data, Charset.forName("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException("私钥加密错误", e);
		}

	}

	public static String decrypt(PublicKey publicKey, String ciphertext) {
		try {
			byte[] data = Base64.getDecoder().decode(ciphertext.getBytes("UTF-8"));

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			data = cipher.doFinal(data);

			return new String(data, Charset.forName("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException("公钥解密错误", e);
		}

	}

	public static byte[] decrypt(PublicKey publicKey, byte[] data) {
		try {

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			return cipher.doFinal(data);
		} catch (Exception e) {
			throw new RuntimeException("公钥解密错误", e);
		}

	}

	public static byte[] sign(byte[] data, PrivateKey privateKey, String algorithm) throws Exception {
		Signature sig = Signature.getInstance(algorithm);
		sig.initSign(privateKey);
		sig.update(data);
		return sig.sign();
	}

	public static boolean verifySign(byte[] data, byte[] sign, PublicKey publicKey, String algorithm) throws Exception {
		Signature sig = Signature.getInstance(algorithm);
		sig.initVerify(publicKey);
		sig.update(data);
		return sig.verify(sign);
	}

	public static void verifyCert(X509Certificate cert, X509Certificate caCert) {
		try {
			cert.verify(caCert.getPublicKey());
		} catch (Exception e) {
			throw new RuntimeException("无效许可");
		}

	}
	
	public static void checkValidity(X509Certificate cert) {
		try {
			cert.checkValidity();
		} catch (CertificateExpiredException e) {
			throw new RuntimeException("许可已过期");
		} catch (CertificateNotYetValidException e) {
			throw new RuntimeException("许可未生效");
		}
	}

}