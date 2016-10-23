package com.nvidia.cosmos.cloud.services.node.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.exceptions.NodeExistsException;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.node.dao.NodeDao;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.node.service.NodeService;

/**
 * @author pbatta
 *
 */
@Service("nodeService")
@Transactional
public class NodeServiceImpl implements NodeService {

	private static Logger logger = LoggerFactory.getLogger(NodeServiceImpl.class);
	@Autowired
	MessageSource messageSource;
	/**
	 * 
	 */
	@Autowired
	private NodeDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.service.NodeService#findById(int)
	 */
	public Node findById(int id) throws NodeNotFoundException {
		Node entity = dao.findById(id);
		if (entity == null) {
			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.id", new String[] { "" + id }, new Locale("en", "US")));
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.service.NodeService#saveNode(com.
	 * nvidia.cosmos.cloud.services.node.model.Node)
	 */
	public void saveNode(Node node) throws NodeExistsException {
		Node exists = dao.findNodeBySerialNumber(node.getSerialNumber());
		if (exists!=null) {
			throw new NodeExistsException(
					messageSource.getMessage("node.exists.name", new String[] { "" + node.getName() }, new Locale("en", "US")));
		}
		dao.saveNode(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.service.NodeService#updateNode(com.
	 * nvidia.cosmos.cloud.services.node.model.Node)
	 */
	public void updateNode(Node node) throws NodeNotFoundException {
		Node entity = dao.findById(node.getId());
		if (entity == null) {
			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.id", new String[] { "" + node.getId() }, new Locale("en", "US")));
		} else {
			entity.setName(node.getName());
			//entity.setSerialNumber(node.getSerialNumber());
			entity.setNodeKey(node.getNodeKey());
			entity.setCloudManaged(node.getCloudManaged());
			entity.setIsleader(node.getIsleader());
			entity.setFwVersion(node.getFwVersion());
			entity.setSerialId(node.getSerialId());
			entity.setSubNet(node.getSubNet());
			entity.setFirstBoot(node.getFirstBoot());
			entity.setTimeOfReboot(node.getTimeOfReboot());
			//entity.setEulaAccepted(node.getEulaAccepted());
			entity.setBiosVersion(node.getBiosVersion());
			entity.setCloudStatus(node.getCloudStatus());
			entity.setTags(node.getTags());
			entity.setGpuConfiguration(node.getGpuConfiguration());
			entity.setIpmi(node.getIpmi());
			entity.setNodeId(node.getNodeId());
			entity.setMode(node.getMode());
			entity.setIpAddress(node.getIpAddress());
			entity.setSwVersion(node.getSwVersion());
			entity.setCloudGroup(node.getCloudGroup());
			entity.setClusterGroup(node.getClusterGroup());
			entity.setModelName(node.getModelName());
			entity.setMemory(node.getMemory());
			entity.setDiskSpace(node.getDiskSpace());
			entity.setGateway(node.getGateway());
			entity.setHealthy(node.getHealthy());
			entity.setCluster(node.getCluster());
			entity.setUpdatedDate(new Date());
			dao.mergeNode(entity);
			logger.info("Node information is updated..");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.service.NodeService#deleteNodeById(
	 * int)
	 */
	public void deleteNodeById(int id) throws NodeNotFoundException {
		Node entity = dao.findById(id);
		if (entity == null) {
			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.id", new String[] { "" + id }, new Locale("en", "US")));
		}
		dao.deleteNodeById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.service.NodeService#findAllNodes()
	 */
	public List<Node> findAllNodes() {
		return dao.findAllNodes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nvidia.cosmos.cloud.services.node.service.NodeService#findNodeByEmail
	 * (java.lang.String)
	 */
	public Node findNodeByName(String name) throws NodeNotFoundException {
		Node entity = dao.findNodeByName(name);
//		if (entity == null) {
//			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.name", new String[] { name }, new Locale("en", "US")));
//		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.node.service.NodeService#
	 * findNodeByNameAndCluster (java.lang.String)
	 */
	@Override
	public Node findNodeByNameAndCluster(String name, String cluster,Integer customerId) throws NodeNotFoundException {
		Node entity = dao.findNodeByNameAndCluster(name, cluster,customerId);
		if (entity == null) {
			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.name", new String[] { ""+name, cluster }, new Locale("en", "US")));
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nvidia.cosmos.cloud.services.node.service.NodeService#
	 * findAllNodesByClusterName (java.lang.Integer)
	 */
	@Override
	public List<Node> findAllNodesByCluster(int clusterId) throws NodeNotFoundException {
		List<Node> entitys = dao.findAllNodes(clusterId);
//		if (entitys == null) {
//			throw new NodeNotFoundException(
//					messageSource.getMessage("node.notfound.name", new String[] { ""+String.valueOf(clusterId) }, new Locale("en", "US")));
//		}
		return entitys;
	}
	
	
	
	@Override
	public List<Node> findAllNodesByCluster(Cluster cluster) throws NodeNotFoundException {
		List<Node> entitys = dao.findAllNodes(cluster.getId());
//		if (entitys == null) {
//			throw new NodeNotFoundException(
//					messageSource.getMessage("node.notfound.name", new String[] { ""+String.valueOf(clusterId) }, new Locale("en", "US")));
//		}
		return entitys;
	}

	@Override
	public List<Node> findNodeByCluster(String clusterName) throws NodeNotFoundException {
		List<Node> entitys = dao.findNodeByCluster(clusterName);
		if (entitys == null) {
			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.cluster.name", new String[] { ""+ clusterName }, new Locale("en", "US")));
		}
		return entitys;
	}

	@Override
	public Node findNodeByIpAddress(String ipAddress) throws NodeNotFoundException {
		Node entity=dao.findNodeByIpAddress(ipAddress);
		if (entity == null) {
			throw new NodeNotFoundException(
					messageSource.getMessage("node.notfound.ipaddress", new String[] { ""+String.valueOf(ipAddress) }, new Locale("en", "US")));
		}
		return entity;
	}

	
	/*@Override
	public void updateNodeStatusByTime(Integer minute) throws NodeNotFoundException {
		dao.updateNodeStatusByTime(minute);
	}
	*/
	
     /**
      * 
      */
	@Override
	public Node findNodeByNodeNameAndClusterId(Node node) throws NodeNotFoundException {
		Node entity=dao.findNodeByNodeNameAndClusterId(node);
		return entity;
	}

	@Override
	public Node findNodeBySerialNumber(String serialNumber) throws NodeNotFoundException {
		Node entity=dao.findNodeBySerialNumber(serialNumber);
		return entity;
	}

	@Override
	public void updateNodeEulaAccepted(Node node) throws NodeNotFoundException {
		Node entity = dao.findById(node.getId());
		if (entity == null) {
			throw new NodeNotFoundException(messageSource.getMessage("node.notfound.id", new String[] { "" + node.getId() }, new Locale("en", "US")));
		} else {
			entity.setEulaAccepted(node.getEulaAccepted());
			dao.mergeNode(entity);
			logger.info("Node Eual information is updated..");
		}
	}

}