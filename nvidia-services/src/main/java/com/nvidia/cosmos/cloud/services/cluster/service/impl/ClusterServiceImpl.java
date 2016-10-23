package com.nvidia.cosmos.cloud.services.cluster.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.ClusterExistsException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.dao.ClusterDao;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;

/**
 * @author pbatta
 *
 */
@Service("clusterService")
@Transactional
public class ClusterServiceImpl implements ClusterService {

	private static Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

	@Autowired
	MessageSource messageSource;
	/**
	 * 
	 */
	@Autowired
	private ClusterDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.cluster.service.ClusterService#findById(
	 * int)
	 */
	public Cluster findById(int id) throws ClusterNotFoundException {
		Cluster entity = dao.findById(id);
		if (entity == null) {
			throw new ClusterNotFoundException(messageSource.getMessage("cluster.notfound.id", new String[] { "" + id }, new Locale("en", "US")));
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.cluster.service.ClusterService#
	 * saveCluster(com.nvidia.cosmos.cloud.services.cluster.model.Cluster)
	 */
	public void saveCluster(Cluster cluster) throws ClusterExistsException {
		Cluster exists = dao.findClusterByClusterNameAndCustomerId(cluster.getName(), cluster.getCustomer().getId());
		if (exists != null && cluster.getSerialNumber().equals(exists.getSerialNumber()) == true) {
			throw new ClusterExistsException(
					messageSource.getMessage("cluster.exists.name", new String[] { "" + cluster.getName() }, new Locale("en", "US")));
		}
		dao.saveCluster(cluster);
		logger.info("Cluster is saved successfully..");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.cluster.service.ClusterService#
	 * updateCluster(com.nvidia.cosmos.cloud.services.cluster.model.Cluster)
	 */
	public void updateCluster(Cluster cluster) throws ClusterNotFoundException {
		Cluster entity = dao.findById(cluster.getId());
		if (entity == null) {
			throw new ClusterNotFoundException(
					messageSource.getMessage("cluster.notfound.id", new String[] { "" + cluster.getId() }, new Locale("en", "US")));
		} else {
			entity.setName(cluster.getName());
			entity.setIpAddress(cluster.getIpAddress());
			entity.setNfsDetails(cluster.getNfsDetails());
			entity.setCustomer(cluster.getCustomer());
			// entity.setSerialNumber(cluster.getSerialNumber());
			entity.setUpdatedDate(new Date());
			entity.setUpgradeOnBoot(cluster.getUpgradeOnBoot());

			dao.mergeCluster(entity);
			logger.info("Cluster information is updated..");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.cluster.service.ClusterService#
	 * deleteClusterById(int)
	 */
	public void deleteClusterById(int id) throws ClusterNotFoundException {
		Cluster entity = dao.findById(id);
		if (entity == null) {
			throw new ClusterNotFoundException(messageSource.getMessage("cluster.notfound.id", new String[] { "" + id }, new Locale("en", "US")));
		}
		dao.deleteClusterById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.cluster.service.ClusterService#
	 * findAllClusters()
	 */
	public List<Cluster> findAllClusters() {
		return dao.findAllClusters();
	}

	public Cluster findByClusterName(String name) throws ClusterNotFoundException {
		Cluster entity = dao.findClusterByName(name);
		// if(entity==null){
		// throw new
		// ClusterNotFoundException(messageSource.getMessage("cluster.notfound.name",
		// new String[]{""+name}, new Locale("en", "US")));
		// }
		return entity;
	}

	@Override
	public List<Cluster> findClusterByCustomerId(int customerId) throws ClusterNotFoundException {
		List<Cluster> entity = dao.findClusterByCustomerId(customerId);
		// if(entity==null || entity.isEmpty()){
		// throw new
		// ClusterNotFoundException(messageSource.getMessage("cluster.notfound.customer.id",
		// new String[]{""+customerId}, new Locale("en", "US")));
		// }
		return entity;
	}

	@Override
	public Cluster findClusterByClusterNameAndCustomerId(String clusterName, int customerId) throws ClusterNotFoundException {
		Cluster cluster = dao.findClusterByClusterNameAndCustomerId(clusterName, customerId);
		return cluster;
	}
	
	@Override
	public Cluster findClusterBYSerialNumber(String serialNumber, String customerId) throws ClusterNotFoundException{
		Cluster cluster = dao.findClusterBYSerialNumber(serialNumber, customerId);
		return cluster;
	}
	
	@Override
	public Cluster findCluster(String clusterId, String customerId) throws ClusterNotFoundException{
		Cluster cluster = dao.findCluster(clusterId, customerId);
		return cluster;
	}

	@Override
	public Cluster findNodeBySerialNumber(String serialNumber) throws ClusterNotFoundException {
		Cluster cluster = dao.findNodeBySerialNumber(serialNumber);
		return cluster;
	}
}