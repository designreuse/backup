/**
 * 
 */
package com.nvidia.cosmos.cloud.auth.encryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author prbatta
 *
 */
public class EncryptTest {
   
   private static Logger logger = LoggerFactory.getLogger(EncryptTest.class);
   public EncryptTest(){
	  
		
   }
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		IEncryptor iencrypt = EncryptorFactory.getEncryptor();
//	   System.out.println(""+iencrypt.encrypt("admin"));
		String tr = "Z4jZyqz9LKTV1/EAe/wL4SSwEQeaqMzh";
		tr = tr.replaceAll("/", "~");
		logger.info(tr);
		logger.info(""+iencrypt.decrypt("Z4jZyqz9LKTV1/EAe/wL4SSwEQeaqMzh"));
	   
	   String[] str = "tresyop@yopmail.com^true".split("\\^");
	   logger.info(str[0]);
	}

}
