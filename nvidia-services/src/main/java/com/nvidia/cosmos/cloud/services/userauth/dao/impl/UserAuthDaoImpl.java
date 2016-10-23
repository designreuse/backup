package com.nvidia.cosmos.cloud.services.userauth.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.userauth.dao.UserAuthDao;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;

/**
 * @author pbatta
 *
 */
@Repository("userAuthDao")
public class UserAuthDaoImpl extends AbstractDao<Integer, UserAuth> implements UserAuthDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserAuthDao#findById(int)
     */
    public UserAuth findById(int id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserAuthDao#saveUserAuth(com.nvidia.cosmos.cloud.services.model.UserAuth)
     */
    public void saveUserAuth(UserAuth userAuth) {
        persist(userAuth);
    }
    
    public void mergeUserAuth(UserAuth userAuth) throws UserAuthNotFoundException{
        merge(userAuth);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserAuthDao#deleteUserAuthBySsn(java.lang.String)
     */
    public void deleteUserAuthById(int id) {
    	UserAuth userAuth = new UserAuth();
    	userAuth.setId(id);
    	delete(userAuth);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserAuthDao#findAllUserAuths()
     */
    @SuppressWarnings("unchecked")
    public List<UserAuth> findAllUserAuths() {
        Criteria criteria = createEntityCriteria();
        return (List<UserAuth>) criteria.list();
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userauth.dao.UserAuthDao#findUserAuthByAuthToken(java.lang.String)
     */
    public UserAuth findUserAuthByAuthToken(String authToken)throws NonUniqueResultException {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("authToken", authToken));
        return (UserAuth) criteria.uniqueResult();
    }
    
    @Override  
   public  List<UserAuth> findUserAuthByEmail(String email) throws UserAuthNotFoundException{
    	Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (List<UserAuth>) criteria.list();
    }
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userauth.dao.UserAuthDao#findUserAuthByExtToken(java.lang.String)
     */
    public UserAuth findUserAuthByExtToken(String extToken) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("extToken", extToken));
    	return (UserAuth) criteria.uniqueResult();
    }

	@Override
	public Long getLoginUsers() {
		Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.count("id"));
        return (Long)criteria.uniqueResult();
	}
	
	
    
    
 
}