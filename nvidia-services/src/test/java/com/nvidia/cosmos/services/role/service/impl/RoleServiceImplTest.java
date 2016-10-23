/**
 * 
 */
package com.nvidia.cosmos.services.role.service.impl;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.RoleExistsException;
import com.nvidia.cosmos.cloud.exceptions.RoleNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;

/**
 * @author bprasad
 * 
 */
@Test
public class RoleServiceImplTest extends Assert {
	//private Logger logger = LoggerFactory.getLogger(RoleServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	final static String ROLE1 = USERNAME + "_role1";
	final static String DESCRIPION = "description";
	
	
	final static String ROLE2 = USERNAME + "_role2";
	
	RoleService roleService = null;
	ServicesFactory factory = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		roleService = factory.getRoleService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		
		clean(ROLE1);
		clean(ROLE2);
		
	}
	
	private void clean(String roleName) throws Exception {
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

	
	
	/*@Test(priority=1)
	public void testSaveRole() throws Exception { // creeate new role
		//cleanUp();
		try {
			//Role role = new Role(ROLE1, DESCRIPION); 
			Role role = new Role();
			role.setName(ROLE1);
			role.setDescription(DESCRIPION);
			role.setCreatedDate(new Date());
			roleService.saveRole(role);
			
			//logger.info("Role is created in test " + ROLE1);
			Role role1 = roleService.findRoleByName(ROLE1);
			
			//logger.info("Role information is fetched again and name is "+role1.getName());
			assertNotNull(role1.getId());
			assertEquals(role1.getName(), ROLE1);
			assertEquals(role1.getDescription(), DESCRIPION);
			
			assert true;
			
            Role role2 = new Role(ROLE2, DESCRIPION); 
			
			roleService.saveRole(role2);
			
			//logger.info("Role is created in test " + ROLE2);
			Role role12 = roleService.findRoleByName(ROLE2);
			
			//logger.info("Role information is fetched again and name is "+role2.getName());
			assertNotNull(role2.getId());
			assertEquals(role2.getName(), ROLE2);
			assertEquals(role2.getDescription(), DESCRIPION);
			
			assert true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	
	@Test(priority=2,expectedExceptions = RoleExistsException.class,dependsOnMethods = {"testSaveRole"}) // fails bcs throwing NoSuchMessageException 
	public void testSaveRole1() throws Exception {// create existing role
		   Role role = new Role(ROLE2, DESCRIPION); 
			
			roleService.saveRole(role);
			
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveRole"})
	public void testUpdateRole1() throws Exception { // update existing role
		//cleanUp();
		try {
			Role role = roleService.findRoleByName(ROLE1);
		    
		    //logger.info("Role is created in test " + ROLE1);
            role.setName(ROLE1);
			role.setDescription(DESCRIPION);
			roleService.updateRole(role);
			
			Role role1 = roleService.findRoleByName(ROLE1);
			
			//logger.info("Role information is fetched again and name is "+role1.getName());
			
			assertNotNull(role1.getId());
			assertEquals(role1.getName(), ROLE1);
			assertEquals(role1.getDescription(), DESCRIPION);
			
			assert true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = RoleNotFoundException.class) // update the non existing role
	public void testUpdateRole2() throws Exception {
		
		    Role role =  new Role();
            role.setId(111111);
            roleService.updateRole(role);
	}
	
	@Test(priority=5,dependsOnMethods = {"testSaveRole"}) // find all roles
	public void testFindAllRoles() throws Exception {
		try {
			
			List<Role> rl= roleService.findAllRoles();
			assertNotNull(rl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveRole"}) // get role by name
	public void testFindRoleByName1() throws Exception {
		try {
			Role role1 = roleService.findRoleByName(ROLE1);
			
			//logger.info("Role information is fetched again and name is "+role1.getName());
			assertNotNull(role1.getId());
			assertEquals(role1.getName(), ROLE1);
			assertEquals(role1.getDescription(), DESCRIPION);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveRole"}) // get  role by noexisting name 
	public void testFindRoleByName2() throws Exception {
		try {
			Role role1 = roleService.findRoleByName("abcd");
			assertNull(role1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
			
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveRole"}) // get role by id
	public void testFindRoleById1() throws Exception {
		try {
			Role role1 = roleService.findRoleByName(ROLE1);
			
			//logger.info("role information is fetched again and name is "+role1.getName());
			Role role2 = roleService.findById(role1.getId());
			
			//logger.info("role information is fetched again and name is "+role2.getName());
			assertNotNull(role2.getId());
			assertEquals(role2.getName(), ROLE1);
			assertEquals(role2.getDescription(), DESCRIPION);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveRole"},expectedExceptions=RoleNotFoundException.class) // get  role by noexisting id 
	public void testFindRoleById2() throws Exception {
		
			Role role1 = roleService.findById(6768787);
			
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveRole"}) // delete role By Id
	public void testDeleteRoleById1() throws Exception {
		try {
			
			Role role = new Role("role","description");
			roleService.saveRole(role);
			
			Role role1 = roleService.findRoleByName(role.getName());
			
			//logger.info("role information is fetched again and name is "+role1.getName());
			roleService.deleteRoleById(role1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveRole"},expectedExceptions=RoleNotFoundException.class) // delete non existing role
	public void testDeleteRoleById2() throws Exception {
		
			roleService.deleteRoleById(23232323);
			
	}*/

	


}
