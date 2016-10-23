package com.nvidia.cosmos.cloud.services.token.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.token.dao.TokenDao;
import com.nvidia.cosmos.cloud.services.token.model.Token;

/**
 * @author pbatta
 *
 */
@Repository("tokenDao")
public class TokenDaoImpl extends AbstractDao<Integer, Token> implements TokenDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.TokenDao#findById(int)
     */
    public Token findById(int id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.TokenDao#saveToken(com.nvidia.cosmos.cloud.services.model.Token)
     */
    public void saveToken(Token token) {
        persist(token);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.TokenDao#deleteTokenBySsn(java.lang.String)
     */
    public void deleteTokenById(int id) {
    	Token token = new Token();
    	token.setId(id);
    	delete(token);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.TokenDao#findAllTokens()
     */
    @SuppressWarnings("unchecked")
    public List<Token> findAllTokens() {
        Criteria criteria = createEntityCriteria();
        return (List<Token>) criteria.list();
    }
 
    
    public Token findTokenByKey(String key) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("key", key));
        return (Token) criteria.uniqueResult();
    }
    
   
    public List<Token> findAllTokens(int customerId) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("customer.id", customerId));
    	return (List<Token>) criteria.list();
    }
}