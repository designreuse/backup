package com.nvidia.cosmos.services.userauth.service.impl;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.UserAuthExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;

public class UserAuthServiceImplTest extends Assert {
//	private Logger logger = LoggerFactory.getLogger(UserAuthServiceImplTest.class);
	
	final static String USERNAME = System.getProperty("user.name");
	final static String ROLE1 = USERNAME + "_role1";
	
	final static String USER1 = USERNAME + "_user1";
	final static String USER1EMAIL =USER1+"@mail.com";
	
	final static String USER2 = USERNAME + "_user2";
	final static String USER2EMAIL =USER2+"@mail.com";
	
	final static String TOKEN1 = "token1";
	final static String TOKEN2 ="token2";
	
	final static String EXTTOKEN1 = "exttoken1";
	final static String EXTTOKEN2 ="exttoken2";
	
	
	ServicesFactory factory = null;
	UserAuthService userAuthService = null;
	
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		userAuthService = factory.getUserAuthService();
		
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(TOKEN1);
		clean(TOKEN2);
		
	}
	
	private void clean(String token) throws Exception {
		UserAuth userAuth = null;
		try {
			userAuth = userAuthService.findUserAuthByAuthToken(token);
			if(userAuth!=null){
				userAuthService.deleteUserAuthById(userAuth.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*@Test(priority =1)
	public void testSaveUserAuth1() throws Exception{ // create user authentication
		
		try{
		UserAuth userAuth = new UserAuth();
		userAuth.setName(USER1);
		userAuth.setEmail(USER1EMAIL);
		userAuth.setAuthToken(TOKEN1);
		userAuth.setExtToken(EXTTOKEN1);
		userAuth.setRoleName(ROLE1);
		userAuth.setCreatedDate(new Date());
		userAuthService.saveUserAuth(userAuth);
		
		
		//logger.info("user authentication is created :: " + USER1);
		
		UserAuth userAuth1 = userAuthService.findUserAuthByAuthToken(TOKEN1);
		
		//logger.info("User authentication information is fetched again and name is "+userAuth1.getName());
		assertNotNull(userAuth1.getId());
		assertEquals(userAuth1.getName(), USER1);
		assertEquals(userAuth1.getEmail(), USER1EMAIL);
		assertEquals(userAuth1.getAuthToken(), TOKEN1);
		assertEquals(userAuth1.getExtToken(), EXTTOKEN1);
		assertEquals(userAuth1.getRoleName(), ROLE1);
		
		assert true;
		
		UserAuth userAuth2 = new UserAuth();
		userAuth2.setName(USER2);
		userAuth2.setEmail(USER2EMAIL);
		userAuth2.setAuthToken(TOKEN2);
		userAuth2.setExtToken(EXTTOKEN2);
		userAuth2.setRoleName(ROLE1);
		userAuth2.setCreatedDate(new Date());
		userAuthService.saveUserAuth(userAuth2);
		
		
		//logger.info("user authentication is created :: " + USER2);
		
		UserAuth userAuth12 = userAuthService.findUserAuthByAuthToken(TOKEN2);
		
		//logger.info("User authentication information is fetched again and name is "+userAuth12.getName());
		assertNotNull(userAuth12.getId());
		assertEquals(userAuth12.getName(), USER2);
		assertEquals(userAuth12.getEmail(), USER2EMAIL);
		assertEquals(userAuth12.getAuthToken(), TOKEN2);
		assertEquals(userAuth12.getExtToken(), EXTTOKEN2);
		assertEquals(userAuth12.getRoleName(), ROLE1);
		
		assert true;
		
		
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
		
	}
	
	@Test(priority=2,expectedExceptions = UserAuthExistsException.class,dependsOnMethods = {"testSaveUserAuth1"}) // create a existing user auth
	public void testSaveUserAuth2() throws Exception {

		UserAuth userAuth = new UserAuth();
		userAuth.setName(USER1);
		userAuth.setEmail(USER1EMAIL);
		userAuth.setAuthToken(TOKEN1);
		userAuth.setExtToken(EXTTOKEN1);
		userAuth.setRoleName(ROLE1);
		userAuth.setCreatedDate(new Date());
		userAuthService.saveUserAuth(userAuth);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveUserAuth1"}) // update the existing user auth
	public void testUpdateUserAuth1() throws Exception {
		//cleanUp();
		try{
            UserAuth userAuth = userAuthService.findUserAuthByAuthToken(TOKEN1);
			
            userAuth.setName(USER1);
			userAuth.setEmail(USER1EMAIL);
			userAuth.setAuthToken(TOKEN1);
			userAuth.setExtToken(EXTTOKEN1);
			userAuth.setRoleName(ROLE1);
            userAuth.setExtToken(EXTTOKEN1);
            userAuthService.updateUserAuth(userAuth);
			
            
            //logger.info("user auth is updated :: " + TOKEN1);
			
			UserAuth userAuth1 = userAuthService.findUserAuthByAuthToken(TOKEN1);
			
			 //logger.info("user auth information is fetched again and name is "+userAuth1.getName());
			assertNotNull(userAuth1.getId());
			assertEquals(userAuth1.getName(), USER1);
			assertEquals(userAuth1.getEmail(), USER1EMAIL);
			assertEquals(userAuth1.getAuthToken(), TOKEN1);
			assertEquals(userAuth1.getExtToken(), EXTTOKEN1);
			assertEquals(userAuth1.getRoleName(), ROLE1);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = UserAuthNotFoundException.class) // update the non existing user auth
	public void testUpdateUserAuth2() throws Exception {
		//cleanUp();
		    UserAuth userAuth =  new UserAuth();
		    userAuth.setId(111111);
		    userAuthService.updateUserAuth(userAuth);
	}
	
	@Test(priority=5,dependsOnMethods = {"testSaveUserAuth1"}) // find all userauth
	public void testFindAllUserAuth() throws Exception {
		try {
			
			List<UserAuth> ul=  userAuthService.findAllUserAuths();
			
			//logger.info("al "+ul.size());
			
			assertNotNull(ul.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveUserAuth1"}) // get user auth by token
	public void testfindUserAuthByAuthToken1() throws Exception {
		try {
			UserAuth userAuth1 = userAuthService.findUserAuthByAuthToken(TOKEN1);
			
			//logger.info("user auth information is fetched again and name is "+userAuth1.getName());
			assertNotNull(userAuth1.getId());
			assertEquals(userAuth1.getName(), USER1);
			assertEquals(userAuth1.getEmail(), USER1EMAIL);
			assertEquals(userAuth1.getAuthToken(), TOKEN1);
			assertEquals(userAuth1.getExtToken(), EXTTOKEN1);
			assertEquals(userAuth1.getRoleName(), ROLE1);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveUserAuth1"},expectedExceptions=UserAuthNotFoundException.class) // get  user auth by noexisting token
	public void testfindUserAuthByAuthToken2() throws Exception {
		
		UserAuth userAuth1 = userAuthService.findUserAuthByAuthToken("jhjhjhj");
			
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveUserAuth1"}) // get user auth by token
	public void testfindUserAuthByExtToken1() throws Exception {
		try {
			UserAuth userAuth1 = userAuthService.findUserAuthByExtToken(EXTTOKEN1);
			
			//logger.info("user auth information is fetched again and name is "+userAuth1.getName());
			assertNotNull(userAuth1.getId());
			assertEquals(userAuth1.getName(), USER1);
			assertEquals(userAuth1.getEmail(), USER1EMAIL);
			assertEquals(userAuth1.getAuthToken(), TOKEN1);
			assertEquals(userAuth1.getExtToken(), EXTTOKEN1);
			assertEquals(userAuth1.getRoleName(), ROLE1);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveUserAuth1"},expectedExceptions=UserAuthNotFoundException.class) // get  user auth by noexisting token
	public void testfindUserAuthByExtToken2() throws Exception {
		
		UserAuth userAuth1 = userAuthService.findUserAuthByExtToken("jkjkjkj");
			
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveUserAuth1"}) // get  user auth by id
	public void testFindUserAuthById1() throws Exception {
		try {
			UserAuth userAuth1 = userAuthService.findUserAuthByAuthToken(TOKEN1);
			
			//logger.info("user auth information is fetched again and name is "+userAuth1.getName());
			UserAuth userAuth2 = userAuthService.findById(userAuth1.getId());
			
			//logger.info("user auth information is fetched again and name is "+userAuth2.getName());
			assertNotNull(userAuth2.getId());
			assertEquals(userAuth2.getName(), USER1);
			assertEquals(userAuth2.getEmail(), USER1EMAIL);
			assertEquals(userAuth2.getAuthToken(), TOKEN1);
			assertEquals(userAuth2.getExtToken(), EXTTOKEN1);
			assertEquals(userAuth2.getRoleName(), ROLE1);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveUserAuth1"},expectedExceptions=UserAuthNotFoundException.class) // get token by non existing id
	public void testFindUserAuthById2() throws Exception {
		
			UserAuth userAuth1 = userAuthService.findById(89898989);
			
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveUserAuth1"}) // delete user auth By Id
	public void testDeleteUserAuthById1() throws Exception {
		try {
			
			UserAuth userAuth = new UserAuth();
			userAuth.setName("name");
			userAuth.setEmail("email@gmail.com");
			userAuth.setAuthToken("token");
			userAuth.setExtToken("ext_token");
			userAuth.setRoleName("role");
			userAuth.setCreatedDate(new Date());
			userAuthService.saveUserAuth(userAuth); 
			
			UserAuth userAuth1 = userAuthService.findUserAuthByAuthToken("token");
			
			//logger.info("user auth information is fetched again and name is "+userAuth1.getName());
			userAuthService.deleteUserAuthById(userAuth1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveUserAuth1"},expectedExceptions=UserAuthNotFoundException.class) // delete non existimg user auth
	public void testDeleteUserAuthById2() throws Exception {
		
			userAuthService.deleteUserAuthById(9898989);
			
	}*/
	
	
		
	
	

}

