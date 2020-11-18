package com.zjKingdee.androidServer.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionAlgorithmUtil {
	
	public static String encryptionAlgorithm(String msg) throws NoSuchAlgorithmException {
		return sha(md5(msg));
	}
	
	public static String md5(String msg) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(msg.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	public static String sha(String msg) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(msg.getBytes());
		return new BigInteger(1,md.digest()).toString(32);
	}
}
