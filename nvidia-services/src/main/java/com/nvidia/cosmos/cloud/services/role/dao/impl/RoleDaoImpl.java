package com.nvidia.cosmos.cloud.services.role.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.role.dao.RoleDao;
import com.nvidia.cosmos.cloud.services.role.model.Role;

/**
 * @author pbatta
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.RoleDao#findById(int)
     */
    public Role findById(int id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.RoleDao#saveRole(com.nvidia.cosmos.cloud.services.model.Role)
     */
    public void saveRole(Role role) {
        persist(role);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.RoleDao#deleteRoleBySsn(java.lang.String)
     */
    public void deleteRoleById(int id) {
    	Role role = new Role();
    	role.setId(id);
    	delete(role);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.RoleDao#findAllRoles()
     */
    @SuppressWarnings("unchecked")
    public List<Role> findAllRoles() {
        Criteria criteria = createEntityCriteria();
        return (List<Role>) criteria.list();
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.role.dao.RoleDao#findRoleByName(java.lang.String)
     */
    public Role findRoleByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Role) criteria.uniqueResult();
    }
    
 
}