package com.piggy.coffee.jwt;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {
		DefaultHashService hashService = new DefaultHashService(); // 默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("bridge_admin")); // 私盐，默认无
	//	hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。默认就这个
		hashService.setHashIterations(100); // 生成Hash值的迭代次数

		Base64.decode("");
		
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes(Base64.decode("ZOOr75nXOXRFCvc2kvtlHg=="))).setIterations(2)
				.build();
		Hash hash = hashService.computeHash(request);
		System.out.println(hash.getSalt());
		System.out.println(hash.toHex());

	}

	@Test
	public void test2() {
		DefaultHashService hashService = new DefaultHashService(); // 默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("bridge_admin")); // 私盐，默认无
		//hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。默认就这个
		hashService.setHashIterations(1000); // 生成Hash值的迭代次数

		
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("bridge")).setIterations(1000).build();
		Hash hash = hashService.computeHash(request);
		System.out.println(hash.getSalt());
		System.out.println(hash.toHex());

	}
	
	public void test3() {
		System.out.println(Base64.decode("ZOOr75nXOXRFCvc2kvtlHg=="));
	}

}
