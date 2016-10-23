package com.nvidia.cosmos.cloud.services.cluster.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.dao.ClusterDao;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;

/**
 * @author pbatta
 *
 */
@Repository("clusterDao")
public class ClusterDaoImpl extends AbstractDao<Integer, Cluster> implements ClusterDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.dao.ClusterDao#findById(int)
	 */
	public Cluster findById(int id) {
		return getByKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.dao.ClusterDao#saveCluster(com.nvidia.
	 * cosmos.cloud.services.model.Cluster)
	 */
	public void saveCluster(Cluster cluster) {
		persist(cluster);
	}

	public void mergeCluster(Cluster cluster) {
		merge(cluster);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.dao.ClusterDao#deleteClusterBySsn(java.
	 * lang.String)
	 */
	public void deleteClusterById(int id) {
		/*
		 * Query query = getSession().createSQLQuery("delete from "
		 * +ServicesConstants.CLUSTER_TABLE_NAME+" where id = :id");
		 * query.setInteger("id", id); query.executeUpdate();
		 */
		Cluster cluster = new Cluster();
		cluster.setId(id);
		delete(cluster);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.dao.ClusterDao#findAllClusters()
	 */
	@SuppressWarnings("unchecked")
	public List<Cluster> findAllClusters() {
		Criteria criteria = createEntityCriteria();
		return (List<Cluster>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.cluster.dao.ClusterDao#findClusterByName
	 * (java.lang.String)
	 */
	public Cluster findClusterByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Cluster) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.cluster.dao.ClusterDao#
	 * findClusterByCustomerId(java.lang.Integer)
	 */
	@Override
	public List<Cluster> findClusterByCustomerId(int customerId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("customer.id", customerId));
		return (List<Cluster>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.cluster.dao.ClusterDao#
	 * findClusterByIdAndCustomerId(String clusterId,java.lang.Integer)
	 */
	@Override
	public Cluster findClusterByClusterNameAndCustomerId(String clusterName, int customerId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", clusterName));
		criteria.add(Restrictions.eq("customer.id", customerId));
		return (Cluster) criteria.uniqueResult();
	}
	
	@Override
	public Cluster findCluster(String clusterId, String customerId) throws ClusterNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", Integer.parseInt(clusterId)));
		criteria.add(Restrictions.eq("customer.id", Integer.parseInt(customerId)));
		return (Cluster) criteria.uniqueResult();
	}
	
	
	@Override
	public Cluster findClusterBYSerialNumber(String serialNumber, String customerId) throws ClusterNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("serialNumber", serialNumber));
		criteria.add(Restrictions.eq("customer.id", Integer.parseInt(customerId)));
		return (Cluster) criteria.uniqueResult();
	}

	@Override
	public Cluster findNodeBySerialNumber(String serialNumber) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("serialNumber", serialNumber));
		return (Cluster) criteria.uniqueResult();
	}

}