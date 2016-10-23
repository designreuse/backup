package com.nvidia.cosmos.cloud.services.node.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.NodeExistsException;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.node.model.Node;

/**
 * @author pbatta
 *
 */
public interface NodeService {

	/**
	 * @param id
	 * @return
	 * @throws NodeNotFoundException
	 */
	Node findById(int id) throws NodeNotFoundException;

	/**
	 * @param node
	 * @throws NodeExistsException
	 */
	void saveNode(Node node) throws NodeExistsException;

	/**
	 * @param node
	 * @throws NodeNotFoundException
	 */
	void updateNode(Node node) throws NodeNotFoundException;

	/**
	 * 
	 * @param node
	 * @throws NodeNotFoundException
	 */
	void updateNodeEulaAccepted(Node node) throws NodeNotFoundException;

	/**
	 * @param id
	 * @throws NodeNotFoundException
	 */
	void deleteNodeById(int id) throws NodeNotFoundException;

	/**
	 * @return
	 */
	List<Node> findAllNodes();

	/**
	 * @param name
	 * @return
	 * @throws NodeNotFoundException
	 */
	public Node findNodeByName(String name) throws NodeNotFoundException;

	/**
	 * 
	 * @param name
	 * @param cluster
	 * @param customerId
	 * @return
	 * @throws NodeNotFoundException
	 */
	Node findNodeByNameAndCluster(String name, String cluster, Integer customerId) throws NodeNotFoundException;

	/**
	 * 
	 * @param cluster
	 * @return
	 * @throws NodeNotFoundException
	 */
	List<Node> findAllNodesByCluster(int clusterId) throws NodeNotFoundException;

	List<Node> findAllNodesByCluster(Cluster cluster) throws NodeNotFoundException;

	/**
	 * 
	 * @param clusterName
	 * @return
	 * @throws NodeNotFoundException
	 */
	List<Node> findNodeByCluster(String clusterName) throws NodeNotFoundException;

	/**
	 * 
	 * @param ipAddress
	 * @return
	 * @throws NodeNotFoundException
	 */
	Node findNodeByIpAddress(String ipAddress) throws NodeNotFoundException;

	/**
	 * 
	 * @param minute
	 * @throws NodeNotFoundException
	 */
	/*void updateNodeStatusByTime(Integer minute) throws NodeNotFoundException;*/

	/**
	 * 
	 * @param node
	 * @return
	 * @throws NodeNotFoundException
	 */
	Node findNodeByNodeNameAndClusterId(Node node) throws NodeNotFoundException;

	/**
	 * 
	 * @param serialNumber
	 * @return
	 * @throws NodeNotFoundException
	 */
	public Node findNodeBySerialNumber(String serialNumber) throws NodeNotFoundException;
}
