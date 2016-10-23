package com.nvidia.cosmos.cloud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncryptionUtil {

		public static String getHashCode(String authToken) throws NoSuchAlgorithmException {
			MessageDigest md5Handle = MessageDigest.getInstance("MD5");

			md5Handle.reset();
			md5Handle.update(authToken.getBytes());

			byte md5Digest[] = md5Handle.digest();

			StringBuffer authKey = new StringBuffer();

			for (int position = 0; position < md5Digest.length; position++) {
				authKey.append(Integer.toHexString(0xFF & md5Digest[position]));
			}
			return authKey.toString();
		}
	
	 public static String getHashCodePassword(String msg){
	  	   StringBuffer hexString = new StringBuffer();
	  		MessageDigest digester=null;
	  		try {
	  			 digester = MessageDigest.getInstance("MD5");
	  			digester.update(msg.getBytes());
	  			 byte[] hash = digester.digest();
	  		        for (int i = 0; i < hash.length; i++) {
	  		            if ((0xff & hash[i]) < 0x10) {
	  		                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
	  		            }
	  		            else {
	  		                hexString.append(Integer.toHexString(0xFF & hash[i]));
	  		            }
	  		        }
	  			
	  		} catch (NoSuchAlgorithmException e) {
	  			
	  		}
	  		return hexString.toString();
	     }

}
