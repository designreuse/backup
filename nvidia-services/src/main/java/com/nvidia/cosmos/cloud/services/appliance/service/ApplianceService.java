package com.nvidia.cosmos.cloud.services.appliance.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.ApplianceExistsException;
import com.nvidia.cosmos.cloud.exceptions.ApplianceNotFoundException;
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;

/**
 * @author pbatta
 *
 */
public interface ApplianceService {
	 
    /**
     * @param id
     * @return
     * @throws ApplianceNotFoundException
     */
    Appliance findById(int id) throws ApplianceNotFoundException;
    /**
     * @param appliance
     * @throws ApplianceExistsException
     */
    void saveAppliance(Appliance appliance) throws ApplianceExistsException;
    /**
     * @param appliance
     * @throws ApplianceNotFoundException
     */
    void updateAppliance(Appliance appliance) throws ApplianceNotFoundException;
    /**
     * @param id
     * @throws ApplianceNotFoundException
     */
    void deleteApplianceById(int id) throws ApplianceNotFoundException;
    /**
     * @return
     */
    List<Appliance> findAllAppliances(); 
    /**
     * @param email
     * @return
     * @throws ApplianceNotFoundException
     */
    public Appliance findApplianceByServiceKey(String serviceKey) throws ApplianceNotFoundException;
    /**
     * @return
     */
    List<Appliance> findAllAppliances(int customerId) throws CustomerNotFoundException; 
}
