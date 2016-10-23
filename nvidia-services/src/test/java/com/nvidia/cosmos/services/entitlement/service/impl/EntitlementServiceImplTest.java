/**
 * 
 */
package com.nvidia.cosmos.services.entitlement.service.impl;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.EntitlementExistsException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.SerialNumberExitsException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;

/**
 * @author bprasad
 * 
 */
@Test
public class EntitlementServiceImplTest extends Assert {
	//private Logger logger = LoggerFactory.getLogger(EntitlementServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	
	final static String CUSTOMER1 = USERNAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO="8078877899";
	
	final static String ENTITLEMENT1 = USERNAME + "_entitlement1";
	final static String ENTITLEMENT1KEY =ENTITLEMENT1+"AHSA7878AAS";
	
	final static String ENTITLEMENT2 = USERNAME + "_entitlement2";
	final static String ENTITLEMENT2KEY =ENTITLEMENT2+"AHSA7878AAS";
	
	final static String SERIALNO1="serialNumber1";
	final static String SERIALNO2="serialNumber2";
	
	
	EntitlementService entitlementService = null;
	CustomerService customerService =null;
	ServicesFactory factory = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		entitlementService = factory.getEntitlementService();
		customerService = factory.getCustomerService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(ENTITLEMENT1KEY);
		clean(ENTITLEMENT2KEY);
		cleanCustomer(CUSTOMER1EMAIL);
		
	}
	
	private void clean(String entitlementKey) throws Exception {
		Entitlement entitlement = null;

		try {
			entitlement = entitlementService.findEntitlementByKey(entitlementKey);
			if(entitlement!=null){
				entitlementService.deleteEntitlementById(entitlement.getId());
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
	public void testSaveEntitlement1() throws Exception { // create new entitlement
		//cleanUp();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			customerService.saveCustomer(customer);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Entitlement entitlement = new Entitlement(); 
			entitlement.setCustomerName(ENTITLEMENT1);
			entitlement.setKey(ENTITLEMENT1KEY);
			entitlement.setSerialNumber(SERIALNO1);
			entitlement.setCustomer(customer1);
			entitlement.setCreatedDate(new Date());
			
			entitlementService.saveEntitlement(entitlement);
			
			//logger.info("Entitlement is created in test " + ENTITLEMENT1);
			Entitlement entitlement1 = entitlementService.findEntitlementByKey(ENTITLEMENT1KEY);
			
			
			//logger.info("Entitlement information is fetched again and name is "+entitlement1.getCustomerName());
			assertNotNull(entitlement1.getId());
			assertEquals(entitlement1.getCustomerName(), ENTITLEMENT1);
			assertEquals(entitlement1.getKey(), ENTITLEMENT1KEY);
			assertEquals(entitlement1.getSerialNumber(), SERIALNO1);
			
			
			assert true;
			
			Entitlement entitlement2 = new Entitlement(); 
			entitlement2.setCustomerName(ENTITLEMENT2);
			entitlement2.setKey(ENTITLEMENT2KEY);
			entitlement2.setSerialNumber(SERIALNO2);
			entitlement2.setCustomer(customer1);
			entitlement2.setCreatedDate(new Date());
			
			entitlementService.saveEntitlement(entitlement2);
			
			//logger.info("Entitlement is created in test " + ENTITLEMENT1);
			Entitlement entitlement12 = entitlementService.findEntitlementByKey(ENTITLEMENT2KEY);
			
			
			//logger.info("Entitlement information is fetched again and name is "+entitlement12.getCustomerName());
			assertNotNull(entitlement2.getId());
			assertEquals(entitlement2.getCustomerName(), ENTITLEMENT2);
			assertEquals(entitlement2.getKey(), ENTITLEMENT2KEY);
			assertEquals(entitlement2.getSerialNumber(), SERIALNO2);
			
			
			assert true;
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = EntitlementExistsException.class,dependsOnMethods = {"testSaveEntitlement1"}) // create a existing entitlement key
	public void testSaveEntitlement2() throws Exception {
		    Entitlement entitlement = entitlementService.findEntitlementByKey(ENTITLEMENT1KEY);
		    		
			entitlementService.saveEntitlement(entitlement);
	}
	
	@Test(priority=3,expectedExceptions = SerialNumberExitsException.class,dependsOnMethods = {"testSaveEntitlement1"}) // create a existing serail no
	public void testSaveEntitlement3() throws Exception {
		    Entitlement entitlement = new Entitlement();
			entitlement.setKey("kjkjkj");
			entitlement.setSerialNumber(SERIALNO1);
		    		
			entitlementService.saveEntitlement(entitlement);
	}
	
	@Test(priority=4,dependsOnMethods = {"testSaveEntitlement1"}) // update the existing entitlement
	public void testUpdateEntitlement1() throws Exception {
		//cleanUp();
		try{
            Entitlement entitlement = entitlementService.findEntitlementByKey(ENTITLEMENT1KEY);
			
            entitlement.setKey(ENTITLEMENT1KEY);
            entitlement.setCustomerName(ENTITLEMENT1);
            entitlement.setSerialNumber(SERIALNO1);
            
            entitlementService.updateEntitlement(entitlement);
			
            
            //logger.info("entitlement is updated :: " + ENTITLEMENT1);
			
			Entitlement entitlement1 = entitlementService.findEntitlementByKey(ENTITLEMENT1KEY);
			
			//logger.info("entitlement information is fetched again and key is "+entitlement1.getKey());
			assertNotNull(entitlement1.getId());
			assertEquals(entitlement1.getCustomerName(), ENTITLEMENT1);
			assertEquals(entitlement1.getKey(), ENTITLEMENT1KEY);
			assertEquals(entitlement1.getSerialNumber(), SERIALNO1);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=5,expectedExceptions = EntitlementNotFoundException.class) // update the non existing entitlement
	public void testUpdateEntitlement2() throws Exception {
		
		    Entitlement entitlement =  new Entitlement();
		    entitlement.setId(111111);
		    entitlementService.updateEntitlement(entitlement);
	}


	@Test(priority=6,dependsOnMethods = {"testSaveEntitlement1"}) // find all entitlement
	public void testFindAllEntitlement() throws Exception {
		try {
			
			List<Entitlement> el= entitlementService.findAllEntitlements();
			
			//logger.info("el size"+el.size());
			
			assertNotNull(el.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveEntitlement1"}) // get entitlement by  key
	public void testfindEntitlementByKey1() throws Exception {
		try {
			Entitlement entitlement1 = entitlementService.findEntitlementByKey(ENTITLEMENT1KEY);
			
			//logger.info("entitlement information is fetched again and name is "+entitlement1.getKey());
			assertNotNull(entitlement1.getId());
			assertEquals(entitlement1.getCustomerName(), ENTITLEMENT1);
			assertEquals(entitlement1.getKey(), ENTITLEMENT1KEY);
			assertEquals(entitlement1.getSerialNumber(), SERIALNO1);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveEntitlement1"}) // get  entitlement by noexisting  key
	public void testfindEntitlementByKey2() throws Exception {
		
		try {
			Entitlement entitlement1 = entitlementService.findEntitlementByKey("abcd");
			
			assertNull(entitlement1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveEntitlement1"}) // get entitlement by  serial number
	public void testfindEntitlementBySerialNumber1() throws Exception {
		try {
			Entitlement entitlement1 = entitlementService.findEntitlementBySerialNumber(SERIALNO1);
			
			//logger.info("entitlement information is fetched again and name is "+entitlement1.getKey());
			assertNotNull(entitlement1.getId());
			assertEquals(entitlement1.getCustomerName(), ENTITLEMENT1);
			assertEquals(entitlement1.getKey(), ENTITLEMENT1KEY);
			assertEquals(entitlement1.getSerialNumber(), SERIALNO1);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveEntitlement1"},expectedExceptions=EntitlementNotFoundException.class) // get  entitlement by noexisting  serial no
	public void testfindEntitlementBySerialNumber2() throws Exception {
		
			Entitlement entitlement1 = entitlementService.findEntitlementBySerialNumber("jkjkjkjk");
			
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveEntitlement1"}) // get  entitlement by id
	public void testFindEntitlementById1() throws Exception {
		try {
			Entitlement entitlement1 = entitlementService.findEntitlementByKey(ENTITLEMENT1KEY);
			
			//logger.info("entitlement information is fetched again and name is "+entitlement1.getCustomerName());
			
			Entitlement entitlement2 = entitlementService.findById(entitlement1.getId());
			
			//logger.info("entitlement information is fetched again and name is "+entitlement2.getCustomerName());
			assertNotNull(entitlement2.getId());
			assertEquals(entitlement1.getCustomerName(), ENTITLEMENT1);
			assertEquals(entitlement1.getKey(), ENTITLEMENT1KEY);
			assertEquals(entitlement1.getSerialNumber(), SERIALNO1);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveEntitlement1"},expectedExceptions=EntitlementNotFoundException.class) // get entitlement by non existing id
	public void testFindEntitlementById2() throws Exception {
		
			Entitlement entitlement1 = entitlementService.findById(8989898);
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveEntitlement1"}) // delete entitlement By Id
	public void testDeleteEntitlementById1() throws Exception {
		try {
			
             Entitlement entitlement = new Entitlement("entitlement", "entitlementkey", "serialNumber3"); 
			entitlementService.saveEntitlement(entitlement);
			Entitlement entitlement1 = entitlementService.findEntitlementByKey("entitlementkey");
			
			//logger.info("entitlement information is fetched again and name is "+entitlement1.getCustomerName());
			entitlementService.deleteEntitlementById(entitlement1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=14,dependsOnMethods = {"testSaveEntitlement1"},expectedExceptions=EntitlementNotFoundException.class) // delete non existimg entitlement
	public void testDeleteEntitlementById2() throws Exception {
		
			entitlementService.deleteEntitlementById(9898989);
			
	}*/
}
