package com.woniu.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
/**
 * MD5加密
 * @author Administrator
 *
 */
public class MYMD5 {
	public static String getMD5(String str) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] strBs = str.getBytes();
		byte[] bs = md.digest(strBs);
		Formatter format = new Formatter();
		for (byte b : bs) {
			format.format("%02x", b);
		}
		return format.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(getMD5("123456"));
	}
}
