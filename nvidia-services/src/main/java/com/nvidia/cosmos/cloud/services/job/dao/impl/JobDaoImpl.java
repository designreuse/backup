package com.nvidia.cosmos.cloud.services.job.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.exceptions.JobNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.job.dao.JobDao;
import com.nvidia.cosmos.cloud.services.job.model.Job;

@Repository("jobDao")
public class JobDaoImpl extends AbstractDao<Integer, Job> implements JobDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.dao.JobDao#createJob(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void createJob(Job job) {
		persist(job);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.dao.JobDao#deleteJob(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void deleteJob(Job job) {
		delete(job);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.dao.JobDao#findJobByType(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public Job findJobByType(Job job) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("serviceType", job.getServiceType()));
		return (Job) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.dao.JobDao#findJobByName(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public Job findJobByName(Job job) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("node", "node");
		criteria.add(Restrictions.eq("name", job.getName()));
		criteria.add(Restrictions.eq("isDeleted", job.getIsDeleted()));
		criteria.add(Restrictions.eq("node.id", job.getNode().getId()));
		return (Job) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.job.dao.JobDao#mergeJob(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void mergeJob(Job job) {
		merge(job);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.job.dao.JobDao#findAllJobs()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAllJobs() {
		Criteria criteria = createEntityCriteria();
		return (List<Job>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.dao.JobDao#findAllJobsByNode(com.
	 * nvidia.cosmos.cloud.services.node.model.Node)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAllJobsByNode(Job job) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("node", "node");
		criteria.add(Restrictions.eq("isDeleted", job.getIsDeleted()));
		criteria.add(Restrictions.eq("node.id", job.getNode().getId()));
		return (List<Job>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.job.dao.JobDao#findById(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public Job findById(Job job) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", job.getId()));
		return (Job) criteria.uniqueResult();
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.job.dao.JobDao#findById(com.nvidia.
	 * cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public  Job findJobByCustomer(String jobUniqueID, String customerID) throws JobNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("uniqueId", jobUniqueID));
		criteria.add(Restrictions.eq("customer.id", Integer.parseInt(customerID)));
		return (Job) criteria.uniqueResult();
	}
	
	
	@Override
	public  Job findJobByUser(String jobUniqueID, String userID) throws JobNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("uniqueId", jobUniqueID));
		criteria.add(Restrictions.eq("user.id", Long.parseLong(userID)));
		return (Job) criteria.uniqueResult();
	}
	
	@Override
	public Job findJobUiqueID(String jobUniqueID) throws JobNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("uniqueId", jobUniqueID));
		
		return (Job) criteria.uniqueResult();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAllJobsByCustomer(Job job, Customer customer) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("node", "node");
		criteria.createAlias("node.cluster", "cluster");
		criteria.createAlias("cluster.customer", "customer");
		// criteria.add(Restrictions.eq("cluster.id", "node.cluster"));
		criteria.add(Restrictions.eq("isDeleted", job.getIsDeleted()));
		criteria.add(Restrictions.eq("customer.id", customer.getId()));
		return (List<Job>) criteria.list();
	}
	
	@Override
	public List<Job> findJobByCustomerContainer(String container, String customerID) throws JobNotFoundException{
		 Criteria criteria = createEntityCriteria();
		 criteria.add(Restrictions.eq("customer.id", Integer.parseInt(customerID)));
		 criteria.add(Restrictions.eq("dockerContainerName", container));
		 return (List<Job>) criteria.list();
	 }
	
	
	@Override
	public List<Job> findJobByUserContainer(String container, String userID) throws JobNotFoundException{
		 Criteria criteria = createEntityCriteria();
		 criteria.add(Restrictions.eq("user.id", Long.parseLong(userID)));
		 criteria.add(Restrictions.eq("dockerContainerName", container));
		 return (List<Job>) criteria.list();
	 }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findCustomerJobs(String customerID){
		Criteria criteria = createEntityCriteria();
		
		criteria.add(Restrictions.eq("customer.id", Integer.parseInt(customerID)));
		return (List<Job>) criteria.list();
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAllJobsByCluster(Job job, Cluster cluster) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("node", "node");
		criteria.createAlias("node.cluster", "cluster");
		criteria.createAlias("cluster.customer", "customer");
		criteria.add(Restrictions.eq("cluster.id", cluster.getId()));
		criteria.add(Restrictions.eq("isDeleted", job.getIsDeleted()));
		criteria.add(Restrictions.eq("customer.id", cluster.getCustomer().getId()));
		return (List<Job>) criteria.list();
	}

	@Override
	public List<Job> findAllJobsByServiceType(Job job) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("node", "node");
		criteria.add(Restrictions.eq("isDeleted", job.getIsDeleted()));
		criteria.add(Restrictions.eq("serviceType", job.getServiceType()));
		criteria.add(Restrictions.eq("node.id", job.getNode().getId()));
		return (List<Job>) criteria.list();
	}
}
