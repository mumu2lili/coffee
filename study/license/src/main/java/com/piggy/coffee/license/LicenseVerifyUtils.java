package com.piggy.coffee.license;

import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.Cipher;

public class LicenseVerifyUtils {
	// 加密算法
	private static final String ENCRYPT_ALGORITHM = "SHA256withRSA";
	// 签名算法
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

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

	public static void verifyCert(X509Certificate cert, X509Certificate caCert) {
		try {
			cert.verify(caCert.getPublicKey());
		} catch (Exception e) {
			throw new RuntimeException("无效许可");
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
}
