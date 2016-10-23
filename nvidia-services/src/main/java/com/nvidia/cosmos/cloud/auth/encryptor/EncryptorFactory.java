package com.nvidia.cosmos.cloud.auth.encryptor;

import com.nvidia.cosmos.cloud.auth.encryptor.crypt.BCryptEncoder;
//import com.nvidia.cosmos.cloud.auth.encryptor.crypt.BCryptEncoder;
import com.nvidia.cosmos.cloud.auth.encryptor.crypt.DefaultCryptEncryptor;

/**
 * The factory that is used to get the required Encryptor, that is configured for
 * the Nvidia system.
 * @author bprasad
 */
public class EncryptorFactory
{
    /** Creates a new instance of EncryptorFactory */
    private EncryptorFactory()
    {
    }
    
    /**
     * This method gets the Encryptor configured for the collab System. The specific
     * encryptor is argumented to the method.
     * 
     * this method lowers the case of the format, so as to normalise. Hence the case
     * may not be paid attention to, so long as the characters are consistent.
     * @return
     */    
    public static IEncryptor getEncryptor() throws Exception
    {
        return new DefaultCryptEncryptor();
    }
    
    public static IEncryptor getBCryptEncryptor() throws Exception
    {
        return new BCryptEncoder();
    }
}
