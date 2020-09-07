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
import java.security.cert.CertificateFactory;
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
	private static final String ENCRYPT_ALGORITHM = "SHA256withRSA";
	// 签名算法
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	public static final String SIGN = System.getProperty("line.separator");
	private static final String CA_CERT_DATA = new StringBuilder().append("-----BEGIN CERTIFICATE-----").append(SIGN)
			.append("MIIDnTCCAoWgAwIBAgIEaOv7rDANBgkqhkiG9w0BAQsFADB/MQ8wDQYDVQQGDAbk").append(SIGN)
			.append("uK3lm70xDzANBgNVBAgMBua5luWNlzEPMA0GA1UEBwwG6ZW/5rKZMRIwEAYDVQQK").append(SIGN)
			.append("DAnlrp7orq3kupExJzAlBgNVBAsMHua5luWNl+aZuuaTjuenkeaKgOaciemZkOWF").append(SIGN)
			.append("rOWPuDENMAsGA1UEAxMEbXVtdTAeFw0yMDA5MDMwOTAxMzFaFw0zMDA5MDEwOTAx").append(SIGN)
			.append("MzFaMH8xDzANBgNVBAYMBuS4reWbvTEPMA0GA1UECAwG5rmW5Y2XMQ8wDQYDVQQH").append(SIGN)
			.append("DAbplb/mspkxEjAQBgNVBAoMCeWunuiureS6kTEnMCUGA1UECwwe5rmW5Y2X5pm6").append(SIGN)
			.append("5pOO56eR5oqA5pyJ6ZmQ5YWs5Y+4MQ0wCwYDVQQDEwRtdW11MIIBIjANBgkqhkiG").append(SIGN)
			.append("9w0BAQEFAAOCAQ8AMIIBCgKCAQEAueK02KjhwPyj1Q6kinFgcWsFdP0m4GJXW4go").append(SIGN)
			.append("47ICaOcI9WRkuJWNBKWCH5ozgkxj9ZseZGGahSVOpkEN7ao09HvGbAQHAHxRH+FN").append(SIGN)
			.append("/Q+eGlL4/uJZjU1xA5MmWcwXIYYL6MfmF9/+MR5slI0hQ8eAJOyW0XOwHS4iG9ME").append(SIGN)
			.append("mFi+jjGLGrtMtv+OsZMLWtyHL1GvWW+yZ+R9a1YGj0Y2Js+4P/AFke2rMMepaTZx").append(SIGN)
			.append("FMqOokde/72KSl3yHFCfwR0TdtaAOPEbSVdGXGjp0V//k4RP7/Tb+K6SCAcHWltp").append(SIGN)
			.append("qHvUitl+EwKjth5sxWK+WedFwoxcZkJrM8qG/yL6fgVnpB9s9wIDAQABoyEwHzAd").append(SIGN)
			.append("BgNVHQ4EFgQUsoElFohddOc5cdLMszF8gRasUTowDQYJKoZIhvcNAQELBQADggEB").append(SIGN)
			.append("AKUePaJxjwY1QPcG6dXk4k+AbNY/XyUqScj3Wn/d6mG87fwA+UztGSkgxPLBfB/E").append(SIGN)
			.append("5PyKGoAmTWtOFg3ksXTeeV16ZdlrKJ+RJu/07E1PQh9B5B//V+CveVCDnJGRPr7U").append(SIGN)
			.append("cyVNoZpyFMYLS5zPIivlV/KEaKwxnmZVevSSN4oYSfkjVIIiYWH+5psNoXfaVQ9Q").append(SIGN)
			.append("a1dkTR7qGd9Qt6M23v/piFMYra30PWdBgYYT+sT8rZ3/5L3V6fdbFOM4xLjlVbJY").append(SIGN)
			.append("GwzGy8jW+Sfes4t8E0m8yb3/7SQik+MwAMePODW7kx8Deq3fXplSopnjyYtwvVQW").append(SIGN)
			.append("uH+Nd2XKn0YKVT42sIqWcow=").append(SIGN).append("-----END CERTIFICATE-----").toString();

	public static X509Certificate readCertFromPemFile(File pemFile) {
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

	public static X509Certificate readCaCert() {
		return parseCert(CA_CERT_DATA);

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

	//
	public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initSign(privateKey);
		sig.update(data);
		return sig.sign();
	}

	public static boolean verify(byte[] data, byte[] sign, PublicKey publicKey) throws Exception {
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
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

}