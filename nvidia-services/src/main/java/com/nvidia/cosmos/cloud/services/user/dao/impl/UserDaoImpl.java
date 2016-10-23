package com.nvidia.cosmos.cloud.services.user.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.dao.UserDao;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserDao#findById(int)
     */
    public User findById(Long id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserDao#saveUser(com.nvidia.cosmos.cloud.services.model.User)
     */
    public void saveUser(User user) {
    		persist(user);
    }
    
    public void  mergeUser(User user) throws UserNameExitsException  {
		merge(user);
		
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserDao#deleteUserBySsn(java.lang.String)
     */
    public void deleteUserById(Long id) {
    	User user = new User();
    	user.setId(id);
    	delete(user);
    }
    
   /* public Long getMaxUID() {
       // Query query = getSession().createSQLQuery("select max(uid) from "+ServicesConstants.USER_TABLE_NAME);
    	Criteria criteria = createEntityCriteria();
         criteria.setProjection(Projections.max("uId"));
         Long maxUID=1000L;
         if(criteria.uniqueResult()!=null){
        	 maxUID = (Long)criteria.uniqueResult();
         }
        	return maxUID;
    }
    
    
    public Long getMaxGID() {
        // Query query = getSession().createSQLQuery("select max(uid) from "+ServicesConstants.USER_TABLE_NAME);
     	Criteria criteria = createEntityCriteria();
          criteria.setProjection(Projections.max("gId"));
          Long maxGID=1000L;
          if(criteria.uniqueResult()!=null){
         	 maxGID = (Long)criteria.uniqueResult();
          }
         	return maxGID;
     }*/
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.UserDao#findAllUsers()
     */
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }
 
    
    public User findUserByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (User) criteria.uniqueResult();
    }
    public User findUserByUserName(String userName) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("userName", userName));
    	return (User) criteria.uniqueResult();
    }
    
    public User findUserByEmail(String email) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("email", email));
    	
    	return (User) criteria.uniqueResult();
    }
    public List<User> findAllUsers(int customerId) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("customer.id", customerId));
    	return (List<User>) criteria.list();
    }

	@Override
	public Long getUserCount() {
		Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.count("id"));
        return (Long)criteria.uniqueResult();
  	}

	@Override
	public Long getActiveUserCount() {
		Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("status", "ACTIVE"));
    	criteria.setProjection(Projections.rowCount());
    	return (Long) criteria.uniqueResult();
	}

	@Override
	public Long getLoginFailedCount(int loginFailedAttempts) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ge("loginFailedCount",loginFailedAttempts));
		criteria.setProjection(Projections.rowCount()); 
		//System.out.println("Failed count["+criteria.uniqueResult()+"]");
        return (Long)criteria.uniqueResult();
	}

	@Override
	public List<User> findAllActiveUserList(Role role) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("role", "role");
		criteria.add(Restrictions.eq("status", "ACTIVE"));
		criteria.add(Restrictions.eq("role.name", role.getName()));
		return (List<User>) criteria.list();
	}
	
	
	@Override
	public User findByIdAndCustomerId(Long id, int customerId) throws UserNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("customer.id", customerId));
    	return (User) criteria.uniqueResult();
		
	}
}