package com.nvidia.cosmos.cloud.services.role.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.RoleExistsException;
import com.nvidia.cosmos.cloud.exceptions.RoleNotFoundException;
import com.nvidia.cosmos.cloud.services.role.dao.RoleDao;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
 
/**
 * @author pbatta
 *
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
 
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private RoleDao dao;
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.role.service.RoleService#findById(int)
     */
    public Role findById(int id) throws RoleNotFoundException{
    	Role entity = dao.findById(id);
    	if(entity==null){
    		throw new RoleNotFoundException(messageSource.getMessage("role.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.role.service.RoleService#saveRole(com.nvidia.cosmos.cloud.services.role.model.Role)
     */
    public void saveRole(Role role) throws RoleExistsException{
    	Role exists = dao.findRoleByName(role.getName());
    	if(exists!=null){
    		throw new RoleExistsException(messageSource.getMessage("role.exists.email", new String[]{""+role.getName()}, new Locale("en", "US")));
    	}
    	dao.saveRole(role);
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.role.service.RoleService#updateRole(com.nvidia.cosmos.cloud.services.role.model.Role)
     */
    public void updateRole(Role role)  throws RoleNotFoundException{
    	Role entity = dao.findById(role.getId());
    	if(entity==null){
    		throw new RoleNotFoundException(messageSource.getMessage("role.notfound.id", new String[]{""+role.getId()}, new Locale("en", "US")));
    	} else {
            entity.setName(role.getName());
            entity.setDescription(role.getDescription());
            entity.setUpdatedDate(new Date());
        }
        dao.saveRole(entity);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.role.service.RoleService#deleteRoleById(int)
     */
    public void deleteRoleById(int id) throws RoleNotFoundException{
    	Role entity = dao.findById(id);
    	if(entity==null){
    		throw new RoleNotFoundException(messageSource.getMessage("role.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        dao.deleteRoleById(id);
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.role.service.RoleService#findAllRoles()
     */
    public List<Role> findAllRoles() {
        return dao.findAllRoles();
    }

	/* (non-Javadoc)
	 * @see com.nvidia.cosmos.cloud.services.role.service.RoleService#findRoleByName(java.lang.String)
	 */
	@Override
	public Role findRoleByName(String name) {
		return dao.findRoleByName(name);
	}
   
     
}