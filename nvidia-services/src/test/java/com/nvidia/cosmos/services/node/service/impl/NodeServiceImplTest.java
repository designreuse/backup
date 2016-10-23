/**
 * 
 */
package com.nvidia.cosmos.services.node.service.impl;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.NodeExistsException;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.node.service.NodeService;

/**
 * @author bprasad
 * 
 */
@Test
public class NodeServiceImplTest extends Assert {
	//private Logger logger = LoggerFactory.getLogger(NodeServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	final static String ROLE1 = USERNAME + "_role1";
	
	final static String CUSTOMER1 = USERNAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO ="8078877899";
	
	final static String CLUSTER1 = USERNAME + "_cluster1";
	final static String CLUSTER1IPADDRESS =CLUSTER1+"@mail.com";
	
	final static String NODE1 = USERNAME + "_node1";
	
	final static String NODE2 = USERNAME + "_node2";
	
	
	NodeService nodeService = null;
	ClusterService clusterService = null;
	ServicesFactory factory = null;
	CustomerService customerService = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		nodeService = factory.getNodeService();
		factory = ServicesFactory.getInstance();
		clusterService = factory.getClusterService();
		customerService = factory.getCustomerService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(NODE1);
		clean(NODE2);
		cleanCluster(CLUSTER1);
		cleanCustomer(CUSTOMER1EMAIL);
	}
	private void cleanCluster(String clusterName) throws Exception {
		Cluster cluster = null;

		try {
			cluster = clusterService.findByClusterName(clusterName);
			if(cluster!=null){
				clusterService.deleteClusterById(cluster.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void clean(String nodeEmail) throws Exception {
		Node node = null;
		try {
			node = nodeService.findNodeByName(nodeEmail);
			if(node!=null){
				nodeService.deleteNodeById(node.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void cleanCustomer(String customerEmail) throws Exception {
		Customer customer = null;
		try {
			customer = customerService.findCustomerByEmail(customerEmail);
			if(customer!=null){
				customerService.deleteCustomerById(customer.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*@Test(priority=1)
	public void testSaveNode1() throws Exception { // create a new node
		//cleanUp();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			customerService.saveCustomer(customer);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Cluster cluster = new Cluster(CLUSTER1, CLUSTER1IPADDRESS,"12.4.5.6.8","SerialNumber1", customer1);
			clusterService.saveCluster(cluster);
			
			//logger.info("Cluster is created in test " + CLUSTER1);
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			Node node =new Node(NODE1, "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254", 
					"ec2-52-38-12-79.us-west-2.compute.amazonaws.com", 
					"factory_default", "yes", "54321", "customer1-cluster1-node1", NODE1,
					"22.33.44.000", "no", "no", "12345", "disconnected", "tiger,lion", "1", "XXXXXX", NODE1,
					"dhcp", "172.17.0.1", "2.0.0", "na", "master", "db2", "50GB", "100GB", "1.1.1.1", "compute-dev.nvidia.com", "yes", "56",
					"http://dgx1-bootstrap-repo-devel1.nvidia.com/", cluster1,"upgradeOnBoot","1");
			
			nodeService.saveNode(node);
		
			
            //logger.info("Node is created in test " + NODE1);
			Node node1 = nodeService.findNodeByName(NODE1);
			
			//logger.info("Node information is fetched again and name is "+node1.getName());
			assertNotNull(node1.getId());
			assertEquals(node1.getName(), NODE1);
			assertEquals(node1.getSerialNumber(), "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254");
			assertEquals(node1.getNodeKey(), "ec2-52-38-12-79.us-west-2.compute.amazonaws.com");
			assertEquals(node1.getCloudManaged(), "factory_default");
			assertEquals(node1.getIsleader(),"yes");
			assertEquals(node1.getFwVersion(),"54321");
			assertEquals(node1.getSerialId(),NODE1);
			assertEquals(node1.getSubNet(), "22.33.44.000");
			assertEquals(node1.getFirstBoot(),"no");
			assertEquals(node1.getEulaAccepted(),"no");
			assertEquals(node1.getBiosVersion(),"12345");
			assertEquals(node1.getCloudStatus(),"disconnected");
			assertEquals(node1.getTags(),"tiger,lion");
			assertEquals(node1.getGpuConfiguration(),"1");
			assertEquals(node1.getIpmi(),"XXXXXX");
			assertEquals(node1.getNodeId(),NODE1);
			assertEquals(node1.getMode(),"dhcp");
			assertEquals(node1.getIpAddress(),"172.17.0.1");
			assertEquals(node1.getSwVersion(),"2.0.0");
			assertEquals(node1.getCloudGroup(),"na");
			assertEquals(node1.getClusterGroup(),"master");
			assertEquals(node1.getModelName(),"db2");
			assertEquals(node1.getMemory(),"50GB");
			assertEquals(node1.getDiskSpace(),"100GB");
			assertEquals(node1.getGateway(),"1.1.1.1");
			assertEquals(node1.getCloudUrl(), "compute-dev.nvidia.com");
			assertEquals(node1.getUserAdded(), "yes");
			assertEquals(node1.getTotalCpuCores(), "56");
			assertEquals(node1.getRepoUrl(), "http://dgx1-bootstrap-repo-devel1.nvidia.com/");
			
			assert true;
			
			Node node2 =new Node(NODE2, "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254", 
					"ec2-52-38-12-79.us-west-2.compute.amazonaws.com", 
					"factory_default", "yes", "54321", "customer1-cluster1-node1", NODE2,
					"22.33.44.000", "no", "no", "12345", "connected", "tiger,lion", "1", "XXXXXX", NODE2,
					"dhcp", "172.17.0.2", "2.0.0", "na", "master", "db2", "50GB", "100GB", "1.1.1.1", "compute-dev.nvidia.com", "yes", "56",
					"http://dgx1-bootstrap-repo-devel1.nvidia.com/", cluster1,"upgradeOnBoot","1");
			
			nodeService.saveNode(node2);
		
			
			//logger.info("Node is created in test " + NODE2);
			Node node12 = nodeService.findNodeByName(NODE2);
			
			//logger.info("Node information is fetched again and name is "+node12.getName());
			assertNotNull(node12.getId());
			assertEquals(node12.getName(), NODE2);
			assertEquals(node12.getSerialNumber(), "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254");
			assertEquals(node12.getNodeKey(), "ec2-52-38-12-79.us-west-2.compute.amazonaws.com");
			assertEquals(node12.getCloudManaged(), "factory_default");
			assertEquals(node12.getIsleader(),"yes");
			assertEquals(node12.getFwVersion(),"54321");
			assertEquals(node12.getSerialId(),NODE2);
			assertEquals(node12.getSubNet(), "22.33.44.000");
			assertEquals(node12.getFirstBoot(),"no");
			assertEquals(node12.getEulaAccepted(),"no");
			assertEquals(node12.getBiosVersion(),"12345");
			assertEquals(node12.getCloudStatus(),"connected");
			assertEquals(node12.getTags(),"tiger,lion");
			assertEquals(node12.getGpuConfiguration(),"1");
			assertEquals(node12.getIpmi(),"XXXXXX");
			assertEquals(node12.getNodeId(),NODE2);
			assertEquals(node12.getMode(),"dhcp");
			assertEquals(node12.getIpAddress(),"172.17.0.2");
			assertEquals(node12.getSwVersion(),"2.0.0");
			assertEquals(node12.getCloudGroup(),"na");
			assertEquals(node12.getClusterGroup(),"master");
			assertEquals(node12.getModelName(),"db2");
			assertEquals(node12.getMemory(),"50GB");
			assertEquals(node12.getDiskSpace(),"100GB");
			assertEquals(node12.getGateway(),"1.1.1.1");
			assertEquals(node12.getCloudUrl(), "compute-dev.nvidia.com");
			assertEquals(node12.getUserAdded(), "yes");
			assertEquals(node12.getTotalCpuCores(), "56");
			assertEquals(node12.getRepoUrl(), "http://dgx1-bootstrap-repo-devel1.nvidia.com/");
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = NodeExistsException.class,dependsOnMethods = {"testSaveNode1"}) // create a existing node -- working well
	public void testSaveNode2() throws Exception {
		//cleanUp();
		    Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
		    Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
		    Node node =new Node(NODE1, "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254", 
					"ec2-52-38-12-79.us-west-2.compute.amazonaws.com", 
					"factory_default", "yes", "54321", "customer1-cluster1-node1", NODE1,
					"22.33.44.000", "no", "no", "12345", "disconnected", "tiger,lion", "1", "XXXXXX", NODE1,
					"dhcp", "172.17.0.1", "2.0.0", "na", "master", "db2", "50GB", "100GB", "1.1.1.1", "compute-dev.nvidia.com", "yes", "56",
					"http://dgx1-bootstrap-repo-devel1.nvidia.com/", cluster1,"upgradeOnBoot","1");
			
			nodeService.saveNode(node);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveNode1"}) // update the existing node  -- working well
	public void testUpdateNode1() throws Exception {
		//cleanUp();
		try{
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
            Node node = nodeService.findNodeByName(NODE1);
			
            node.setName(NODE1);
			node.setSerialNumber("ec26b4bf-4d38-d70e-7f33-b2e72f6c6254");
			node.setNodeKey("ec2-52-38-12-79.us-west-2.compute.amazonaws.com");
			node.setCloudManaged("factory_default");
			node.setIsleader("yes");
			node.setFwVersion("54321");
			node.setSerialId(NODE1);
			node.setSubNet("22.33.44.000");
			node.setFirstBoot("no");
			node.setEulaAccepted("no");
			node.setBiosVersion("12345");
			node.setCloudStatus("disconnected");
			node.setTags("tiger,lion");
			node.setGpuConfiguration("1");
			node.setIpmi("XXXXXX");
			node.setNodeId(NODE1);
			node.setMode("dhcp");
			node.setIpAddress("172.17.0.1");
			node.setSwVersion("2.0.0");
			node.setCloudGroup("na");
			node.setClusterGroup("master");
			node.setModelName("db2");
			node.setMemory("50GB");
			node.setDiskSpace("100GB");
			node.setGateway("1.1.1.1");
			node.setCluster(cluster1);
			node.setCloudUrl("compute-dev.nvidia.com12");
			node.setUserAdded("yes12");
			node.setTotalCpuCores("5612");
			node.setRepoUrl("http://dhjhkjkjkjkj.com/");
			nodeService.updateNode(node);
			
           
            //logger.info("node is updated :: " + NODE1);
			
			Node node1 = nodeService.findNodeByName(node.getName());
			
			 //logger.info("node information is fetched again and name is "+node1.getName());
			assertNotNull(node1.getId());
			assertEquals(node1.getName(), NODE1);
			assertEquals(node1.getSerialNumber(), "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254");
			assertEquals(node1.getNodeKey(), "ec2-52-38-12-79.us-west-2.compute.amazonaws.com");
			assertEquals(node1.getCloudManaged(), "factory_default");
			assertEquals(node1.getIsleader(),"yes");
			assertEquals(node1.getFwVersion(),"54321");
			assertEquals(node1.getSerialId(),NODE1);
			assertEquals(node1.getSubNet(), "22.33.44.000");
			assertEquals(node1.getFirstBoot(),"no");
			assertEquals(node1.getEulaAccepted(),"no");
			assertEquals(node1.getBiosVersion(),"12345");
			assertEquals(node1.getCloudStatus(),"disconnected");
			assertEquals(node1.getTags(),"tiger,lion");
			assertEquals(node1.getGpuConfiguration(),"1");
			assertEquals(node1.getIpmi(),"XXXXXX");
			assertEquals(node1.getNodeId(),NODE1);
			assertEquals(node1.getMode(),"dhcp");
			assertEquals(node1.getIpAddress(),"172.17.0.1");
			assertEquals(node1.getSwVersion(),"2.0.0");
			assertEquals(node1.getCloudGroup(),"na");
			assertEquals(node1.getClusterGroup(),"master");
			assertEquals(node1.getModelName(),"db2");
			assertEquals(node1.getMemory(),"50GB");
			assertEquals(node1.getDiskSpace(),"100GB");
			assertEquals(node1.getGateway(),"1.1.1.1");
			assertEquals(node1.getCluster(), cluster1);
			assertEquals(node1.getCloudUrl(), "compute-dev.nvidia.com12");
			assertEquals(node1.getUserAdded(), "yes12");
			assertEquals(node1.getTotalCpuCores(), "5612");
			assertEquals(node1.getRepoUrl(), "http://dhjhkjkjkjkj.com/");
			
			assert true;
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = NodeNotFoundException.class) // update the non existing node
	public void testUpdateNode2() throws Exception {
		//cleanUp();
		    Node node =  new Node();
            node.setId(189898);
            nodeService.updateNode(node);
	}

	
	@Test(priority=5,dependsOnMethods = {"testSaveNode1"}) // find all nodes
	public void testFindAllNodes() throws Exception {
		try { // works well
			
			List<Node> nl= nodeService.findAllNodes();
			
			//logger.info("nl size is "+nl.size());
			assertNotNull(nl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveNode1"}) // get node by name
	public void testFindNodeByName1() throws Exception {// works well
		try {
			Node node1 = nodeService.findNodeByName(NODE1);
			
			//logger.info("node information is fetched again and name is "+node1.getName());
			assertNotNull(node1.getId());
			assertEquals(node1.getName(), NODE1);
			assertEquals(node1.getSerialId(), NODE1);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveNode1"}) // get  node by noexisting name  -- working well
	public void testFindNodeByName2() throws Exception {
		try {
			Node node1 = nodeService.findNodeByName("abcd");
			assertNull(node1);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	@Test(priority=8,dependsOnMethods = {"testSaveNode1"}) // get node by name and cluster name -- wroks well
	public void findNodeByNameAndCluster1() throws Exception {
		try {
			Cluster cluster1= clusterService.findByClusterName(CLUSTER1);
			Node node1 = nodeService.findNodeByNameAndCluster(NODE1, CLUSTER1,2);
			
			//logger.info("node information is fetched again and name is "+node1.getName());
			assertNotNull(node1.getId());
			assertEquals(node1.getName(), NODE1);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // get  node by noexisting name and non existing clustercluster
	public void findNodeByNameAndCluste2() throws Exception {
		  
		nodeService.findNodeByNameAndCluster("node","cluster",2);// works well
			
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // get  node by nonexisting name and existing clustercluster
	public void findNodeByNameAndCluste3() throws Exception {
		  
		nodeService.findNodeByNameAndCluster("node", CLUSTER1,2); // works well
			
	}
	
    @Test(priority=11,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // get  node by existing name and non existing clustercluster
	public void findNodeByNameAndCluste4() throws Exception {
		  
		nodeService.findNodeByNameAndCluster(NODE1, "cluster",2);// works well
			
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveNode1"}) // get all nodes for by cluster id
	public void testfindNodeByClusterId1() throws Exception {// works well
		try {
			
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			List<Node> nl= nodeService.findAllNodesByCluster(cluster1.getId());
			
			
			assertNotNull(nl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveNode1"}) // get all nodes for non existing cluster id
	public void testfindNodeByClusterId2() throws Exception { // works well
        try {
			
			Cluster cluster1 = new Cluster();
			cluster1.setId(1234);
			List<Node> nl= nodeService.findAllNodesByCluster(cluster1.getId());
			
			
			assertEquals(nl.size(), 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}

	}
	
	@Test(priority=14,dependsOnMethods = {"testSaveNode1"}) // get all nodes for by cluster name
	public void testfindNodeByClusterName1() throws Exception { // works well
		try {
			
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			//logger.info("cluster name "+cluster1.getName());
			List<Node> nl= nodeService.findNodeByCluster(cluster1.getName());
			//logger.info("nl size is "+nl.size());
			
			assertNotNull(nl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=15,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // get all nodes for non existing cluster name
	public void testfindNodeByClusterName2() throws Exception {
		// works well
	    nodeService.findNodeByCluster("jhkukl");
	}
	
	
	
	@Test(priority=16,dependsOnMethods = {"testSaveNode1"}) // get  node  by ip address
	public void testfindNodeByIpAddress1() throws Exception {
		// works well
			try {
				Node node1= nodeService.findNodeByIpAddress("172.17.0.1");
				
				//logger.info("node information is fetched again and name is "+node1.getName());
				assertNotNull(node1.getId());
				assertEquals(node1.getName(), NODE1);
				
				assert true;
				
			} catch (Exception e) {
				e.printStackTrace();
				assert false;
			}
	}
	
	@Test(priority=17,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // get all nodes for non existing ip address
	public void testfindNodeByIpAddress2() throws Exception { // works well
		
	  nodeService.findNodeByIpAddress("192.168.1.1");
	}
	
	@Test(priority=18,dependsOnMethods = {"testSaveNode1"}) // get  node by id
	public void testFindNodeById1() throws Exception {
		try { // works well
			Node node1 = nodeService.findNodeByName(NODE1);
			
			//logger.info("node information is fetched again and name is "+node1.getId());
			Node node2 = nodeService.findById(node1.getId());
			
			//logger.info("node information is fetched again and name is "+node2.getName());
			assertNotNull(node2.getId());
			assertEquals(node2.getName(), NODE1);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=19,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // get node by non existing id
	public void testFindNodeById2() throws Exception {
		
			Node node1 = nodeService.findById(980998);
			
	}
	
	@Test(priority=20,dependsOnMethods = {"testSaveNode1"}) // delete node By Id
	public void testDeleteNodeById1() throws Exception {
		try {
			
           Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
		   Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			Node node =new Node("node1", "ec26b4bf-4d38-d70e-7f33-b2e72f6c6254", 
					"ec2-52-38-12-79.us-west-2.compute.amazonaws.com", 
					"factory_default", "yes", "54321", "customer1-cluster1-node1", "node1",
					"22.33.44.000", "no", "no", "12345", "disconnected", "tiger,lion", "1", "XXXXXX", "node1",
					"dhcp", "172.17.0.1", "2.0.0", "na", "master", "db2", "50GB", "100GB", "1.1.1.1", "compute-dev.nvidia.com", "yes", "56",
					"http://dgx1-bootstrap-repo-devel1.nvidia.com/", cluster1,"upgradeOnBoot","1");
			
			nodeService.saveNode(node);
			
			Node node1 = nodeService.findNodeByName("node1");
			
			//logger.info("node information is fetched again and name is "+node1.getName());
			nodeService.deleteNodeById(node1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=21,dependsOnMethods = {"testSaveNode1"},expectedExceptions=NodeNotFoundException.class) // delete non existimg node
	public void testDeleteNodeById2() throws Exception {
		
		nodeService.deleteNodeById(989887);
			
	}
	
	@Test(priority=22,dependsOnMethods = {"testSaveNode1"}) // update status by time
	public void testupdateNodeStatusByTime() throws Exception {
		//cleanUp();
		try{
			 Node node = nodeService.findNodeByName(NODE2);
			 
			 //logger.info("node is created   " + node.getName()+" and status is "+node.getCloudStatus());
			 Thread.sleep(90000);
			// nodeService.updateNodeStatusByTime(1);
			
            Node node1 = nodeService.findById(node.getId());
			
			//logger.info("node information is fetched again and name is "+node1.getName()+" and  status is "+node1.getCloudStatus());
			assertNotNull(node1.getId());
			assertEquals(node1.getCloudStatus(),"disconnected");
			
			
			assert true;
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}*/

}
