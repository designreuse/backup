package com.nvidia.cosmos.cloud.services.token.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.TokenExistsException;
import com.nvidia.cosmos.cloud.exceptions.TokenNotFoundException;
import com.nvidia.cosmos.cloud.services.customer.dao.CustomerDao;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.token.dao.TokenDao;
import com.nvidia.cosmos.cloud.services.token.model.Token;
import com.nvidia.cosmos.cloud.services.token.service.TokenService;
 
/**
 * @author pbatta
 *
 */
@Service("tokenService")
@Transactional
public class TokenServiceImpl implements TokenService {
 
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private TokenDao dao;
    /**
     * 
     */
    @Autowired
    private CustomerDao customerdao;
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.token.service.TokenService#findById(int)
     */
    public Token findById(int id) throws TokenNotFoundException{
    	Token entity = dao.findById(id);
    	if(entity==null){
    		throw new TokenNotFoundException(messageSource.getMessage("token.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.token.service.TokenService#saveToken(com.nvidia.cosmos.cloud.services.token.model.Token)
     */
    public void saveToken(Token token) throws TokenExistsException{
    	Token exists = dao.findTokenByKey(token.getKey());
    	if(exists!=null){
    		throw new TokenExistsException(messageSource.getMessage("token.exists.key", new String[]{""+token.getKey()}, new Locale("en", "US")));
    	}
    	dao.saveToken(token);
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.token.service.TokenService#updateToken(com.nvidia.cosmos.cloud.services.token.model.Token)
     */
    public void updateToken(Token token)  throws TokenNotFoundException{
    	Token entity = dao.findById(token.getId());
    	if(entity==null){
    		throw new TokenNotFoundException(messageSource.getMessage("token.notfound.id", new String[]{""+token.getId()}, new Locale("en", "US")));
    	} else {
            entity.setKey(token.getKey());
            entity.setUpdatedDate(new Date());
        }
        dao.saveToken(entity);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.token.service.TokenService#deleteTokenById(int)
     */
    public void deleteTokenById(int id) throws TokenNotFoundException{
    	Token entity = dao.findById(id);
    	if(entity==null){
    		throw new TokenNotFoundException(messageSource.getMessage("token.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        dao.deleteTokenById(id);
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.token.service.TokenService#findAllTokens()
     */
    public List<Token> findAllTokens() {
        return dao.findAllTokens();
    }
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.token.service.TokenService#findTokenByEmail(java.lang.String)
     */
    public Token findTokenByKey(String key) throws TokenNotFoundException{
    	Token entity = dao.findTokenByKey(key);
    	if(entity==null){
    		throw new TokenNotFoundException(messageSource.getMessage("token.notfound.key", new String[]{key}, new Locale("en", "US")));
    	}
    	return entity;
    }
    public List<Token> findAllTokens(int customerId) throws CustomerNotFoundException{
    	Customer entity = customerdao.findById(customerId);
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+customerId}, new Locale("en", "US")));
    	}
    	return dao.findAllTokens(customerId);
    }
     
}