/**
 * 
 */
package com.nvidia.cosmos.cloud.services.cluster.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;

/**
 * @author pbatta
 *
 */
public interface ClusterDao {

	/**
	 * @param id
	 * @return
	 */
	Cluster findById(int id);

	/**
	 * @param clusters
	 */
	void saveCluster(Cluster cluster);

	/**
	 * 
	 * @param cluster
	 */
	void  mergeCluster(Cluster cluster);
	
	/**
	 * @param ssn
	 */
	void deleteClusterById(int id);

	/**
	 * @return
	 */
	List<Cluster> findAllClusters();

	/**
	 * @param name
	 * @return
	 */
	Cluster findClusterByName(String name);

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	List<Cluster> findClusterByCustomerId(int customerId);

	/**
	 * 
	 * @param clusterId
	 * @param customerId
	 * @return
	 */
	Cluster findClusterByClusterNameAndCustomerId(String clusterId, int customerId);
	
	Cluster findCluster(String clusterId, String customerId) throws ClusterNotFoundException;
	
	public Cluster findClusterBYSerialNumber(String serialNumber, String customerId) throws ClusterNotFoundException;
	/**
	 * 
	 * @param serialNumber
	 * @return
	 */
	Cluster findNodeBySerialNumber(String serialNumber);

}
