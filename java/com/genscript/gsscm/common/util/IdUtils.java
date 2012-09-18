package com.genscript.gsscm.common.util;

import java.security.SecureRandom;
import java.util.UUID;

public abstract class IdUtils {
	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成,中间有-分割
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return random.nextLong();
	}

	/**
	 * 基于Base62编码的随机生成Long.
	 */
	public static String randomBase62() {
		return EncodeUtils.encodeBase62(random.nextLong());
	}
}
