
package com.nvidia.cosmos.cloud.auth.encryptor.crypt;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author prbatta
 *
 */

public class Crypt {
	private static Logger logger = LoggerFactory.getLogger(Crypt.class);
   // public static final String DESED_ENCRYPT_SCHEME ="DESede"; 
    //public static final String DES_ENCRYP_SCHEME ="DES"; 
   // public static final String DEFAULT_ENCRYPT_KEY ="This is a fairly long phrase used to encrypt";
    
    private KeySpec m_key;
    private SecretKeyFactory m_factory;
    private Cipher m_code;
    
    private static final String	UNIQUECODE_FORMAT = "UTF8";
    //Empty Constructor
    public Crypt(){
    	
    }
    public Crypt(String encryptionScheme, String encryptionKey)throws EncryptionException {
        if(encryptionKey == null){
            throw new IllegalArgumentException("encryption key was null");
        }
        if(encryptionKey.trim().length() < 24){
            throw new IllegalArgumentException("encryption key was less than 24 characters");
        }
        try{
            byte[] keyAsBytes = encryptionKey.getBytes(UNIQUECODE_FORMAT);
           // if(encryptionScheme.equals(DESED_ENCRYPT_SCHEME)){
            if(encryptionScheme.equals(getProperties().getProperty("desed_encrypt_scheme"))){
                m_key = new DESedeKeySpec(keyAsBytes);
           // }else if(encryptionScheme.equals(DES_ENCRYP_SCHEME)){
            }else if(encryptionScheme.equals(getProperties().getProperty("des_encrypt_scheme"))){
                m_key = new DESKeySpec(keyAsBytes);
            } else { throw new IllegalArgumentException("Encryption scheme not supported:"+ encryptionScheme);
            }
            m_factory = SecretKeyFactory.getInstance(encryptionScheme);
            m_code = Cipher.getInstance(encryptionScheme);
        }catch (InvalidKeyException inkex){
            throw new EncryptionException(inkex);
        }catch (UnsupportedEncodingException usex){
            throw new EncryptionException(usex);
        }catch (NoSuchAlgorithmException nsalg){
            throw new EncryptionException(nsalg);
        }catch (NoSuchPaddingException nspex){
            throw new EncryptionException(nspex);
        }
        
    }
    
    public Crypt(String encryptionScheme) throws EncryptionException{
       // this(encryptionScheme, DEFAULT_ENCRYPT_KEY);
    	 this(encryptionScheme, getProperties().getProperty("encrypt.key"));
    	
    }
    
    public synchronized String decrypt(String encryptedString) throws EncryptionException {
        if(encryptedString == null || encryptedString.trim().length() <= 0){
            throw new IllegalArgumentException("encrypted string was null or empty");
        }
        try{
        	SecretKey key = m_factory.generateSecret(m_key);
        	m_code.init(Cipher.DECRYPT_MODE, key);
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] decryptText = base64decoder.decodeBuffer(encryptedString);
            byte[] decryptedText = m_code.doFinal(decryptText);
            return bytes2String(decryptedText);
        }catch (Exception exp){
            throw new EncryptionException(exp);
        }
    }
    
    public String encrypt(String unencryptedString) throws EncryptionException {
        if(unencryptedString == null || unencryptedString.trim().length() == 0){
            throw new IllegalArgumentException(
                    "unencrypted string was null or empty");
        }
        try{
        	SecretKey key = m_factory.generateSecret(m_key);
            m_code.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptText = unencryptedString.getBytes(UNIQUECODE_FORMAT);
            byte[] encryptedText = m_code.doFinal(encryptText);
            BASE64Encoder base64encoder = new BASE64Encoder();
            return base64encoder.encode( encryptedText );
        }catch (Exception exp){
            throw new EncryptionException(exp);
        }
    }
    public synchronized String decrypt(String encryptedString, SecretKey key) throws EncryptionException {
    	if(encryptedString == null || encryptedString.trim().length() <= 0){
    		throw new IllegalArgumentException("encrypted string was null or empty");
    	}
    	try{
    		m_code = Cipher.getInstance(key.getAlgorithm());
    		m_code.init(Cipher.DECRYPT_MODE, key);
    		BASE64Decoder base64decoder = new BASE64Decoder();
    		byte[] decryptText = base64decoder.decodeBuffer(encryptedString);
    		byte[] decryptedText = m_code.doFinal(decryptText);
    		return bytes2String(decryptedText);
    	}catch (Exception exp){
    		throw new EncryptionException(exp);
    	}
    }
    
    public String encrypt(String unencryptedString, SecretKey key) throws EncryptionException {
    	if(unencryptedString == null || unencryptedString.trim().length() == 0){
    		throw new IllegalArgumentException(
    				"unencrypted string was null or empty");
    	}
    	try{
    		m_code = Cipher.getInstance(key.getAlgorithm());
    		m_code.init(Cipher.ENCRYPT_MODE, key);
    		byte[] encryptText = unencryptedString.getBytes(UNIQUECODE_FORMAT);
    		byte[] encryptedText = m_code.doFinal(encryptText);
    		BASE64Encoder base64encoder = new BASE64Encoder();
    		return base64encoder.encode( encryptedText );
    	}catch (Exception exp){
    		throw new EncryptionException(exp);
    	}
    }
    
    public static class EncryptionException extends Exception {
        public EncryptionException(Throwable thr){
            super(thr);
        }
    }
    
    private static String bytes2String(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++){
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }
    
    
    public static Properties getProperties(){
    	Properties prop=null;
       // ClassPathResource pFile = new ClassPathResource("application.properties");
       		prop = new Properties();
             try {
            	 InputStream is =Crypt.class.getClassLoader().getResourceAsStream("application.properties");
				prop.load(is);
			} catch (FileNotFoundException e) {
				logger.error("Error retriving application properties file",e.getMessage());
			} catch (IOException e) {
				logger.error("Error retriving application properties file",e.getMessage());
				e.printStackTrace();
			}
           /*	InputStream is =Crypt.class.getClassLoader().getResourceAsStream("application.properties");
           	if(is!=null){
                   prop.load(is);        	
           	}*/
       	
         return prop;        
       }
    
}