package com.nvidia.cosmos.cloud.auth.encryptor.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nvidia.cosmos.cloud.auth.encryptor.BadPasswordException;
import com.nvidia.cosmos.cloud.auth.encryptor.IEncryptor;

public class BCryptEncoder implements IEncryptor {
	
	private static int STRENTH = 12;
	
	private static BCryptPasswordEncoder digest= new BCryptPasswordEncoder(STRENTH);

	@Override
	public String encrypt(String plainText) throws BadPasswordException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String plainText) throws BadPasswordException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)throws BadPasswordException, Exception {
		if( rawPassword == null ||  encodedPassword==null)
            throw new BadPasswordException( "Insufficient Password Cannot allow null" );
		return digest.matches(rawPassword, encodedPassword);
	}

	@Override
	public String encode(CharSequence rawPassword) throws BadPasswordException, Exception{
		if( rawPassword == null )
            throw new BadPasswordException( "Insufficient Password Cannot allow null" );
		
		return digest.encode(rawPassword);
	}

}
