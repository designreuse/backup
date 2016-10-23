/**
 * 
 */
package com.nvidia.cosmos.cloud.services.entitlement.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;

/**
 * @author pbatta
 *
 */
public interface EntitlementDao {
 
    /**
     * @param id
     * @return
     */
    Entitlement findById(int id);
 
    /**
     * @param entitlements
     */
    void saveEntitlement(Entitlement entitlement);
     
    /**
     * @param ssn
     */
    void deleteEntitlementById(int id);
     
    /**
     * @return
     */
    List<Entitlement> findAllEntitlements();
 
    /**
     * @param name
     * @return
     */
    Entitlement findEntitlementByKey(String key);
    
    /**
     * @param serialNumber
     * @return
     */
    public Entitlement findEntitlementBySerialNumber(String serialNumber);
    
    
    public Entitlement findEntitlement(String serialNumber, String customerId);
    
    /**
     * @param customerId
     * @return
     */
   // public Entitlement findEntitlementByCustomerId(int customerId);
    
    public List<Entitlement> findEntitlementNames(String name);
    
    public List<Entitlement> findEntitlementByName(String name);
    
    public List<Entitlement> checkInfluxDBForCustomer(String dbName, int customerId);
    
}
