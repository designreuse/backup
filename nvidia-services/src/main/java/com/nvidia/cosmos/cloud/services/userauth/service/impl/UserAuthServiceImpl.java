package com.nvidia.cosmos.cloud.services.userauth.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.UserAuthExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.services.userauth.dao.UserAuthDao;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;
 
/**
 * @author pbatta
 *
 */
@Service("userAuthService")
@Transactional
public class UserAuthServiceImpl implements UserAuthService {
 
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private UserAuthDao dao;
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userAuth.service.UserAuthService#findById(int)
     */
    public UserAuth findById(int id) throws UserAuthNotFoundException{
    	UserAuth entity = dao.findById(id);
    	if(entity==null){
    		throw new UserAuthNotFoundException(messageSource.getMessage("userAuth.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userAuth.service.UserAuthService#saveUserAuth(com.nvidia.cosmos.cloud.services.userAuth.model.UserAuth)
     */
    public void saveUserAuth(UserAuth userAuth) throws UserAuthExistsException{
    	UserAuth exists = dao.findUserAuthByAuthToken(userAuth.getAuthToken());
    	if(exists!=null){
    		throw new UserAuthExistsException(messageSource.getMessage("userAuth.exists.authToken", new String[]{""+userAuth.getAuthToken()}, new Locale("en", "US")));
    	}
    	dao.saveUserAuth(userAuth);
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userAuth.service.UserAuthService#updateUserAuth(com.nvidia.cosmos.cloud.services.userAuth.model.UserAuth)
     */
    public void updateUserAuth(UserAuth userAuth)  throws UserAuthNotFoundException{
    	UserAuth entity = dao.findById(userAuth.getId());
    	if(entity==null){
    		throw new UserAuthNotFoundException(messageSource.getMessage("userAuth.notfound.id", new String[]{""+userAuth.getId()}, new Locale("en", "US")));
    	} else {
            entity.setExtToken(userAuth.getExtToken());
            entity.setUpdatedDate(new Date());
        }
        dao.saveUserAuth(entity);
    }
 
    public void mergeUserAuth(UserAuth userAuth)  throws UserAuthNotFoundException{
    	
        dao.mergeUserAuth(userAuth);
    }
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userAuth.service.UserAuthService#deleteUserAuthById(int)
     */
    public void deleteUserAuthById(int id) throws UserAuthNotFoundException{
    	UserAuth entity = dao.findById(id);
    	if(entity==null){
    		throw new UserAuthNotFoundException(messageSource.getMessage("userAuth.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        dao.deleteUserAuthById(id);
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.userAuth.service.UserAuthService#findAllUserAuths()
     */
    public List<UserAuth> findAllUserAuths() {
        return dao.findAllUserAuths();
    }
	@Override
	public UserAuth findUserAuthByAuthToken(String authToken) throws UserAuthNotFoundException, NoSuchMessageException, NonUniqueResultException {
		UserAuth entity = dao.findUserAuthByAuthToken(authToken);
		if(entity==null){
    		throw new UserAuthNotFoundException(messageSource.getMessage("userAuth.notfound.authToken", new String[]{authToken}, new Locale("en", "US")));
    	}
    	return entity;
	}
	@Override
	public UserAuth findUserAuthByExtToken(String extToken) throws UserAuthNotFoundException, NoSuchMessageException {
		UserAuth entity = dao.findUserAuthByExtToken(extToken);
		if(entity==null){
			throw new UserAuthNotFoundException(messageSource.getMessage("userAuth.notfound.extToken", new String[]{extToken}, new Locale("en", "US")));
		}
		return entity;
	}
	
	
	@Override
	public List<UserAuth> findUserAuthByEmail(String email) throws UserAuthNotFoundException, NoSuchMessageException {
		List<UserAuth> entity = dao.findUserAuthByEmail(email);
		if(entity==null){
    		throw new UserAuthNotFoundException(messageSource.getMessage("userAuth.notfound.authToken", new String[]{email}, new Locale("en", "US")));
    	}
    	return entity;
	}
     
}