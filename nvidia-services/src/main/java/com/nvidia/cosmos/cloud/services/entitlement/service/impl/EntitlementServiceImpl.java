package com.nvidia.cosmos.cloud.services.entitlement.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.EntitlementExistsException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.SerialNumberExitsException;
import com.nvidia.cosmos.cloud.services.entitlement.dao.EntitlementDao;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
 
/**
 * @author pbatta
 *
 */
@Service("entitlementService")
@Transactional
public class EntitlementServiceImpl implements EntitlementService {
 
	private static Logger logger = LoggerFactory.getLogger(EntitlementServiceImpl.class);
	
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private EntitlementDao dao;
    
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService#findById(int)
     */
    public Entitlement findById(int id) throws EntitlementNotFoundException{
    	Entitlement entity = dao.findById(id);
    	if(entity==null){
    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService#saveEntitlement(com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement)
     */
    public void saveEntitlement(Entitlement entitlement) throws EntitlementExistsException, SerialNumberExitsException
    {
    	Entitlement exists = dao.findEntitlementByKey(entitlement.getKey());
    	if(exists!=null){
    		logger.info("Customer {} failed to create entitlement with serial number {} and key {}", entitlement.getCustomerName(), entitlement.getSerialNumber(), entitlement.getKey());
    		throw new EntitlementExistsException(messageSource.getMessage("entitlement.exists.key", new String[]{""+entitlement.getKey()}, new Locale("en", "US")));
    	}
    	exists=dao.findEntitlementBySerialNumber(entitlement.getSerialNumber());
    	
    	if(exists!=null){
    		logger.info("Customer {} failed to create entitlement with serial number {} and key {}", entitlement.getCustomerName(), entitlement.getSerialNumber(), entitlement.getKey());
    		throw new SerialNumberExitsException(messageSource.getMessage("entitlement.exists.serialNumber", new String[]{""+entitlement.getSerialNumber()}, new Locale("en", "US")) );
    	}
    	
    	dao.saveEntitlement(entitlement);
    	
    	logger.info("Customer {} successfully created entitlement with serial number {} and key {}", entitlement.getCustomerName(), entitlement.getSerialNumber(), entitlement.getKey());
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService#updateEntitlement(com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement)
     */
    public void updateEntitlement(Entitlement entitlement)  throws EntitlementNotFoundException{
    	Entitlement entity = dao.findById(entitlement.getId());
    	if(entity==null){
    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.id", new String[]{""+entitlement.getId()}, new Locale("en", "US")));
    	} else {
            entity.setCustomerName(entitlement.getCustomerName());
            entity.setKey(entitlement.getKey());
            entity.setSerialNumber(entitlement.getSerialNumber());
            entity.setUpdatedDate(new Date());
            
            entity.setCustomer(entitlement.getCustomer());
        }
        dao.saveEntitlement(entity);
        logger.info("Customer {} successfully updated entitlement with serial number {} and key {}", entitlement.getCustomerName(), entitlement.getSerialNumber(), entitlement.getKey());
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService#deleteEntitlementById(int)
     */
    public void deleteEntitlementById(int id) throws EntitlementNotFoundException{
    	Entitlement entity = dao.findById(id);
    	if(entity==null){
    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
    	dao.deleteEntitlementById(id);
    	logger.info("Customer {} successfully deleted entitlement with serial number {} and key {}", entity.getCustomerName(), entity.getSerialNumber(), entity.getKey());
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService#findAllEntitlements()
     */
    public List<Entitlement> findAllEntitlements() {
        return dao.findAllEntitlements();
    }
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService#findEntitlementByEmail(java.lang.String)
     */
    public Entitlement findEntitlementByKey(String key){
    	Entitlement entity = dao.findEntitlementByKey(key);
//    	if(entity==null){
//    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.email", new String[]{email}, new Locale("en", "US")));
//    	}
    	return entity;
    }
    public Entitlement findEntitlementBySerialNumber(String serialNumber) throws EntitlementNotFoundException{
    	Entitlement entity = dao.findEntitlementBySerialNumber(serialNumber);
    	if(entity==null){
    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.serialNumber", new String[]{""+serialNumber}, new Locale("en", "US")));
    	}
    	return entity;
    }
    
    
    public Entitlement findEntitlement(String serialNumber, String customerID) throws EntitlementNotFoundException{
    	Entitlement entity = dao.findEntitlement(serialNumber,customerID);
    	if(entity==null){
    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.serialNumber", new String[]{""+serialNumber}, new Locale("en", "US")));
    	}
    	return entity;
    }
    
    public List<Entitlement> findEntitlementNames(String name){
    	List<Entitlement> entity = dao.findEntitlementNames(name);
        return entity;
    }
    
    public List<Entitlement> findEntitlementByName(String name){
    	List<Entitlement> entity = dao.findEntitlementByName(name);
        return entity;
    }
    
    public List<Entitlement> checkInfluxDBForCustomer(String dbName, int customerId){
    	List<Entitlement> entity = dao.checkInfluxDBForCustomer(dbName,customerId);
        return entity;
    }

	/*@Override
	public Entitlement findEntitlementByCustomerId(int customerId)
			throws EntitlementNotFoundException {
		Entitlement entity  = dao.findEntitlementByCustomerId(customerId);
		if(entity==null){
    		throw new EntitlementNotFoundException(messageSource.getMessage("entitlement.notfound.customerId", new String[]{""+customerId}, new Locale("en", "US")));
    	}
		return entity;
	}*/
 
     
}