package com.nvidia.cosmos.cloud.services.userauth.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.UserAuthExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;

/**
 * @author pbatta
 *
 */
public interface UserAuthService {
	 
    /**
     * @param id
     * @return
     * @throws UserAuthNotFoundException
     */
    UserAuth findById(int id) throws UserAuthNotFoundException;
    /**
     * @param userAuth
     * @throws UserAuthExistsException
     */
    void saveUserAuth(UserAuth userAuth) throws UserAuthExistsException;
    /**
     * @param userAuth
     * @throws UserAuthNotFoundException
     */
    void updateUserAuth(UserAuth userAuth) throws UserAuthNotFoundException;
    /**
     * @param id
     * @throws UserAuthNotFoundException
     */
    void deleteUserAuthById(int id) throws UserAuthNotFoundException;
    /**
     * @return
     */
    List<UserAuth> findAllUserAuths(); 
    /**
     * @param authToken
     * @return
     * @throws UserAuthNotFoundException
     */
    UserAuth findUserAuthByAuthToken(String authToken) throws UserAuthNotFoundException;
    
    List<UserAuth> findUserAuthByEmail(String email) throws UserAuthNotFoundException;
    /**
     * @param extToken
     * @return
     */
    UserAuth findUserAuthByExtToken(String extToken) throws UserAuthNotFoundException;
    public void mergeUserAuth(UserAuth userAuth)throws UserAuthNotFoundException;
}
