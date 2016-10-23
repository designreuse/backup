package com.nvidia.cosmos.cloud.services.token.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.TokenExistsException;
import com.nvidia.cosmos.cloud.exceptions.TokenNotFoundException;
import com.nvidia.cosmos.cloud.services.token.model.Token;

/**
 * @author pbatta
 *
 */
public interface TokenService {
	 
    /**
     * @param id
     * @return
     * @throws TokenNotFoundException
     */
    Token findById(int id) throws TokenNotFoundException;
    /**
     * @param token
     * @throws TokenExistsException
     */
    void saveToken(Token token) throws TokenExistsException;
    /**
     * @param token
     * @throws TokenNotFoundException
     */
    void updateToken(Token token) throws TokenNotFoundException;
    /**
     * @param id
     * @throws TokenNotFoundException
     */
    void deleteTokenById(int id) throws TokenNotFoundException;
    /**
     * @return
     */
    List<Token> findAllTokens(); 
    /**
     * @param email
     * @return
     * @throws TokenNotFoundException
     */
    public Token findTokenByKey(String key) throws TokenNotFoundException;
    /**
     * @return
     */
    List<Token> findAllTokens(int customerId) throws CustomerNotFoundException; 
}
