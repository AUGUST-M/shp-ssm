package com.shp.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import Decoder.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtil {
	private static final String DES = "DES";
	private static final String DES_KEY = "db!!@#$%";
	private static final String EN_KEY = "shpssm!,";
	
	public static String encrypt(String data) throws Exception{
		return encrypt(EN_KEY + data, DES_KEY);
	}
	
	public static String decrypt(String data) throws Exception{
		String[] strs = decrypt(data, DES_KEY).split(",");
		if(strs.length < 2){
			throw new Exception("DES加密算法解密失败");
		}
		return strs[1];
	}
	
	public static String encrypt(String data, String key) throws Exception{
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}
	
	public static String decrypt(String data, String key) throws Exception{
		if(data == null){
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf, key.getBytes());
		return new String(bt);
	}
	
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secretKey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);

		return cipher.doFinal(data);
	}

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secretKey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

		return cipher.doFinal(data);
	}
	
	public static void main(String[] args) throws Exception {
		String data = "root";
		System.out.println(encrypt(data));
		System.out.println(decrypt(encrypt(data)));
	}
}
