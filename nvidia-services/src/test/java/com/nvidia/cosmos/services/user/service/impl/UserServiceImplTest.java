/**
 * 
 */
package com.nvidia.cosmos.services.user.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

/**
 * @author bprasad
 * 
 */
@Test
public class UserServiceImplTest extends Assert {
	private Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	final static String ROLE1 = USERNAME + "_role1";
	final static String DESCRIPTION = "description";
	
	
	final static String CUSTOMER1 = USERNAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO ="9999912345";
	
	
	final static String USER1 = USERNAME + "_user1";
	final static String USER1EMAIL =USER1+"@mail.com";
	
	final static String USER2 = USERNAME + "_user2";
	final static String USER2EMAIL =USER2+"@mail.com";
	
	UserService userService = null;
	ServicesFactory factory = null;
	RoleService roleService = null;
	CustomerService customerService = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		userService = factory.getUserService();
		roleService = factory.getRoleService();
		customerService = factory.getCustomerService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(USER1EMAIL);
		clean(USER2EMAIL);
		cleanRole(ROLE1);
		cleanCustomer(CUSTOMER1EMAIL);
	}
	
	private void clean(String userEmail) throws Exception {
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
	private void cleanRole(String roleName) throws Exception {
		Role role = null;

		try {
			role = roleService.findRoleByName(roleName);
			if(role!=null){
				roleService.deleteRoleById(role.getId());
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
	
	
	/*@Test(priority=1) // testing save user method to create a new user
	public void testSaveUser1() throws Exception {
	//	cleanup();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			customerService.saveCustomer(customer);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Role role = new Role(ROLE1, DESCRIPTION); 
			roleService.saveRole(role);
			Role role1 = roleService.findRoleByName(ROLE1);
			
			User user = new User();
			user.setName(USER1);
			user.setEmail(USER1EMAIL);
			user.setPhone(PH_NO);
			user.setRole(role1);
			user.setCustomer(customer1);
			user.setUserName("userName1");
			user.setCreatedDate(new Date());
			user.setStatus(UserStatusEnum.REGISTERED.toString());
			userService.saveUser(user);
			
			
			//logger.info("user is created :: " + USER1);
			
			User user1 = userService.findUserByEmail(USER1EMAIL);
			
			//logger.info("User information is fetched again and name is "+user1.getName());
			assertNotNull(user1.getId());
			assertEquals(user1.getName(), USER1);
			assertEquals(user1.getEmail(), USER1EMAIL);
			assertEquals(user1.getPhone(), PH_NO);
			//assertEquals(user1.getRole(), role1);
			
			
			
			assert true;
			
			User user2 = new User();
			user2.setName(USER2);
			user2.setEmail(USER2EMAIL);
			user2.setPhone(PH_NO);
			user2.setRole(role1);
			user2.setCustomer(customer1);
			user2.setUserName(customer1.getName());
			user2.setCreatedDate(new Date());
			user2.setStatus(UserStatusEnum.REGISTERED.toString());
			userService.saveUser(user2);
			
			//logger.info("User information is fetched again and name is "+user2.getName());
			User user12 = userService.findUserByEmail(USER2EMAIL);
			
			//logger.info("User information is fetched again and name is "+user12.getName());
			assertNotNull(user12.getId());
			assertEquals(user12.getName(), USER2);
			assertEquals(user12.getEmail(), USER2EMAIL);
			assertEquals(user12.getPhone(), PH_NO);
			
			assert true;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = UserExistsException.class,dependsOnMethods = {"testSaveUser1"}) // testing save user method to create a existing user
	public void testSaveUser2() throws Exception {
		//cleanUp();
		
            Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			Role role1 = roleService.findRoleByName(ROLE1);
			
			User user = new User();
			user.setName(USER1);
			user.setEmail(USER1EMAIL);
			user.setPhone(PH_NO);
			user.setRole(role1);
			user.setCustomer(customer1);
			user.setCreatedDate(new Date());
			user.setStatus(UserStatusEnum.REGISTERED.toString());
			userService.saveUser(user);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveUser1"}) // update the existing user
	public void testUpdateUser1() throws Exception {
		//cleanUp();
		try{
            User user = userService.findUserByEmail(USER1EMAIL);
			
			user.setName(USER1);
			user.setEmail(USER1EMAIL);
			user.setPhone(PH_NO);
			//user.setPassword("abcd");
			try {
				logger.info("Generating password for ["+user.getPassword()+"]");
				user.setPassword(EncryptorFactory.getBCryptEncryptor().encode("abcd"));
				} catch (Exception e) {
				logger.error("Error while encoding password",e);
			}
			user.setStatus(UserStatusEnum.REGISTERED.toString());
			user.setUpdatedDate(new Date());
			user.setLastLogin(user.getLastLogin());
			user.setqAuthToken(user.getqAuthToken());
			user.setUserName(user.getUserName());
			userService.updateUser(user);
			
            //logger.info("user is created :: " + USER1);
			
			User user1 = userService.findUserByEmail(user.getEmail());
			
			//logger.info("User information is fetched again and name is "+user1.getName());
			assertNotNull(user1.getId());
			assertEquals(user1.getName(), USER1);
			assertEquals(user1.getEmail(), USER1EMAIL);
			assertEquals(user1.getPhone(), PH_NO);
			
			assert true;
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = UserNotFoundException.class) // update the non existing user
	public void testUpdateUser2() throws Exception {
		//cleanUp();
		    User user =  new User();
            user.setId(111111L);
            userService.updateUser(user);
	}
	
	@Test(priority=5,expectedExceptions = UserExistsException.class,dependsOnMethods = {"testSaveUser1"}) // update the existing user which has user name same as customer name
	public void testUpdateUser3() throws Exception {
		//cleanUp();
		User user = userService.findUserByEmail(USER2EMAIL);
		
	    User user1 = userService.findUserByEmail(USER2EMAIL);
	    userService.updateUser(user1);
	}
	@Test(priority=6,expectedExceptions = UserNameExitsException.class,dependsOnMethods = {"testSaveUser1"}) // update the existing user which has user name same as customer name
	public void testUpdateUser4() throws Exception {
		//cleanUp();
		
	    User user2 = userService.findUserByEmail(USER2EMAIL);
	    user2.setUserName("userName1");
	    userService.updateUser(user2);
	}
	
	@Test(priority=7,expectedExceptions = UserNotFoundException.class) // merge the non existing user
	public void testMergeUser1() throws Exception {
		//cleanUp();
		    User user =  new User();
            user.setId(111111L);
            userService.mergeUser(user);
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveUser1"}) // merge a existing user
	public void testMergeUser2() throws Exception {
		try {
			
			User user1 = userService.findUserByEmail(USER1EMAIL);
			userService.mergeUser(user1);
			
			//logger.info("User information is fetched again and name is "+user1.getName());
			assertNotNull(user1.getId());
			assertEquals(user1.getName(), USER1);
			assertEquals(user1.getEmail(), USER1EMAIL);
			assertEquals(user1.getPhone(), PH_NO);
			
			assert true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveUser1"}) // find all users
	public void testFindAllUser() throws Exception {
		try {
			
			List<User> ul= userService.findAllUsers();
			
			//logger.info("ul size in testFindAllUserByCustID "+ul.size());
			assertNotNull(ul.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveUser1"}) // get all users for by customer id
	public void testFindAllUserByCustID() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			List<User> ul= userService.findAllUsers(customer1.getId());
			
			//logger.info("ul size in testFindAllUserByCustID  "+ul.size());
			assertNotNull(ul.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveUser1"},expectedExceptions=CustomerNotFoundException.class) // get all users for no existing customer
	public void testFindAllUserByCustID2() throws Exception {
		
		userService.findAllUsers(222222);
		
		
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveUser1"}) // get user by email
	public void testFindUserByEmail1() throws Exception {
		try {
			User user1 = userService.findUserByEmail(USER1EMAIL);
			
			//logger.info("User information is fetched again and name is "+user1.getName());
			assertNotNull(user1.getId());
			assertEquals(user1.getName(), USER1);
			assertEquals(user1.getEmail(), USER1EMAIL);
			assertEquals(user1.getPhone(), PH_NO);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveUser1"},expectedExceptions=UserNotFoundException.class) // get  user by noexisting email id
	public void testFindUserByEmail2() throws Exception {
		
			User user1 = userService.findUserByEmail("abcde@gmail.com");
			
	}
	
	@Test(priority=14,dependsOnMethods = {"testSaveUser1"}) // get user by username 
	public void testFindUserByUserName1() throws Exception {
		try {
			User user1 = userService.findUserByEmail(USER2EMAIL);
			
			//logger.info("User information is fetched again and name is  "+user1.getName());
			User user2 = userService.findUserByUserName(user1.getUserName());
			
			//logger.info("User information is fetched again and name is  "+user2.getName());
			
			assertNotNull(user2.getId());
			assertEquals(user2.getName(), USER2);
			assertEquals(user2.getEmail(), USER2EMAIL);
			assertEquals(user2.getPhone(), PH_NO);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=15,dependsOnMethods = {"testSaveUser1"},expectedExceptions=UserNotFoundException.class) // get user by noexisting userName
	public void testFindUserByUserName2() throws Exception {
		
			 userService.findUserByUserName("abcfgh");
			
	}
	
	@Test(priority=16,dependsOnMethods = {"testSaveUser1"}) // get  user by id
	public void testFindUserById1() throws Exception {
		try {
			User user1 = userService.findUserByEmail(USER1EMAIL);
			
			//logger.info("User information is fetched again and name is "+user1.getId());
			User user2 = userService.findById(user1.getId());
			
			//logger.info("User information is fetched again and name is "+user2.getName());
			assertNotNull(user2.getId());
			assertEquals(user2.getName(), USER1);
			assertEquals(user2.getEmail(), USER1EMAIL);
			assertEquals(user2.getPhone(), PH_NO);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=17,dependsOnMethods = {"testSaveUser1"},expectedExceptions=UserNotFoundException.class) // get user by non existing id
	public void testFindUserById2() throws Exception {
		
			User user1 = userService.findById(89898989L);
			
	}
	
	@Test(priority=18,dependsOnMethods = {"testSaveUser1"}) // delete user By Id
	public void testDeleteUserById1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			Role role1 = roleService.findRoleByName(ROLE1);
			
			User user = new User();
			user.setName("ashrita");
			user.setEmail("ashrita.abk@gmail.com");
			user.setPhone("1111111111");
			user.setRole(role1);
			user.setCustomer(customer1);
			user.setCreatedDate(new Date());
			user.setStatus(UserStatusEnum.REGISTERED.toString());
			userService.saveUser(user);
			
			User user1 = userService.findUserByEmail(user.getEmail());
			
			//logger.info("User information is fetched again and name is "+user1.getName());
			userService.deleteUserById(user1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=19,dependsOnMethods = {"testSaveUser1"},expectedExceptions=UserNotFoundException.class) // delete non existimg user
	public void testDeleteUserById2() throws Exception {
		
			userService.deleteUserById(2222222222L);
			
	}*/
	
	
	

}
