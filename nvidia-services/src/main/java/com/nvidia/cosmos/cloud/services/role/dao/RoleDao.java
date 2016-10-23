/**
 * 
 */
package com.nvidia.cosmos.cloud.services.role.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.services.role.model.Role;

/**
 * @author pbatta
 *
 */
public interface RoleDao {
 
    /**
     * @param id
     * @return
     */
    Role findById(int id);
 
    /**
     * @param roles
     */
    void saveRole(Role role);
     
    /**
     * @param ssn
     */
    void deleteRoleById(int id);
     
    /**
     * @return
     */
    List<Role> findAllRoles();
 
    /**
     * @param name
     * @return
     */
    Role findRoleByName(String name);
   
}
