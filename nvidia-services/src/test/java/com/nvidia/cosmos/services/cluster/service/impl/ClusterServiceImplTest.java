/**
 * 
 */
package com.nvidia.cosmos.services.cluster.service.impl;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.ClusterExistsException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

/**
 * @author bprasad
 * 
 */
@Test
public class ClusterServiceImplTest extends Assert {
	
	//private Logger logger = LoggerFactory.getLogger(ClusterServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	
	final static String CUSTOMER1 = USERNAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO ="8078877899";
	
	final static String CLUSTER1 = USERNAME + "_cluster1";
	final static String CLUSTER1IPADDRESS =CLUSTER1+"@mail.com";
	final static String CLUSTER1SERAILNO ="SerialNumber1";
	final static String NFS_DETAILS ="10.12.45.6.7.8";
	
	final static String CLUSTER2 = USERNAME + "_cluster2";
	final static String CLUSTER2IPADDRESS =CLUSTER2+"@mail.com";
	final static String CLUSTER2SERAILNO ="SerialNumber2";
	
	ClusterService clusterService = null;
	ServicesFactory factory = null;
	UserService userService = null;
	CustomerService customerService = null;
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		clusterService = factory.getClusterService();
		customerService = factory.getCustomerService();
		userService = factory.getUserService();
		cleanUp();
		cleanUser(CUSTOMER1EMAIL);
		cleanCustomer(CUSTOMER1EMAIL);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
		cleanUser(CUSTOMER1EMAIL);
		cleanCustomer(CUSTOMER1EMAIL);
	}

	private void cleanUp() throws Exception {
		clean(CLUSTER1);
		clean(CLUSTER2);
		//cleanUser(CUSTOMER1EMAIL);
		//cleanCustomer(CUSTOMER1EMAIL);
		
	}
	
	private void clean(String clusterName) throws Exception {
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
	
	private void cleanUser(String userEmail) throws Exception {
		User user = null;
		try {
			user = userService.findUserByEmail(userEmail);
			if(user!=null){
				userService.deleteUserById(user.getId());
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

	
	
	/*@Test(priority=1)// create cluster
	public void testSaveCluster() throws Exception {
		//cleanUp();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			customerService.saveCustomer(customer);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Cluster cluster = new Cluster(CLUSTER1, CLUSTER1IPADDRESS,NFS_DETAILS,CLUSTER1SERAILNO,customer1);
			
			clusterService.saveCluster(cluster);
			
			//logger.info("Cluster is created in test ", CLUSTER1);
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			//logger.info("Cluster information is fetched again and name is "+cluster1.getName());
			assertNotNull(cluster1.getId());
			assertEquals(cluster1.getName(), CLUSTER1);
			assertEquals(cluster1.getIpAddress(), CLUSTER1IPADDRESS);
			assertEquals(cluster1.getNfsDetails(), NFS_DETAILS);
			assertEquals(cluster1.getSerialNumber(), CLUSTER1SERAILNO);
			
			assert true;
			
           Cluster cluster2 = new Cluster(CLUSTER2, CLUSTER2IPADDRESS,NFS_DETAILS,CLUSTER2SERAILNO,customer1);
			
			clusterService.saveCluster(cluster2);
			
			//logger.info("Cluster is created in test " + CLUSTER2);
			Cluster cluster12 = clusterService.findByClusterName(CLUSTER2);
			
			//logger.info("Cluster information is fetched again and name is "+cluster12.getName());
			assertNotNull(cluster12.getId());
			assertEquals(cluster12.getName(), CLUSTER2);
			assertEquals(cluster12.getIpAddress(), CLUSTER2IPADDRESS);
			assertEquals(cluster12.getNfsDetails(), NFS_DETAILS);
			assertEquals(cluster12.getSerialNumber(), CLUSTER2SERAILNO);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = ClusterExistsException.class,dependsOnMethods = {"testSaveCluster"}) // create a existing cluster
	public void testSaveCluster1() throws Exception {
		
            Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
            Cluster cluster = new Cluster(CLUSTER1, CLUSTER1IPADDRESS,NFS_DETAILS,CLUSTER1SERAILNO,customer1);
			clusterService.saveCluster(cluster);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveCluster"}) // update the existing cluster
	public void testUpdateCluster1() throws Exception {
		//cleanUp();
		try{
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
            Cluster cluster = clusterService.findByClusterName(CLUSTER1);
			
            cluster.setName(CLUSTER1);
            cluster.setIpAddress(CLUSTER1IPADDRESS);
            cluster.setNfsDetails(NFS_DETAILS);
            cluster.setSerialNumber(CLUSTER1SERAILNO);
            cluster.setCustomer(customer1);
			
			clusterService.updateCluster(cluster);
			
          
			//logger.info("cluster is created :: " + cluster.getName());
			
			Cluster cluster1 = clusterService.findByClusterName(cluster.getName());
			
			//logger.info("cluster information is fetched again and name is "+cluster1.getName());
			assertNotNull(cluster1.getId());
			assertEquals(cluster1.getName(), CLUSTER1);
			assertEquals(cluster1.getIpAddress(), CLUSTER1IPADDRESS);
			assertEquals(cluster1.getNfsDetails(), NFS_DETAILS);
			assertEquals(cluster1.getSerialNumber(), CLUSTER1SERAILNO);
			
			
			assert true;
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,dependsOnMethods = {"testSaveCluster"},expectedExceptions = ClusterNotFoundException.class) // update the non existing cluster
	public void testUpdateCluster2() throws Exception {
		
		    Cluster cluster =  new Cluster();
            cluster.setId(111111);
            clusterService.updateCluster(cluster);
	}
	
	@Test(priority=5,dependsOnMethods = {"testSaveCluster"}) // find all clusters
	public void testFindAllCluster() throws Exception {
		try {
			
			List<Cluster> cl= clusterService.findAllClusters();
			assertNotNull(cl.size());
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveCluster"}) // get cluster by name
	public void testFindClusterByName1() throws Exception {
		try {
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			//logger.info("cluster information is fetched again and name is "+cluster1.getName());
			assertNotNull(cluster1.getId());
			assertEquals(cluster1.getName(), CLUSTER1);
			assertEquals(cluster1.getIpAddress(), CLUSTER1IPADDRESS);
			assertEquals(cluster1.getNfsDetails(), NFS_DETAILS);
			assertEquals(cluster1.getSerialNumber(), CLUSTER1SERAILNO);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveCluster"}) // get  cluster by noexisting name
	public void testFindClusterByName2() throws Exception {
		
		try {
			Cluster cluster1 = clusterService.findByClusterName("abcd");
			assertNull(cluster1);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveCluster"}) // get all cluster by id
	public void testFindClusterById1() throws Exception {
		try {
			Cluster cluster1 = clusterService.findByClusterName(CLUSTER1);
			
			//logger.info("cluster information is fetched again and id is "+cluster1.getId());
			Cluster cluster2 = clusterService.findById(cluster1.getId());
			
			//logger.info("cluster information is fetched again and name is "+cluster2.getName());
			assertNotNull(cluster2.getId());
			assertEquals(cluster2.getName(), CLUSTER1);
			assertEquals(cluster2.getIpAddress(), CLUSTER1IPADDRESS);
			assertEquals(cluster2.getNfsDetails(), NFS_DETAILS);
			assertEquals(cluster2.getSerialNumber(), CLUSTER1SERAILNO);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveCluster"}) // get cluster by non existing id
	public void testFindClusterById2() throws Exception {
		
		try {
			Cluster cluster1 = clusterService.findByClusterName("abcd");
			assertNull(cluster1);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	@Test(priority=9,dependsOnMethods = {"testSaveCluster"},expectedExceptions=ClusterNotFoundException.class) // get cluster by non existing id
	public void testFindClusterById2() throws Exception {
		
			Cluster cluster1 = clusterService.findById(2656565);
			
	}

	
	@Test(priority=10,dependsOnMethods = {"testSaveCluster"}) // get all cluster for by customer id
	public void testFindAllClusterByCustId1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			List<Cluster> cl= clusterService.findClusterByCustomerId(customer1.getId());
			
			assertNotNull(cl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveCluster"}) // get all clusters for no existing customer
	public void testFindAllClusterByCustId2() throws Exception {
         try {
			
			List<Cluster> cl= clusterService.findClusterByCustomerId(1234);
			
			//assertNotNull(cl.size());
			assertEquals(cl.size(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveCluster"}) // delete cluster By Id
	public void testDeleteClusterById1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Cluster cluster = new Cluster("cluster", "clusterip","10.12.45.6.7.9","sn1",customer1);
			clusterService.saveCluster(cluster);
			
			Cluster cluster1 = clusterService.findById(cluster.getId());
			
			//logger.info("User information is fetched again and name is :"+cluster1.getName());
			clusterService.deleteClusterById(cluster1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveCluster"},expectedExceptions=ClusterNotFoundException.class) // delete non existimg cluster
	public void testClusterUserById2() throws Exception {
		
			clusterService.deleteClusterById(87878);
			
	}*/
	

}
