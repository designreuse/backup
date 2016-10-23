/**
 * 
 */
package com.nvidia.cosmos.cloud.services.node.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.services.node.model.Node;

/**
 * @author pbatta
 *
 */
public interface NodeDao {

	/**
	 * @param id
	 * @return
	 */
	Node findById(int id);

	/**
	 * @param node
	 */
	void saveNode(Node node);

	/**
	 * 
	 * @param node
	 */
	void mergeNode(Node node);

	/**
	 * @param ssn
	 */
	void deleteNodeById(int id);

	/**
	 * @return
	 */
	List<Node> findAllNodes();

	/**
	 * @param name
	 * @return
	 */
	Node findNodeByName(String name);

	/**
	 * @param clusterId
	 * @return
	 */
	List<Node> findAllNodes(int clusterId);

	/**
	 * 
	 * @param name
	 * @param cluster
	 * @param customerId
	 * @return
	 */
	Node findNodeByNameAndCluster(String name, String cluster, Integer customerId);

	/**
	 * 
	 * @param clusterName
	 * @return
	 */
	List<Node> findNodeByCluster(String clusterName);

	/**
	 * 
	 * @param ipAddress
	 * @return
	 */
	Node findNodeByIpAddress(String ipAddress);

	/**
	 * 
	 * @param minute
	 */
	/*void updateNodeStatusByTime(Integer minute);
*/
	public Long getNodeCount();

	public Long getNodeConnectedCount();

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
	 */
	Node findNodeBySerialNumber(String serialNumber);
}
