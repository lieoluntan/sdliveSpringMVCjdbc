package com.sdlive.utilityTalk;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class MD5Util {
	/***
	 * md5����
	 * @param md5
	 * @return
	 */
	public String MD5(String md5) 
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte anArray : array) {
				sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} 
		catch (java.security.NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
			return null;
		}

	}
/**
 * �������
 * @param content
 * @param key
 * @return
 */
	public String encrypts(String content, String key)
	{
		if(key == null || key.length() != 16)
		{
			System.err.println("AES key �ĳ��ȱ�����16λ��");
			return null;
		}
		try
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/NOPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = content.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0)
			{
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keyspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			if (encrypted == null){
				return null;
			}
			char[] hexArray = "0123456789abcdef".toCharArray();
			char[] hexChars = new char[encrypted.length * 2];
			for (int j = 0; j < encrypted.length; j++) {
				int v = encrypted[j] & 0xFF;
				hexChars[j * 2] = hexArray[v >>> 4];
				hexChars[j * 2 + 1] = hexArray[v & 0x0F];
			}
			return new String(hexChars);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �������ĺ������ַ����
	 * @param str
	 * @return
	 */
	public String encode(String str) { 
		String strUTF8 = null;
		try {  
			strUTF8 = URLEncoder.encode(str, "UTF-8"); 
			System.out.println(strUTF8); 
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
		return strUTF8;
	}  
	/*
	  public static byte[] decrypt(byte[] content, String password) {
	        try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");// ����AES��Key�����
	            kgen.init(128, new SecureRandom(password.getBytes()));
	            SecretKey secretKey = kgen.generateKey();// ����û����룬���һ����Կ
	            byte[] enCodeFormat = secretKey.getEncoded();// ���ػ�����ʽ����Կ
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// ת��ΪAESר����Կ
	            Cipher cipher = Cipher.getInstance("AES");// ����������
	            cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������
	            byte[] result = cipher.doFinal(content);  
	            return result; // ����   
	            
	   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	
	// ����
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
                System.out.print("KeyΪ��null");
                return null;
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
                System.out.print("Key���Ȳ���16λ");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
*/

}


