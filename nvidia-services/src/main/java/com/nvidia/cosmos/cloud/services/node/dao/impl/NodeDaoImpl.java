package com.nvidia.cosmos.cloud.services.node.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.services.node.dao.NodeDao;
import com.nvidia.cosmos.cloud.services.node.model.Node;

/**
 * @author pbatta
 *
 */
@Repository("nodeDao")
public class NodeDaoImpl extends AbstractDao<Integer, Node> implements NodeDao {

	/**
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(NodeDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.dao.NodeDao#findById(int)
	 */
	public Node findById(int id) {
		return getByKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.dao.NodeDao#saveNode(com.nvidia.cosmos.
	 * cloud.services.model.Node)
	 */
	public void saveNode(Node node) {
		persist(node);
	}

	public void mergeNode(Node node) {
		merge(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.dao.NodeDao#deleteNodeBySsn(java.lang.
	 * String)
	 */
	public void deleteNodeById(int id) {
		Node node = new Node();
		node.setId(id);
		delete(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.dao.NodeDao#findAllNodes()
	 */
	@SuppressWarnings("unchecked")
	public List<Node> findAllNodes() {
		Criteria criteria = createEntityCriteria();
		return (List<Node>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.dao.NodeDao#findNodeByName(java.
	 * lang.String)
	 */
	public Node findNodeByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Node) criteria.uniqueResult();
	}

	public List<Node> findAllNodes(int clusterId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("cluster.id", clusterId));
		return (List<Node>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.node.dao.NodeDao#
	 * findNodeByNameAndCluster(java.lang.String)
	 */
	public Node findNodeByNameAndCluster(String name, String cluster, Integer customerId) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("cluster", "cluster");
		criteria.createAlias("cluster.customer", "customer");
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("cluster.name", cluster));
		criteria.add(Restrictions.eq("customer.id", customerId));
		return (Node) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.dao.NodeDao#findNodeByCluster(java.
	 * lang.String)
	 */
	public List<Node> findNodeByCluster(String clusterName) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("cluster", "cluster");
		criteria.add(Restrictions.eq("cluster.name", clusterName));
		return (List<Node>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.dao.NodeDao#findNodeByIpAddress(
	 * java.lang.String)
	 */
	@Override
	public Node findNodeByIpAddress(String ipAddress) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ipAddress", ipAddress));
		return (Node) criteria.uniqueResult();
	}

	/*public void updateNodeStatusByTime(Integer minute){
		Long  totalTimeInSeconds=TimeUnit.MINUTES.toSeconds(minute);
		logger.debug("Updating node status if node update excceds more than {} secodns",totalTimeInSeconds);
    	Query query = getSession().createSQLQuery("update " +ServicesConstants.NODE_TABLE_NAME+ ",(select AVG(UNIX_TIMESTAMP(SYSDATE())-UNIX_TIMESTAMP(node.UPDATED_DATE)) "
    			+ " as avg_time_seconds from "+ServicesConstants.NODE_TABLE_NAME+" node) node1 set CLOUD_STATUS='disconnected' where  node1.avg_time_seconds > :time and id<>0");
        query.setLong("time", totalTimeInSeconds);
        query.executeUpdate();
        logger.debug("Succcessfully updated...");
    }
	*/
	
	@Override
	public Long getNodeCount() {
		Criteria criteria = createEntityCriteria();
		criteria.setProjection(Projections.count("id"));
		return (Long) criteria.uniqueResult();
	}

	@Override
	public Long getNodeConnectedCount() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("cloudStatus", "CONNECTED"));
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public Node findNodeByNodeNameAndClusterId(Node node) throws NodeNotFoundException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", node.getName()));
		criteria.createAlias("cluster", "cluster");
		criteria.createAlias("cluster.customer", "customer");
		criteria.add(Restrictions.eq("cluster.id", node.getCluster().getId()));
		criteria.add(Restrictions.eq("customer.id", node.getCluster().getCustomer().getId()));
		return (Node) criteria.uniqueResult();
	}

	@Override
	public Node findNodeBySerialNumber(String serialNumber) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("serialNumber", serialNumber));
		return (Node) criteria.uniqueResult();
	}

}