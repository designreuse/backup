/**
 * 
 */
package com.nvidia.cosmos.cloud.services.userauth.dao;

import java.util.List;

import org.springframework.context.NoSuchMessageException;

import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;

/**
 * @author pbatta
 *
 */
public interface UserAuthDao {
 
    /**
     * @param id
     * @return
     */
    UserAuth findById(int id);
 
    /**
     * @param userAuths
     */
    void saveUserAuth(UserAuth userAuth);
     
    /**
     * @param ssn
     */
    void deleteUserAuthById(int id);
     
    /**
     * @return
     */
    List<UserAuth> findAllUserAuths();
 
    /**
     * @param name
     * @return
     */
    UserAuth findUserAuthByAuthToken(String authToken);
    
    List<UserAuth> findUserAuthByEmail(String email) throws UserAuthNotFoundException;
    /**
     * @param name
     * @return
     */
    UserAuth findUserAuthByExtToken(String extToken);
    
    public Long getLoginUsers();
    
    public void mergeUserAuth(UserAuth userAuth)throws UserAuthNotFoundException;
   
}
