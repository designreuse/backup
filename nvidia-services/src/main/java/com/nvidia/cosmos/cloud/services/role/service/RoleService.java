package com.nvidia.cosmos.cloud.services.role.service;

import java.util.List;


import com.nvidia.cosmos.cloud.exceptions.RoleExistsException;
import com.nvidia.cosmos.cloud.exceptions.RoleNotFoundException;
import com.nvidia.cosmos.cloud.services.role.model.Role;

/**
 * @author pbatta
 *
 */
public interface RoleService {
	 
    /**
     * @param id
     * @return
     * @throws RoleNotFoundException
     */
    Role findById(int id) throws RoleNotFoundException;
    /**
     * @param role
     * @throws RoleExistsException
     */
    void saveRole(Role role) throws RoleExistsException;
    /**
     * @param role
     * @throws RoleNotFoundException
     */
    void updateRole(Role role) throws RoleNotFoundException;
    /**
     * @param id
     * @throws RoleNotFoundException
     */
    void deleteRoleById(int id) throws RoleNotFoundException;
    /**
     * @return
     */
    List<Role> findAllRoles(); 
    /**
     * @param name
     * @return
     * @throws RoleNotFoundException
     */
    Role findRoleByName(String name);
     
}
