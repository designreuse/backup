/**
 * 
 */
package com.nvidia.cosmos.services.appliance.service.impl;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.ApplianceExistsException;
import com.nvidia.cosmos.cloud.exceptions.ApplianceNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;
import com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;

/**
 * @author bprasad
 * 
 */
@Test
public class ApplianceServiceImplTest extends Assert {
	//private Logger logger = LoggerFactory.getLogger(ApplianceServiceImplTest.class);
	
	final static String APPLIANCENAME = System.getProperty("user.name");
	
	final static String CUSTOMER1 = APPLIANCENAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO ="8078877899";
	
	final static String APPLIANCE1 = APPLIANCENAME + "_appliance1";
	final static String APPLIANCE1SERVICE_KEY =APPLIANCE1+"_2323_2323-32";
	
	final static String APPLIANCE2 = APPLIANCENAME + "_appliance2";
	final static String APPLIANCE2SERVICE_KEY =APPLIANCE2+"_2323_2323-32";
	
	ApplianceService applianceService = null;
	ServicesFactory factory = null;
	CustomerService customerService = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		applianceService = factory.getApplianceService();
		customerService = factory.getCustomerService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(APPLIANCE1SERVICE_KEY);
		clean(APPLIANCE2SERVICE_KEY);
		cleanCustomer(CUSTOMER1EMAIL);
	}
	
	private void clean(String applianceServiceKey) throws Exception {
		Appliance appliance = null;
		try {
			appliance = applianceService.findApplianceByServiceKey(applianceServiceKey);
			if(appliance!=null){
				applianceService.deleteApplianceById(appliance.getId());
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
	
	
	/*@Test(priority= 1)
	public void testSaveAppliance1() throws Exception { // save a new appliance
		//cleanUp();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			customerService.saveCustomer(customer);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Appliance appliance = new Appliance(APPLIANCE1, APPLIANCE1SERVICE_KEY, customer1); 
			
			applianceService.saveAppliance(appliance);
			
		//	//logger.info("Appliance is created in test " + APPLIANCE1);
			Appliance appliance1 = applianceService.findApplianceByServiceKey(APPLIANCE1SERVICE_KEY);
			
		//	//logger.info("Appliance information is fetched again and name is "+appliance1.getName());
			assertNotNull(appliance1.getId());
			assertEquals(appliance1.getName(), APPLIANCE1);
			assertEquals(appliance1.getServiceKey(), APPLIANCE1SERVICE_KEY);
			
			assert true;
			
            Appliance appliance2 = new Appliance(APPLIANCE2, APPLIANCE2SERVICE_KEY, customer1); 
			
			applianceService.saveAppliance(appliance2);
			
			//logger.info("Appliance is created in test " + APPLIANCE2);
			Appliance appliance12 = applianceService.findApplianceByServiceKey(APPLIANCE2SERVICE_KEY);
			
			//logger.info("Appliance information is fetched again and name is "+appliance12.getName());
			assertNotNull(appliance12.getId());
			assertEquals(appliance12.getName(), APPLIANCE2);
			assertEquals(appliance12.getServiceKey(), APPLIANCE2SERVICE_KEY);
			
			assert true;
		
		
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = ApplianceExistsException.class,dependsOnMethods = {"testSaveAppliance1"}) // create a existing appliance
	public void testSaveAppliance2() throws Exception {
		    Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Appliance appliance = new Appliance(APPLIANCE1, APPLIANCE1SERVICE_KEY, customer1); 
			applianceService.saveAppliance(appliance);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveAppliance1"}) // update the existing appliance
	public void testUpdateAppliance1() throws Exception {
		//cleanUp();
		try{
            Appliance appliance = applianceService.findApplianceByServiceKey(APPLIANCE1SERVICE_KEY);
			
            appliance.setName(APPLIANCE1);
            appliance.setServiceKey(APPLIANCE1SERVICE_KEY);
			applianceService.updateAppliance(appliance);
			
            
            //logger.info("appliance is updated :: " + APPLIANCE1);
			
			Appliance appliance1 = applianceService.findApplianceByServiceKey(APPLIANCE1SERVICE_KEY);
			
			//logger.info("apliance information is fetched again and name is "+appliance1.getName());
			assertNotNull(appliance1.getId());
			assertEquals(appliance1.getName(), APPLIANCE1);
			assertEquals(appliance1.getServiceKey(), APPLIANCE1SERVICE_KEY);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = ApplianceNotFoundException.class) // update the non existing apploiance
	public void testUpdateAppliance2() throws Exception {
		//cleanUp();
		    Appliance appliance =  new Appliance();
		    appliance.setId(111111);
            applianceService.updateAppliance(appliance);
	}
	
	@Test(priority=5,dependsOnMethods = {"testSaveAppliance1"}) // find all appliances
	public void testFindAllAppliance() throws Exception {
		try {
			
			List<Appliance> al= applianceService.findAllAppliances();
			
			//logger.info("al size "+al.size());
			assertNotNull(al.size());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveAppliance1"}) // get applaince by service key
	public void testfindApplianceByServiceKey1() throws Exception {
		try {
			Appliance appliance1 = applianceService.findApplianceByServiceKey(APPLIANCE1SERVICE_KEY);
			
			//logger.info("appliance information is fetched again and name is  "+appliance1.getName());
			assertNotNull(appliance1.getId());
			assertEquals(appliance1.getName(), APPLIANCE1);
			assertEquals(appliance1.getServiceKey(), APPLIANCE1SERVICE_KEY);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveAppliance1"},expectedExceptions=ApplianceNotFoundException.class) // get  appliance by noexisting service key
	public void testfindApplianceByServiceKey2() throws Exception {
		
			Appliance appliance1 = applianceService.findApplianceByServiceKey("nbnmm");
			
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveAppliance1"}) // get all appliances for by customer id
	public void testfindAllAppliancesByCustId1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			List<Appliance> al= applianceService.findAllAppliances(customer1.getId());
			
			//logger.info("al size with customer id"+al.size());
			
			assertNotNull(al.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveAppliance1"},expectedExceptions=CustomerNotFoundException.class) // get all appliances for non existing customer
	public void testfindAllAppliancesByCustId2() throws Exception {
		
		applianceService.findAllAppliances(8787878);
	}
	

	@Test(priority=10,dependsOnMethods = {"testSaveAppliance1"}) // get  appliances by id
	public void testFindApplianceById1() throws Exception {
		try {
			Appliance appliance1 = applianceService.findApplianceByServiceKey(APPLIANCE1SERVICE_KEY);
			
			//logger.info("appliance information is fetched again and name is "+appliance1.getName());
			Appliance appliance2 = applianceService.findById(appliance1.getId());
			
			//logger.info("appliances information is fetched again and name is "+appliance2.getName());
			assertNotNull(appliance2.getId());
			assertEquals(appliance2.getName(), APPLIANCE1);
			assertEquals(appliance2.getServiceKey(), APPLIANCE1SERVICE_KEY);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveAppliance1"},expectedExceptions=ApplianceNotFoundException.class) // get appliances by non existing id
	public void testFindApplianceById2() throws Exception {
		
			Appliance appliance1 = applianceService.findById(89898989);
			
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveAppliance1"}) // delete appliance By Id
	public void testDeleteApplianceById1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
            Appliance appliance = new Appliance("appliancename1", "appliancekey1", customer1); 
			
			applianceService.saveAppliance(appliance);
			Appliance appliance1 = applianceService.findApplianceByServiceKey("appliancekey1");
			
			//logger.info("appliance information is fetched again and name is "+appliance1.getName());
			applianceService.deleteApplianceById(appliance1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveAppliance1"},expectedExceptions=ApplianceNotFoundException.class) // delete non existimg appliance
	public void testDeleteApplianceById2() throws Exception {
		
			applianceService.deleteApplianceById(6878787);
			
	}*/


}
