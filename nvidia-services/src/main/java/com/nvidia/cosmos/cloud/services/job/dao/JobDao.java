package com.nvidia.cosmos.cloud.services.job.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.JobNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.job.model.Job;

public interface JobDao {

	/**
	 * 
	 * @param job
	 */
	void createJob(Job job);

	/**
	 * 
	 * @param job
	 */
	void deleteJob(Job job);

	/**
	 * 
	 * @param job
	 * @return
	 */
	Job findJobByType(Job job);

	/**
	 * 
	 * @param job
	 * @return
	 */
	Job findJobByName(Job job);

	/**
	 * 
	 * @param job
	 */
	void mergeJob(Job job);

	/**
	 * 
	 * @return
	 */
	List<Job> findAllJobs();

	/**
	 * 
	 * @param node
	 * @return
	 */

	List<Job> findAllJobsByNode(Job job);

	/**
	 * 
	 * @param job
	 * @return
	 */
	Job findById(Job job);
	
	
    Job findJobByCustomer(String jobUniqueID, String customerID) throws JobNotFoundException;
   
    Job findJobByUser(String jobUniqueID, String userID) throws JobNotFoundException;
    
    Job findJobUiqueID(String jobUniqueID) throws JobNotFoundException;
    
     List<Job> findJobByCustomerContainer(String container, String customerID) throws JobNotFoundException;
      List<Job> findJobByUserContainer(String container, String userID) throws JobNotFoundException;
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	List<Job> findAllJobsByCustomer(Job job,Customer customer);
	
	
	 List<Job> findCustomerJobs(String customerID);
	
	
	/**
	 * 
	 * @param job
	 * @param cluster
	 * @return
	 */
	List<Job> findAllJobsByCluster(Job job,Cluster cluster);
	
	/**
	 * 
	 * @param job
	 * @return
	 */
	List<Job> findAllJobsByServiceType(Job job);

}
