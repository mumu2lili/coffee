package com.piggy.coffee.common.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

/**
 * Created by guange on 23/02/2017.
 */
public final class Base64Util {

	/**
	 * base64编码
	 *
	 * @param code
	 * @return
	 */
	public static String encode(String code) {
		byte[] encode = Base64.encodeBase64URLSafe(code.getBytes(StandardCharsets.UTF_8));
		return new String(encode, StandardCharsets.UTF_8);
	}

	public static byte[] encodeBytes(byte[] codes) {
		return Base64.encodeBase64(codes);
	}

	/**
	 * base64解码
	 *
	 * @param code
	 * @return
	 */
	public static String decode(String code) {
		byte[] decode = Base64.decodeBase64(code);
		return new String(decode, StandardCharsets.UTF_8);
	}

	/**
	 * base64再解码，把原本的非URL safe编码转换为URL safe编码
	 *
	 * @param code
	 * @return
	 */
	public static String reencode(String code) {
		String str = decode(code);
		//str = str.replace("\n", "\r\n");
		str = replaceLineFeed(str);
		return encode(str);
	}
	
	private static String replaceLineFeed(String str) {
		StringBuilder sb = new StringBuilder();
		char[] cs = str.toCharArray();
		int len = cs.length;
		for (int i = 0; i < len; i++) {
			char c = cs[i];
			if (c == '\n') {
				if (i == 0) {
					sb.append('\r').append(c);
				} else {
					char pre = cs[i - 1];
					if (pre == '\r') {
						sb.append(c);
					} else {
						sb.append('\r').append(c);
					}
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 正常编码
	 *
	 * @param code
	 * @return
	 */
	public static String encodeNoSafe(String code) {
		byte[] encode = Base64.encodeBase64(code.getBytes(StandardCharsets.UTF_8));
		return new String(encode, StandardCharsets.UTF_8);
	}


}
