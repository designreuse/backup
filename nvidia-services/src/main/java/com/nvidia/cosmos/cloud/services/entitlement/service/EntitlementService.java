package com.nvidia.cosmos.cloud.services.entitlement.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.context.NoSuchMessageException;

import com.nvidia.cosmos.cloud.exceptions.EntitlementExistsException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.SerialNumberExitsException;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;

/**
 * @author pbatta
 *
 */
public interface EntitlementService {
	 
    /**
     * @param id
     * @return
     * @throws EntitlementNotFoundException
     */
    Entitlement findById(int id) throws EntitlementNotFoundException;
    /**
     * @param entitlement
     * @throws EntitlementExistsException
     * @throws SerialNumberExitsException 
     * @throws Exception 
     * @throws NoSuchMessageException 
     */
    void saveEntitlement(Entitlement entitlement) throws EntitlementExistsException, SerialNumberExitsException;
    /**
     * @param entitlement
     * @throws EntitlementNotFoundException
     */
    void updateEntitlement(Entitlement entitlement) throws EntitlementNotFoundException;
    /**
     * @param id
     * @throws EntitlementNotFoundException
     */
    void deleteEntitlementById(int id) throws EntitlementNotFoundException;
    /**
     * @return
     */
    List<Entitlement> findAllEntitlements(); 
    /**
     * @param email
     * @return
     * @throws EntitlementNotFoundException
     */
    public Entitlement findEntitlementByKey(String key) throws EntitlementNotFoundException;
    /**
     * @param serialNumber
     * @return
     * @throws EntitlementNotFoundException
     */
    public Entitlement findEntitlementBySerialNumber(String serialNumber) throws EntitlementNotFoundException;
    
    public Entitlement findEntitlement(String serialNumber,String customerID) throws EntitlementNotFoundException;
    
    /**
     * @param customerId
     * @return
     * @throws EntitlementNotFoundException
     */
   // public Entitlement findEntitlementByCustomerId(int customerId) throws EntitlementNotFoundException;
    
    public List<Entitlement> findEntitlementNames(String name) throws EntitlementNotFoundException;
    
    public List<Entitlement> findEntitlementByName(String name) throws EntitlementNotFoundException;
    
    public List<Entitlement> checkInfluxDBForCustomer(String dbName, int customerId) throws EntitlementNotFoundException;
     
}
