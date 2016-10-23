
package com.nvidia.cosmos.cloud.auth.encryptor;



/**
 * This interface defines a contract for plugging in any type of Encryptor for the
 * passwords. The Encryptors may be Crypt, SHA, MD5, TripleDES or anything.
 * So long as they conform to this interface, they can be plugged into the Clidentity
 * System.
 * @author bprasad
 */
public interface IEncryptor {
	/**
     * Contract method that needs to be implemented by a Password encryption provider
     * for the Clidentity System.
     *
     * @param plainText The plain text password; that needs to be encrypted.
     * @return cipher equivalent of the password.
     */    
    public String encrypt( String plainText ) throws BadPasswordException,Exception;
    /**
     * Contract method that needs to be implemented by a Password encryption provider
     * for the Clidentity System.
     * @param plainText The plain text password; that needs to be encrypted.
     * @return cipher equivalent of the password.
     */    
    public String decrypt(String plainText) throws BadPasswordException,Exception;
    
    public boolean matches(CharSequence rawPassword, String encodedPassword)throws BadPasswordException, Exception;
    
    public String encode(CharSequence rawPassword)throws BadPasswordException, Exception;
    
    
    
}
