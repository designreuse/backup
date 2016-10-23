package com.nvidia.cosmos.cloud.services.user.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */
public interface UserService {
	 
    /**
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    User findById(Long id) throws UserNotFoundException;
    /**
     * @param user
     * @throws UserExistsException
     */
    void saveUser(User user) throws UserExistsException;
    /**
     * @param user
     * @throws UserNotFoundException
     * @throws UserNameExitsException 
     */
   
    void updateUser(User user) throws UserNotFoundException,UserExistsException, UserNameExitsException;
    /**
     * @param id
     * @throws UserNotFoundException
     */
    void deleteUserById(Long id) throws UserNotFoundException;
    /**
     * @return
     */
    List<User> findAllUsers(); 
    /**
     * @param email
     * @return
     * @throws UserNotFoundException
     */
    public User findUserByEmail(String email) throws UserNotFoundException;
    /**
     * @return
     */
    List<User> findAllUsers(int customerId) throws CustomerNotFoundException; 
    /**
     * @param userName
     * @return
     * @throws UserNotFoundException
     */
    public User findUserByUserName(String userName) throws UserNotFoundException;
    
    public User findUserByUserName(String userName,String password) throws UserNotFoundException;
    
    public void mergeUser(User user)  throws UserNotFoundException,  UserNameExitsException ;
    
    public void deleteUser(User user) throws UserNotFoundException;
    
    List<User> findAllActiveUserList(Role role);
    
    public User findByIdAndCustomerId(Long id, int customerId) throws UserNotFoundException;
}
