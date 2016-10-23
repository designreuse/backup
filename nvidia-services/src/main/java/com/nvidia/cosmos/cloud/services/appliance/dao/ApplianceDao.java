/**
 * 
 */
package com.nvidia.cosmos.cloud.services.appliance.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;

/**
 * @author pbatta
 *
 */
public interface ApplianceDao {
 
    /**
     * @param id
     * @return
     */
    Appliance findById(int id);
 
    /**
     * @param appliance
     */
    void saveAppliance(Appliance appliance);
     
    /**
     * @param ssn
     */
    void deleteApplianceById(int id);
     
    /**
     * @return
     */
    List<Appliance> findAllAppliances();
 
    /**
     * @param name
     * @return
     */
    Appliance findApplianceByServiceKey(String serviceKey);
    /**
     * @param customerId
     * @return
     */
    List<Appliance> findAllAppliances(int customerId);
}
