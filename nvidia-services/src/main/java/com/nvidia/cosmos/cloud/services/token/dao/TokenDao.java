/**
 * 
 */
package com.nvidia.cosmos.cloud.services.token.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.services.token.model.Token;

/**
 * @author pbatta
 *
 */
public interface TokenDao {
 
    /**
     * @param id
     * @return
     */
    Token findById(int id);
 
    /**
     * @param token
     */
    void saveToken(Token token);
     
    /**
     * @param ssn
     */
    void deleteTokenById(int id);
     
    /**
     * @return
     */
    List<Token> findAllTokens();
 
    /**
     * @param name
     * @return
     */
    Token findTokenByKey(String key);
    /**
     * @param customerId
     * @return
     */
    List<Token> findAllTokens(int customerId);
}
