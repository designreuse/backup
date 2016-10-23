package com.nvidia.cosmos.cloud.services.cluster.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.ClusterExistsException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;

/**
 * @author pbatta
 *
 */
public interface ClusterService {
	 
    /**
     * @param id
     * @return
     * @throws ClusterNotFoundException
     */
    Cluster findById(int id) throws ClusterNotFoundException;
    /**
     * @param cluster
     * @throws ClusterExistsException
     */
    void saveCluster(Cluster cluster) throws ClusterExistsException;
    /**
     * @param cluster
     * @throws ClusterNotFoundException
     */
    void updateCluster(Cluster cluster) throws ClusterNotFoundException;
    /**
     * @param id
     * @throws ClusterNotFoundException
     */
    void deleteClusterById(int id) throws ClusterNotFoundException;
    /**
     * @return
     */
    List<Cluster> findAllClusters(); 
    /**
     * @param id
     * @return
     * @throws ClusterNotFoundException
     */
    Cluster findByClusterName(String name) throws ClusterNotFoundException;
    
    /**
     * 
     * @param customerId
     * @return
     * @throws ClusterNotFoundException
     */
    List<Cluster> findClusterByCustomerId(int customerId) throws ClusterNotFoundException;
    
    /**
     * 
     * @param clusterId
     * @param customerId
     * @return
     * @throws ClusterNotFoundException
     */
    Cluster findClusterByClusterNameAndCustomerId(String clusterId, int customerId) throws ClusterNotFoundException;
    
    
    Cluster findCluster(String clusterId, String customerId) throws ClusterNotFoundException;
    
    
    Cluster findClusterBYSerialNumber(String serialNumber, String customerId) throws ClusterNotFoundException;
    
    
    /**
     * 
     * @param serialNumber
     * @return
     */
    Cluster findNodeBySerialNumber(String serialNumber) throws ClusterNotFoundException;
     
}
