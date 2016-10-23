package com.nvidia.cosmos.cloud.services.appliance.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.services.appliance.dao.ApplianceDao;
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;

/**
 * @author pbatta
 *
 */
@Repository("applianceDao")
public class ApplianceDaoImpl extends AbstractDao<Integer, Appliance> implements ApplianceDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.ApplianceDao#findById(int)
     */
    public Appliance findById(int id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.ApplianceDao#saveAppliance(com.nvidia.cosmos.cloud.services.model.Appliance)
     */
    public void saveAppliance(Appliance appliance) {
        persist(appliance);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.ApplianceDao#deleteApplianceBySsn(java.lang.String)
     */
    public void deleteApplianceById(int id) {
    	Appliance appliance = new Appliance();
    	appliance.setId(id);
    	if (appliance != null) {
    	    delete(appliance);
    	}
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.ApplianceDao#findAllAppliances()
     */
    @SuppressWarnings("unchecked")
    public List<Appliance> findAllAppliances() {
        Criteria criteria = createEntityCriteria();
        return (List<Appliance>) criteria.list();
    }
 
    
    public Appliance findApplianceByServiceKey(String serviceKey) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("serviceKey", serviceKey));
        return (Appliance) criteria.uniqueResult();
    }
    
   
    public List<Appliance> findAllAppliances(int customerId) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("customer.id", customerId));
    	return (List<Appliance>) criteria.list();
    }
}