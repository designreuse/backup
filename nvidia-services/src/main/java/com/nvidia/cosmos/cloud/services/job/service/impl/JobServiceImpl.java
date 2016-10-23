package com.nvidia.cosmos.cloud.services.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.JobNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.job.dao.JobDao;
import com.nvidia.cosmos.cloud.services.job.model.Job;
import com.nvidia.cosmos.cloud.services.job.service.JobService;

@Service("jobService")
@Transactional
public class JobServiceImpl implements JobService {

	private static final String NO = "no";

	private static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

	@Autowired
	MessageSource messageSource;
	/**
	 * 
	 */
	@Autowired
	private JobDao jobDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#createJob(com.
	 * nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void createJob(Job job) {
		jobDao.createJob(job);
		logger.debug("Job is created successfully...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#deleteJob(com.
	 * nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void deleteJob(Job job) throws JobNotFoundException {
		Job entity = jobDao.findById(job);
		if (entity == null) {
			throw new JobNotFoundException(
					messageSource.getMessage("job.notfound.name", new String[] { "" + job.getName() }, new Locale("en", "US")));
		}
		jobDao.deleteJob(job);
		logger.debug("Job is deleted successfully...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#findJobByType(com
	 * .nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public Job findJobByType(Job job) throws JobNotFoundException {
		Job entity = jobDao.findJobByType(job);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#findJobByName(com
	 * .nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public Job findJobByName(Job job) throws JobNotFoundException {
		Job entity = jobDao.findJobByName(job);
		// if (entity == null) {
		// throw new JobNotFoundException(
		// messageSource.getMessage("job.notfound.name", new String[] { "" +
		// job.getName() }, new Locale("en", "US")));
		// }
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#mergeJob(com.
	 * nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void mergeJob(Job job) throws JobNotFoundException {
		Job entity = jobDao.findById(job);
		if (entity == null) {
			throw new JobNotFoundException(
					messageSource.getMessage("job.notfound.name", new String[] { "" + job.getName() }, new Locale("en", "US")));
		} else {
			if (job != null && entity.getIsDeleted() != null && entity.getIsDeleted().equals(NO)==true) {
				job.setUpdatedDate(new Date());
				jobDao.mergeJob(job);
				logger.debug("Job is updated successfully...");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#mergeJob(com.
	 * nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public void updateJob(Job job) throws JobNotFoundException {
		Job entity = jobDao.findById(job);
		if (entity == null) {
			throw new JobNotFoundException(
					messageSource.getMessage("job.notfound.name", new String[] { "" + job.getName() }, new Locale("en", "US")));
		} else {
			if (job != null) {
				entity.setIsDeleted(job.getIsDeleted());
				entity.setUpdatedDate(new Date());
				jobDao.mergeJob(entity);
				logger.debug("Job is updated successfully...");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#findAllJobs()
	 */
	@Override
	public List<Job> findAllJobs() {
		return jobDao.findAllJobs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.job.service.JobService#findAllJobsByNode
	 * (com.nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public List<Job> findAllJobsByNode(Job job) {
		return jobDao.findAllJobsByNode(job);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.job.service.JobService#findById
	 * (com.nvidia.cosmos.cloud.services.job.model.Job)
	 */
	@Override
	public Job findById(Job job) {
		Job entity = jobDao.findById(job);
		return entity;
	}
	@Override
	public  Job findJobByCustomer(String jobUniqueID, String customerID) throws JobNotFoundException{
		Job entity = jobDao.findJobByCustomer(jobUniqueID,customerID);
		return entity;
	}
	
	
	@Override
	public  Job findJobByUser(String jobUniqueID, String userID) throws JobNotFoundException{
		Job entity = jobDao.findJobByUser(jobUniqueID,userID);
		return entity;
	}
	
	
	@Override
	public  Job findJobUiqueID(String jobUniqueID) throws JobNotFoundException{
		Job entity = jobDao.findJobUiqueID(jobUniqueID);
		return entity;
	}
	
	@Override
	public List<Job> findJobByCustomerContainer(String container, String customerID) throws JobNotFoundException{
		List<Job> entity = jobDao.findJobByCustomerContainer(container,customerID);
		return entity;
	}
	
	@Override
	public List<Job> findJobByUserContainer(String container, String userID) throws JobNotFoundException{
		List<Job> entity = jobDao.findJobByUserContainer(container,userID);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.job.service.JobService#
	 * findAllJobsByCustomer(com.nvidia.cosmos.cloud.services.customer.model.
	 * Customer)
	 */
	@Override
	public List<Job> findAllJobsByCustomer(Job job, Customer customer) {
		return jobDao.findAllJobsByCustomer(job, customer);
	}
	
	@Override
	public List<Job> findCustomerJobs(String customerID){
		return jobDao.findCustomerJobs(customerID);
	}

	@Override
	public void removeJob(Job job) throws JobNotFoundException {
		Job entity = jobDao.findById(job);
		if (entity == null) {
			throw new JobNotFoundException(
					messageSource.getMessage("job.notfound.name", new String[] { "" + job.getName() }, new Locale("en", "US")));
		} else {
			entity.setUpdatedDate(new Date());
			entity.setIsDeleted(job.getIsDeleted());
			jobDao.mergeJob(entity);
			logger.debug("Job is updated successfully...");
		}
	}

	@Override
	public List<Job> findAllJobsByCluster(Job job, Cluster cluster) {
		return jobDao.findAllJobsByCluster(job, cluster);
	}

	@Override
	public List<Job> findAllJobsByServiceType(Job job) {
		return jobDao.findAllJobsByServiceType(job);
	}

}
