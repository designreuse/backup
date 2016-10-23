package com.nvidia.cosmos.cloud.services.entitlement.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.entitlement.dao.EntitlementDao;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;

/**
 * @author pbatta
 *
 */
@Repository("entitlementDao")
public class EntitlementDaoImpl extends AbstractDao<Integer, Entitlement> implements EntitlementDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.EntitlementDao#findById(int)
     */
    public Entitlement findById(int id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.EntitlementDao#saveEntitlement(com.nvidia.cosmos.cloud.services.model.Entitlement)
     */
    public void saveEntitlement(Entitlement entitlement){
    	persist(entitlement);

    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.EntitlementDao#deleteEntitlementBySsn(java.lang.String)
     */
    public void deleteEntitlementById(int id) {
    	Entitlement entitlement = new Entitlement();
    	entitlement.setId(id);
    	delete(entitlement);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.EntitlementDao#findAllEntitlements()
     */
    @SuppressWarnings("unchecked")
    public List<Entitlement> findAllEntitlements() {
        Criteria criteria = createEntityCriteria();
        return (List<Entitlement>) criteria.list();
    }
 
    
    public Entitlement findEntitlementByKey(String key) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("key", key));
        return (Entitlement) criteria.uniqueResult();
    }
    public Entitlement findEntitlementBySerialNumber(String serialNumber) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("serialNumber", serialNumber));
    	return (Entitlement) criteria.uniqueResult();
    }
    
    public List<Entitlement> findEntitlementNames(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.like("customerName", name+"%"));
       
        return (List<Entitlement>) criteria.list();
    }
    
    
    public List<Entitlement> findEntitlementByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("customerName", name));
        return (List<Entitlement>) criteria.list();
    }
    
    public Entitlement findEntitlement(String serialNumber, String customerID) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("serialNumber", serialNumber));
    	criteria.add(Restrictions.eq("customer.id", Integer.parseInt(customerID)));
    	return (Entitlement) criteria.uniqueResult();
    }
    
    public List<Entitlement> checkInfluxDBForCustomer(String dbName, int customerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("databaseName", dbName));
    	criteria.add(Restrictions.ne("customer.id", customerId));
        return (List<Entitlement>) criteria.list();
    }

	/*@Override
	public Entitlement findEntitlementByCustomerId(int customerId) {
		Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("id", customerId));
    	return (Entitlement) criteria.uniqueResult();
	}*/
   
}