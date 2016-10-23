
package com.nvidia.cosmos.cloud.auth.encryptor.crypt; //NOPMD


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nvidia.cosmos.cloud.auth.encryptor.BadPasswordException;
import com.nvidia.cosmos.cloud.auth.encryptor.IEncryptor;

/**
 * This is a default implementation of the Crypt Encryptor for the collab System.
 * @author prbatta
 */
//@SuppressWarnings("PMD")
public class DefaultCryptEncryptor implements IEncryptor {
	private static Logger logger = LoggerFactory.getLogger(DefaultCryptEncryptor.class);
	
   public DefaultCryptEncryptor(){
	  
		
   }


   // private static String encryptionKey = "ASDF asdf 1234 8983 jklasdf J2Jaf8";
    private static Crypt encrypter = null;
    /** Creates a new instance of DefaultCryptEncryptor */
   

    /**
     * The contract method fulfilled by this implementor.
     * @param plainText The plain text password.
     * @return cipher equivalent of the plain text password, encrypted by the Crypt.
     */
    public String encrypt(String plainText) throws BadPasswordException,Exception {
    	try{
    		logger.debug("CRYPTO property *********************************{}",Crypt.getProperties().getProperty("desed_encrypt_scheme"));
            String encryptionScheme = Crypt.getProperties().getProperty("desed_encrypt_scheme");//Crypt.DESED_ENCRYPT_SCHEME;
            encrypter =  new Crypt( encryptionScheme, Crypt.getProperties().getProperty("encrypt.secret") );    //new Crypt( encryptionScheme, encryptionKey );
        }catch(Exception e){
            e.printStackTrace();
        }
        /*
         *  Here the passwords will create wat ever the plainText length
         *  Since this modifiedcations done because while importing the users
         *  the username will have more than 8 characters.
         */
        if( plainText == null )
            throw new BadPasswordException( "Insufficient Password Cannot allow null" );

        return encrypter.encrypt( plainText );
    }
    /**
     * The contract method fulfilled by this implementor.
     * The salt chosen for encryption is the first two characters of the plain text
     * password input.
     * @param plainText The plain text password.
     * @return cipher equivalent of the plain text password, encrypted by the Crypt.
     */
    public String decrypt(String plainText) throws BadPasswordException,Exception {
    	try{
            String encryptionScheme = Crypt.getProperties().getProperty("desed_encrypt_scheme");//Crypt.DESED_ENCRYPT_SCHEME;
            encrypter = new Crypt(encryptionScheme, Crypt.getProperties().getProperty("encrypt.secret"));//new Crypt( encryptionScheme, encryptionKey );
        }catch(Exception e){
            //e.getMessage();
        }
        /*
         *  Here the passwords will create wat ever the plainText length
         *  Since this modifiedcations done because while importing the users
         */
        if( plainText == null ){
            throw new BadPasswordException( "Insufficient Password Cannot allow null" );
        }

        return encrypter.decrypt( plainText );
    }
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
}
