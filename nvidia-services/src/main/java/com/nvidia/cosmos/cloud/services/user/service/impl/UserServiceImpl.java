package com.nvidia.cosmos.cloud.services.user.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.services.customer.dao.CustomerDao;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.dao.UserDao;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
 
/**
 * @author pbatta
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private UserDao dao;
    /**
     * 
     */
    @Autowired
    private CustomerDao customerdao;
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.user.service.UserService#findById(int)
     */
    public User findById(Long id) throws UserNotFoundException{
    	User entity = dao.findById(id);
    	if(entity==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.user.service.UserService#saveUser(com.nvidia.cosmos.cloud.services.user.model.User)
     */
    public void saveUser(User user) throws UserExistsException{
    	User exists = dao.findUserByEmail(user.getEmail());
    	if(exists!=null){
    		throw new UserExistsException(messageSource.getMessage("user.exists.email", new String[]{""+user.getEmail()}, new Locale("en", "US")));
    	}
    	//user.setgId(dao.getMaxGID()+1);
       // user.setuId(dao.getMaxUID()+1);
    	dao.saveUser(user);
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.user.service.UserService#updateUser(com.nvidia.cosmos.cloud.services.user.model.User)
     */
    public void updateUser(User user)  throws UserNotFoundException,UserExistsException, UserNameExitsException{
    	User entity = dao.findById(user.getId());
    	if(entity==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.id", new String[]{""+user.getId()}, new Locale("en", "US")));
    	} 
    	User existingUser  = dao.findUserByUserName(user.getUserName());
    	if((existingUser!=null)&& (existingUser.getUserName().equals(user.getUserName())) && (existingUser.getId()!=user.getId())){
    		throw new UserNameExitsException(messageSource.getMessage("username.exists.name",
					new String[] { "" + user.getUserName() }, new Locale("en", "US")));
    	}
    		//UserName should not match organization name, that cause problems in quay queries.
        	Customer customer= customerdao.findCustomer(user);
        	if(customer.getName().equals(user.getUserName())){
        		throw new UserExistsException("User already exists");
        	}else{	
				entity.setName(user.getName());
				entity.setEmail(user.getEmail());
				entity.setPhone(user.getPhone());
				entity.setPassword(user.getPassword());
				/*try {
					System.out.println("Generating password for ["+user.getPassword()+"]");
					entity.setPassword(EncryptorFactory.getBCryptEncryptor().encode(user.getPassword()));
					} catch (Exception e) {
					logger.error("Error while encoding password",e);
				}*/
				entity.setStatus(user.getStatus());
				entity.setUpdatedDate(new Date());
				entity.setLastLogin(user.getLastLogin());
				entity.setqAuthToken(user.getqAuthToken());
				entity.setUserName(user.getUserName());
				entity.setSentEmail(user.isSentEmail());
				entity.setPasswordKey(user.getPasswordKey());
				if(user.getRole().getName().equals(ServicesConstants.CUSTOMER_ADMIN)){
					entity.setgId(user.getgId());
					entity.setuId(user.getuId());
				}
				dao.mergeUser(entity);
        	
        }
    }
    
    
    public void mergeUser(User user)  throws UserNotFoundException, UserNameExitsException {
    	//User entity = dao.findById(user.getId());
    	if(dao.findById(user.getId())==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.id", new String[]{""+user.getId()}, new Locale("en", "US")));
    	} else {
    		dao.mergeUser(user);
        }
        
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.user.service.UserService#deleteUserById(int)
     */
    public void deleteUserById(Long id) throws UserNotFoundException{
    	User entity = dao.findById(id);
    	if(entity==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        dao.deleteUserById(id);
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.user.service.UserService#findAllUsers()
     */
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.user.service.UserService#findUserByEmail(java.lang.String)
     */
    public User findUserByEmail(String email) throws UserNotFoundException{
    	User entity = dao.findUserByEmail(email);
    	if(entity==null){
    		//throw new UserNotFoundException(messageSource.getMessage("user.notfound.email", new String[]{email}, new Locale("en", "US")));
    		throw new UserNotFoundException(messageSource.getMessage("username.invalid", null, new Locale("en", "US")));
    	}
    	return entity;
    }
    public List<User> findAllUsers(int customerId) throws CustomerNotFoundException{
    	Customer entity = customerdao.findById(customerId);
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+customerId}, new Locale("en", "US")));
    	}
    	return dao.findAllUsers(customerId);
    }
    public User findUserByUserName(String userName) throws UserNotFoundException{
    	User entity = dao.findUserByUserName(userName);
    	if(entity==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.userName", new String[]{userName}, new Locale("en", "US")));
    	}
    	return entity;
    }
    
    
    
    
    public User findUserByUserName(String userName,String password) throws UserNotFoundException{
    	User entity = dao.findUserByUserName(userName);
    	if(entity==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.userName", new String[]{userName}, new Locale("en", "US")));
    	}
    	try {
			//if(EncryptorFactory.getBCryptEncryptor().matches(getHashCode(password), entity.getPassword())){
    		if(EncryptorFactory.getBCryptEncryptor().matches(password, entity.getPassword()) 
    				|| EncryptorFactory.getBCryptEncryptor().matches(getHashCode(password), entity.getPassword())){
				return entity;
			}
		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
	    	
		} 
       	return null;
    }
    
    private static String getHashCode(String msg){
	  	   StringBuffer hexString = new StringBuffer();
	  		MessageDigest digester=null;
	  		try {
	  			 digester = MessageDigest.getInstance("MD5");
	  			digester.update(msg.getBytes());
	  			 byte[] hash = digester.digest();
	  		        for (int i = 0; i < hash.length; i++) {
	  		            if ((0xff & hash[i]) < 0x10) {
	  		                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
	  		            }
	  		            else {
	  		                hexString.append(Integer.toHexString(0xFF & hash[i]));
	  		            }
	  		        }
	  			
	  		} catch (NoSuchAlgorithmException e) {
	  			logger.error(e.getMessage());
	  		}
	  		return hexString.toString();
	     }
    
    public void deleteUser(User user) throws UserNotFoundException{
    	User entity = dao.findById(user.getId());
    	if(entity==null){
    		throw new UserNotFoundException(messageSource.getMessage("user.notfound.id", new String[]{""+user.getgId()}, new Locale("en", "US")));
    	}
        dao.deleteUserById(entity.getId());
    }

    @Override
	public List<User> findAllActiveUserList(Role role) {
		return dao.findAllActiveUserList(role);
	}
    
    public User findByIdAndCustomerId(Long id, int customerId) throws UserNotFoundException{
    	User entity = dao.findByIdAndCustomerId(id,customerId);
    	return entity;
    }
}
