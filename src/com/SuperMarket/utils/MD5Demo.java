package com.SuperMarket.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.w3c.dom.ranges.RangeException;

/**
* MD5加密算法
* @author JamsF
* @version 创建时间：2020年3月2日 下午1:06:04
*/
public class MD5Demo {
	public static String md5(String pass) {
		byte[] secretBytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			secretBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RangeException((short) 0, "没有md5算法");
		}
		String md5code = new BigInteger(1,secretBytes).toString(16);
		for(int i = 0;i < 32 - md5code.length();i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}
