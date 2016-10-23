package com.nvidia.cosmos.cloud.services.appliance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;

import com.nvidia.cosmos.cloud.exceptions.ApplianceExistsException;
import com.nvidia.cosmos.cloud.exceptions.ApplianceNotFoundException;
import com.nvidia.cosmos.cloud.services.customer.dao.CustomerDao;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.appliance.dao.ApplianceDao;
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;
import com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService;
 
/**
 * @author pbatta
 *
 */
@Service("applianceService")
@Transactional
public class ApplianceServiceImpl implements ApplianceService {
	
	private static Logger logger = LoggerFactory.getLogger(ApplianceServiceImpl.class);

 
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private ApplianceDao dao;
    /**
     * 
     */
    @Autowired
    private CustomerDao customerdao;
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService#findById(int)
     */
    public Appliance findById(int id) throws ApplianceNotFoundException{
    	Appliance entity = dao.findById(id);
    	if(entity==null){
    		throw new ApplianceNotFoundException(messageSource.getMessage("appliance.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService#saveAppliance(com.nvidia.cosmos.cloud.services.appliance.model.Appliance)
     */
    public void saveAppliance(Appliance appliance) throws ApplianceExistsException{
    	Appliance exists = dao.findApplianceByServiceKey(appliance.getServiceKey());
    	if(exists!=null){
    		throw new ApplianceExistsException(messageSource.getMessage("appliance.exists.servicekey", new String[]{""+appliance.getServiceKey()}, new Locale("en", "US")));
    	}
    	dao.saveAppliance(appliance);
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService#updateAppliance(com.nvidia.cosmos.cloud.services.appliance.model.Appliance)
     */
    public void updateAppliance(Appliance appliance)  throws ApplianceNotFoundException{
    	Appliance entity = dao.findById(appliance.getId());
    	if(entity==null){
    		throw new ApplianceNotFoundException(messageSource.getMessage("appliance.notfound.id", new String[]{""+appliance.getId()}, new Locale("en", "US")));
    	} else {
            entity.setName(appliance.getName());
            entity.setServiceKey(appliance.getServiceKey());
            entity.setUpdatedDate(new Date());
        }
        dao.saveAppliance(entity);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService#deleteApplianceById(int)
     */
    public void deleteApplianceById(int id) throws ApplianceNotFoundException{
    	Appliance entity = dao.findById(id);
    	if(entity==null){
    		throw new ApplianceNotFoundException(messageSource.getMessage("appliance.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        dao.deleteApplianceById(id);
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService#findAllAppliances()
     */
    public List<Appliance> findAllAppliances() {
        return dao.findAllAppliances();
    }
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService#findApplianceByEmail(java.lang.String)
     */
    public Appliance findApplianceByServiceKey(String serviceKey) throws ApplianceNotFoundException{
    	Appliance entity = dao.findApplianceByServiceKey(serviceKey);
    	if(entity==null){
    		throw new ApplianceNotFoundException(messageSource.getMessage("appliance.notfound.servicekey", new String[]{serviceKey}, new Locale("en", "US")));
    	}
    	return entity;
    }
    public List<Appliance> findAllAppliances(int customerId) throws CustomerNotFoundException{
    	Customer entity = customerdao.findById(customerId);
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+customerId}, new Locale("en", "US")));
    	}
    	return dao.findAllAppliances(customerId);
    }
     
}