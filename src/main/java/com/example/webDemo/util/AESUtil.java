package com.example.webDemo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES加解密工具类
 * 
 * @author gaoyuan
 *
 */
public class AESUtil {
	
	// key需要为16位
	private String key = "b@2dsvx#ejk5m9rt";

	//"算法/模式/补码方式"
	private static String TRANSFORMAT = "AES/ECB/PKCS5Padding";

	private AESUtil(String key){
		this.key = key;
	}
	
	private AESUtil(){
		
	}
	
	public static AESUtil getInstance(){
		return new AESUtil();
	}
	
	public static AESUtil getInstance(String key){
		return new AESUtil(key);
	}

    // 加密
    public String encrypt(String sSrc) throws Exception {
    	
    	if(sSrc == null || sSrc.length() == 0){
    		
    		return "";
    	}
        
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(TRANSFORMAT);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));

        return new String(Base64.encodeBase64URLSafe(encrypted), "UTF-8");
    }

    // 解密
    public String decrypt(String sSrc) throws Exception {
        
    	if(sSrc == null || sSrc.length() == 0){
    		
    		return "";
    	}

		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(TRANSFORMAT);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] encrypted = Base64.decodeBase64(sSrc);
		byte[] original = cipher.doFinal(encrypted);
		
		return new String(original, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
       
    	
        String enString = AESUtil.getInstance().encrypt("\ndfa\n中文");
        System.out.println("加密后的字串是：" + enString);
        
        String deString = AESUtil.getInstance().decrypt(enString);
        System.out.println("解密密后的字串是：" + deString);
    }
}
