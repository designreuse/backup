/**
 * 
 */
package com.nvidia.cosmos.services.customer.service.impl;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.CustomerExistsException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

/**
 * @author bprasad
 * 
 */
@Test
public class CustomerServiceImplTest extends Assert {
	//private Logger logger = LoggerFactory.getLogger(CustomerServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	final static String CUSTOMER1 = USERNAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO="8078877899";
	
	final static String CUSTOMER2 = USERNAME + "_customer2";
	final static String CUSTOMER2EMAIL ="admin@nvidia.com";
	
	CustomerService customerService = null;
	UserService userService = null;
	RoleService roleService =null;
	ServicesFactory factory = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		customerService = factory.getCustomerService();
		userService = factory.getUserService();
		roleService= factory.getRoleService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(CUSTOMER1EMAIL);
		clean(CUSTOMER2EMAIL);
		
	}
	
	private void clean(String customerEmail) throws Exception {
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
	public void testSaveCustomer1() throws Exception { // create customer
		cleanUp();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			
			customerService.saveCustomer(customer);
			
			//logger.info("Customer is created in test " + CUSTOMER1);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			
			//logger.info("Customer information is fetched again and name is "+customer1.getName());
			assertNotNull(customer1.getId());
			assertEquals(customer1.getName(), CUSTOMER1);
			assertEquals(customer1.getEmail(), CUSTOMER1EMAIL);
			assertEquals(customer1.getPhone(), PH_NO);
			
			assert true;
			
            Customer customer2 = new Customer(CUSTOMER2, CUSTOMER2EMAIL, PH_NO); 
			
			customerService.saveCustomer(customer2);
			
			//logger.info("Customer is created in test " + CUSTOMER2);
			Customer customer12 = customerService.findCustomerByEmail(CUSTOMER2EMAIL);
			
			
			//logger.info("Customer information is fetched again and name is "+customer12.getName());
			assertNotNull(customer12.getId());
			assertEquals(customer12.getName(), CUSTOMER2);
			assertEquals(customer12.getEmail(), CUSTOMER2EMAIL);
			assertEquals(customer12.getPhone(), PH_NO);
			
			assert true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = CustomerExistsException.class,dependsOnMethods = {"testSaveCustomer1"}) // create a existing customer
	public void testSaveCustomer2() throws Exception {
		   Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
		
		   customerService.saveCustomer(customer);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveCustomer1"}) // update the existing customer
	public void testUpdateCustomer1() throws Exception {
		//cleanUp();
		try{
            Customer customer = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
            customer.setName(CUSTOMER1);
            customer.setEmail(CUSTOMER1EMAIL);
            customer.setPhone(PH_NO);
            customer.setClientId("clientid");
            customer.setUpdatedDate(new Date());
            customer.setGrafanaAuthToken("authtoken");
            customer.setGrafanaOrgId("orgid");
			customerService.updateCustomer(customer);
			
            
            //logger.info("customer is updated :: " + CUSTOMER1);
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			//logger.info("apliance information is fetched again and name is "+customer1.getName());
			assertNotNull(customer1.getId());
			assertEquals(customer1.getName(),CUSTOMER1);
			assertEquals(customer1.getEmail(),CUSTOMER1EMAIL);
			assertEquals(customer1.getPhone(),PH_NO);
			assertEquals(customer1.getClientId(),"clientid");
            assertEquals(customer1.getGrafanaAuthToken(),"authtoken");
            assertEquals(customer1.getGrafanaOrgId(),"orgid");
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = CustomerNotFoundException.class) // update the non existing customer
	public void testUpdateCustomer2() throws Exception {
		//cleanUp();
		    Customer customer =  new Customer();
		    customer.setId(967678);
		    customerService.updateCustomer(customer);
	}
	
	@Test(priority=5,dependsOnMethods = {"testSaveCustomer1"}) // find all customer
	public void testFindAllCustomer() throws Exception {
		try {
			
			List<Customer> cl= customerService.findAllCustomers();
			
			//logger.info("al size "+cl.size());
			assertNotNull(cl.size());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveCustomer1"}) // get customer by email id
	public void testfindCustomerByEmail1() throws Exception {
		try {
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			//logger.info("customer information is fetched again and name is "+customer1.getName());
			assertNotNull(customer1.getId());
			assertEquals(customer1.getName(), CUSTOMER1);
			assertEquals(customer1.getEmail(),CUSTOMER1EMAIL);
			assertEquals(customer1.getPhone(),PH_NO);
			assertEquals(customer1.getClientId(),"clientid");
            assertEquals(customer1.getGrafanaAuthToken(),"authtoken");
            assertEquals(customer1.getGrafanaOrgId(),"orgid");
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveCustomer1"}) // get  customer by noexisting email id
	public void testfindCustomerByEmail2() throws Exception {
		
		try {
			Customer customer1 = customerService.findCustomerByEmail("abcd@gmail.com");
			assertNull(customer1);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveCustomer1"}) // get customer by email id
	public void testfindCustomerByName1() throws Exception {
		try {
			Customer customer1 = customerService.findCustomerByName(CUSTOMER1);
			
			//logger.info("customer information is fetched again and name is "+customer1.getName());
			assertNotNull(customer1.getId());
			assertEquals(customer1.getName(),CUSTOMER1);
			assertEquals(customer1.getEmail(),CUSTOMER1EMAIL);
			assertEquals(customer1.getPhone(),PH_NO);
			assertEquals(customer1.getClientId(),"clientid");
            assertEquals(customer1.getGrafanaAuthToken(),"authtoken");
            assertEquals(customer1.getGrafanaOrgId(),"orgid");
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveCustomer1"}) // get  customer by noexisting email id
	public void testfindCustomerByName2() throws Exception {
		
		try {
			Customer customer1 = customerService.findCustomerByName("abcd");
			assertNull(customer1);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveCustomer1"}) // get customer by email id
	public void testfindCustomerByUser1() throws Exception {
		try {
			Customer customer1 = customerService.findCustomerByName(CUSTOMER1);
			
			//logger.info("customer information is fetched again and name is "+customer1.getName());
			User user = userService.findUserByEmail(CUSTOMER1EMAIL);
			Customer customer2 = customerService.findCustomer(user);
			assertNotNull(customer2.getId());
			assertEquals(customer2.getName(),CUSTOMER1);
			assertEquals(customer2.getEmail(),CUSTOMER1EMAIL);
			assertEquals(customer2.getPhone(),PH_NO);
			assertEquals(customer2.getClientId(),"clientid");
            assertEquals(customer2.getGrafanaAuthToken(),"authtoken");
            assertEquals(customer2.getGrafanaOrgId(),"orgid");
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveCustomer1"}) // get  customer by noexisting email id
	public void testfindCustomerByUser2() throws Exception {
		try {
			User user = new User();
			user.setEmail("abcd@gmail.com");
			Customer customer2 = customerService.findCustomer(user);
			assertNull(customer2);
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	
	
	@Test(priority=12,dependsOnMethods = {"testSaveCustomer1"}) // merge a existing customer
	public void testMergeCustomer1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			customerService.mergeCustomer(customer1);
			
			//logger.info("customer information is fetched again and name is "+customer1.getName());
			assertNotNull(customer1.getId());
			assertEquals(customer1.getName(),CUSTOMER1);
			assertEquals(customer1.getEmail(),CUSTOMER1EMAIL);
			assertEquals(customer1.getPhone(),PH_NO);
			assertEquals(customer1.getClientId(),"clientid");
            assertEquals(customer1.getGrafanaAuthToken(),"authtoken");
            assertEquals(customer1.getGrafanaOrgId(),"orgid");
			
			assert true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
   @Test(priority=13,expectedExceptions = CustomerNotFoundException.class) // merge the non existing customer
	public void testMergeCustomer2() throws Exception {
		//cleanUp();
		    Customer customer =  new Customer();
            customer.setId(898989);
            customerService.mergeCustomer(customer);
	}
   
   @Test(priority=14,dependsOnMethods = {"testSaveCustomer1"}) // get  customer by id
	public void testFindustomerById1() throws Exception {
		try {
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			//logger.info("customer information is fetched again and name is "+customer1.getName());
			Customer customer2 = customerService.findById(customer1.getId());
			
			//logger.info("customer information is fetched again and name is "+customer2.getName());
			assertNotNull(customer2.getId());
			assertEquals(customer2.getName(),CUSTOMER1);
			assertEquals(customer2.getEmail(),CUSTOMER1EMAIL);
			assertEquals(customer2.getPhone(),PH_NO);
			assertEquals(customer2.getClientId(),"clientid");
            assertEquals(customer2.getGrafanaAuthToken(),"authtoken");
            assertEquals(customer2.getGrafanaOrgId(),"orgid");
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=15,dependsOnMethods = {"testSaveCustomer1"},expectedExceptions=CustomerNotFoundException.class) // get customer by non existing id
	public void testFindCustomerById2() throws Exception {
		
			Customer customer1 = customerService.findById(89898989);
			
	}
	
	@Test(priority=16,dependsOnMethods = {"testSaveCustomer1"}) // delete customer By Id
	public void testDeleteCustomerById1() throws Exception {
		try {
			
            Customer customer = new Customer("customername", "customeremila@gmail.com", "90900998"); 
			customerService.saveCustomer(customer);
			
			Customer customer1 = customerService.findCustomerByEmail("customeremila@gmail.com");
			
			//logger.info("customer information is fetched again and name is "+customer1.getName());
			customerService.deleteCustomerById(customer1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=17,dependsOnMethods = {"testSaveCustomer1"},expectedExceptions=CustomerNotFoundException.class) // delete non existimg customer
	public void testDeleteCustomerById2() throws Exception {
		
			customerService.deleteCustomerById(988989);
			
	}*/

	


}
