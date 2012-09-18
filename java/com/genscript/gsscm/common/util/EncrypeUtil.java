package com.genscript.gsscm.common.util;

/**
 * @description: MD5加密算法工具类
 * @author: Golf
 * @createDate: 2010/7/25 4:42 PM
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypeUtil {
	// 加密的算法名称
	private static final String encrypeType = "MD5";

	// 构造函数
	public EncrypeUtil() {
	}

	// 随机生成5位随机数
	public final static String get5Radom() {
		String newString = null;

		// 得到0.0到1.0之间的数字,并扩大100000倍
		double doubleP = Math.random() * 100000;

		// 如果数据等于100000,则减少1
		if (doubleP >= 100000) {
			doubleP = 99999;
		}

		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);

		// 转化为字符串
		newString = "" + tempString;

		// 把得到的数增加为固定长度,为5位
		while (newString.length() < 5) {
			newString = "0" + newString;
		}

		return newString;
	}

	// 主要把传递过来的字符串参数转化为经过MD5算法加密的字符串
	public final static String encrypeString(String neededEncrypedString)
			throws Exception {
		// 初始化加密之后的字符串
		String encrypeString = null;

		// 16进制的数组
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		// 字符串的加密过程
		try {
			// 把需要加密的字符串转化为字节数组
			byte[] neededEncrypedByteTemp = neededEncrypedString.getBytes();

			// 得到MD5的加密算法对象
			MessageDigest md = MessageDigest.getInstance(encrypeType);

			// 更新算法使用的摘要
			md.update(neededEncrypedByteTemp);

			// 完成算法加密过程
			byte[] middleResult = md.digest();

			// 把加密后的字节数组转化为字符串
			int length = middleResult.length;
			char[] neededEncrypedByte = new char[length * 2];
			int k = 0;
			for (int i = 0; i < length; i++) {
				byte byte0 = middleResult[i];
				neededEncrypedByte[k++] = hexDigits[byte0 >>> 4 & 0xf];
				neededEncrypedByte[k++] = hexDigits[byte0 & 0xf];
			}
			encrypeString = new String(neededEncrypedByte);
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex);
		}

		// 返回加密之后的字符串
		return encrypeString;
	}
}
