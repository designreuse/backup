/**
 * 
 */
package com.nvidia.cosmos.cloud.services.user.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */
public interface UserDao {
 
    /**
     * @param id
     * @return
     */
    User findById(Long id);
 
    /**
     * @param user
     */
    void saveUser(User user);
     
    /**
     * @param ssn
     */
    void deleteUserById(Long id);
     
    /**
     * @return
     */
    List<User> findAllUsers();
 
    /**
     * @param name
     * @return
     */
    User findUserByName(String name);
    /**
     * @param email
     * @return
     */
    User findUserByEmail(String email);
    /**
     * @param customerId
     * @return
     */
    List<User> findAllUsers(int customerId);
    /**
     * @param name
     * @return
     */
    User findUserByUserName(String userName);
    
  //  public Long getMaxUID();
    
   // public Long getMaxGID();
    
    
    public void mergeUser(User user) throws UserNameExitsException ;
    
    public Long getUserCount();
    
    public Long getActiveUserCount();
    
    public Long getLoginFailedCount(int loginFailedAttempts);
    
    /**
     * 
     * @return
     */
    List<User> findAllActiveUserList(Role role);
    
    public User findByIdAndCustomerId(Long id, int customerId) throws UserNotFoundException;
    
}
