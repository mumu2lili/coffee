package com.piggy.coffee.license.util;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class KeyUtils {

	public static PrivateKey getPrivateKey(String storeType, String storePath, String storePassword, String alias,
			String keyPassword) {
		try {
			KeyStore keystore = KeyStore.getInstance(storeType);
			keystore.load(new FileInputStream(storePath), storePassword.toCharArray());
			KeyPair keyPair = getPrivateKey(keystore, alias, keyPassword.toCharArray());
			PrivateKey privateKey = keyPair.getPrivate();
			return privateKey;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
		try {
			Key key = keystore.getKey(alias, password);
			if (key instanceof PrivateKey) {
				Certificate cert = keystore.getCertificate(alias);
				PublicKey publicKey = cert.getPublicKey();
				return new KeyPair(publicKey, (PrivateKey) key);
			} else {
				throw new RuntimeException("无私钥");
			}
		} catch (Exception e) {
			throw new RuntimeException("读取密钥对出错", e);
		}

	}

}