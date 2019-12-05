package com.server.api.common;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.simple.SimpleLogger;



/**
 * Created by wjl on 2017/5/15.
 */
public class Cipher {
	
//	public static String aesContacts(String content,String key) throws Exception {
//		return Cipher.base64Encode(Cipher.encrypt(content, key));
//	}

	public static byte[] encrypt(String content, String key) {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			javax.crypto.Cipher cipher;
			cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
			return encrypted;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(byte[] content, String key) {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			javax.crypto.Cipher cipher;
			cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal(content);
			String originalString = new String(original, "UTF-8");
			return originalString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static String base64Encode(byte[] content) {
//		String base = new String(Base64.encode(content));
//		return base;
//
//	}
//
//	public static byte[] base64Decode(String content) throws Exception  {
//		return Base64.decode(content);
//	}
	
	
    public static byte[] aes256bitCBC(String content,byte[] key,byte[] iv){
    	try{
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            javax.crypto.Cipher cipher;
            cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
            byte[] cipherData = cipher.doFinal(content.getBytes("UTF-8"));
/*            System.out.println(new String(cipherData));
            System.out.println(Base64.encode(cipherData));*/
            return cipherData;   	
        } catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e){
			e.printStackTrace();
		}
		return null;
    }
   
//	public static byte[] RSAEncode (String str) throws Exception {
//		SimpleLogger.logInfo(Cipher.class,"#########################################################################");
//		SimpleLogger.logInfo(Cipher.class,"public2.pem的位置放在"+"D:\\workspace\\public2.pem");
//	    FileInputStream fis = new FileInputStream("D:\\workspace\\public2.pem");
//		byte[] key=new byte[266];
//		fis.read(key);
//		Security.addProvider(new BouncyCastleProvider());
//		ByteArrayInputStream bais = new ByteArrayInputStream(key);
//		PEMReader reader = new PEMReader(new InputStreamReader(bais), new PasswordFinder() {
//		    public char[] getPassword() {
//		        return "".toCharArray();
//		    }
//		});
//		PublicKey pubk = (PublicKey) reader.readObject();
//		reader.close();
//		System.out.println(pubk);
//		javax.crypto.Cipher cipher;
//        cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, pubk);
//        byte[] results=cipher.doFinal(str.getBytes());
//        return results;
//	}
	    
	public static String md5(String string) 
	{
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) hex.append("0");
				hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	public static byte[] SHA256(String strText){  
	    // 返回值  
		byte[] byteResult = null;  
	    // 是否是有效字符串  
	    if (strText != null && strText.length() > 0){  
	    	try{  
		        // SHA 加密开始  
		        // 创建加密对象 并傳入加密類型  
		        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");  
		        // 传入要加密的字符串  
		        messageDigest.update(strText.getBytes());  
		        // 得到 byte 類型结果  
		        byteResult = messageDigest.digest();  
		      }  
		      catch (NoSuchAlgorithmException e){  
		    	  e.printStackTrace();  
		      }  
	    }  
	    return byteResult;  
	  }  
	
	public static String SHA256Hex(String strText){
		return bytes2Hex(SHA256(strText));
	}
	
	private static String bytes2Hex(byte[] bts) {
		String des = "";
	    String tmp = null;
	    for (int i = 0; i < bts.length; i++) {
	        tmp = (Integer.toHexString(bts[i] & 0xFF));
	        if (tmp.length() == 1) {
	           des += "0";
	        }
	           des += tmp;
	    }
	        return des;
	}

 }
