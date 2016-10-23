/**
 * 
 */
package com.nvidia.cosmos.services.token.service.impl;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.TokenExistsException;
import com.nvidia.cosmos.cloud.exceptions.TokenNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.token.model.Token;
import com.nvidia.cosmos.cloud.services.token.service.TokenService;

/**
 * @author bprasad
 * 
 */
@Test
public class TokenServiceImplTest extends Assert {
	//private Logger logger = LoggerFactory.getLogger(TokenServiceImplTest.class);
	
	final static String TOKENNAME = System.getProperty("user.name");
	
	final static String CUSTOMER1 = TOKENNAME + "_customer1";
	final static String CUSTOMER1EMAIL =CUSTOMER1+"@mail.com";
	final static String PH_NO ="8078877899";
	
	final static String TOKEN1KEY =TOKENNAME+"_2323_2323-32";
	
	final static String TOKEN2KEY =TOKENNAME+"_2_2323_2323-32";
	
	TokenService tokenService = null;
	ServicesFactory factory = null;
	CustomerService customerService = null;
	
	@BeforeClass
	public void setUp() throws Exception, Throwable {
		factory = ServicesFactory.getInstance();
		tokenService = factory.getTokenService();
		customerService = factory.getCustomerService();
		cleanUp();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		cleanUp();
	}

	private void cleanUp() throws Exception {
		clean(TOKEN1KEY);
		clean(TOKEN2KEY);
		cleanCustomer(CUSTOMER1EMAIL);
	}
	
	private void clean(String tokenKey) throws Exception {
		Token token = null;
		try {
			token = tokenService.findTokenByKey(tokenKey);
			if(token!=null){
				tokenService.deleteTokenById(token.getId());
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
	
	
	/*@Test(priority =1)
	public void testSaveToken1() throws Exception { // create a token
		//cleanUp();
		try {
			Customer customer = new Customer(CUSTOMER1, CUSTOMER1EMAIL, PH_NO); 
			customerService.saveCustomer(customer);
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
			Token token = new Token(TOKEN1KEY, customer1); 
			
			tokenService.saveToken(token);
			
			//logger.info("Token is created in test " + TOKEN1KEY);
			Token token1 = tokenService.findTokenByKey(TOKEN1KEY);
			
			//logger.info("Token information is fetched again and name is "+token1.getKey());
			assertNotNull(token1.getId());
			assertEquals(token1.getKey(), TOKEN1KEY);
			
			assert true;
			
            Token token2 = new Token(TOKEN2KEY, customer1); 
			
			tokenService.saveToken(token2);
			
			//logger.info("Token is created in test " + TOKEN2KEY);
			Token token12 = tokenService.findTokenByKey(TOKEN2KEY);
			
			//logger.info("Token information is fetched again and name is "+token12.getKey());
			assertNotNull(token12.getId());
			assertEquals(token12.getKey(), TOKEN2KEY);
			
			assert true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=2,expectedExceptions = TokenExistsException.class,dependsOnMethods = {"testSaveToken1"}) // create a existing token
	public void testSaveToken2() throws Exception {
		    Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			
		    Token token = new Token(TOKEN1KEY, customer1); 
			tokenService.saveToken(token);
	
	}
	
	@Test(priority=3,dependsOnMethods = {"testSaveToken1"}) // update the existing token
	public void testUpdateToken1() throws Exception {
		//cleanUp();
		try{
            Token token =tokenService.findTokenByKey(TOKEN1KEY);
			
           token.setKey(TOKEN1KEY);
          tokenService.updateToken(token);
			
            
            //logger.info("toekn is updated :: " + TOKEN1KEY);
			
			Token token1 = tokenService.findTokenByKey(TOKEN1KEY);
			
			//logger.info("apliance information is fetched again and name is "+token1.getKey());
			assertNotNull(token1.getId());
			assertEquals(token1.getKey(),TOKEN1KEY);
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=4,expectedExceptions = TokenNotFoundException.class) // update the non existing token
	public void testUpdateToken2() throws Exception {
		//cleanUp();
		    Token token =  new Token();
		    token.setId(111111);
            tokenService.updateToken(token);
	}
	
	@Test(priority=5,dependsOnMethods = {"testSaveToken1"}) // find all token
	public void testFindAllToken() throws Exception {
		try {
			
			List<Token> tl= tokenService.findAllTokens();
			
			//logger.info("tl size "+tl.size());
			
			assertNotNull(tl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=6,dependsOnMethods = {"testSaveToken1"}) // get token by key
	public void testfindTokenByKey1() throws Exception {
		try {
			Token token1 = tokenService.findTokenByKey(TOKEN1KEY);
			
			//logger.info("token information is fetched again and name is "+token1.getKey());
			assertNotNull(token1.getId());
			assertEquals(token1.getKey(),TOKEN1KEY);
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=7,dependsOnMethods = {"testSaveToken1"},expectedExceptions=TokenNotFoundException.class) // get  token by noexisting service key
	public void testfindTokenByKey2() throws Exception {
		
			Token token1 = tokenService.findTokenByKey("hdjhcj");
			
	}
	
	@Test(priority=8,dependsOnMethods = {"testSaveToken1"}) // get all tokens for by customer id
	public void testfindAllTokensByCustId1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
			List<Token> tl= tokenService.findAllTokens(customer1.getId());
			
			//logger.info("tl with customer id"+tl.size());
			
			assertNotNull(tl.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=9,dependsOnMethods = {"testSaveToken1"},expectedExceptions=CustomerNotFoundException.class) // get all token for non existing customer
	public void testfindAllToeknsByCustId2() throws Exception {
		
		tokenService.findAllTokens(8989898);
	}
	
	@Test(priority=10,dependsOnMethods = {"testSaveToken1"}) // get  token by id
	public void testFindTokenById1() throws Exception {
		try {
			Token token1 = tokenService.findTokenByKey(TOKEN1KEY);
			
			//logger.info("token information is fetched again and name is "+token1.getKey());
			Token token2 = tokenService.findById(token1.getId());
			
			//logger.info("token information is fetched again and name is "+token2.getKey());
			assertNotNull(token2.getId());
			assertEquals(token2.getKey(), TOKEN1KEY);
			
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=11,dependsOnMethods = {"testSaveToken1"},expectedExceptions=TokenNotFoundException.class) // get token by non existing id
	public void testFindTokenById2() throws Exception {
		
			Token token1 =tokenService.findById(89898989);
			
	}
	
	@Test(priority=12,dependsOnMethods = {"testSaveToken1"}) // delete token By Id
	public void testDeleteTokenById1() throws Exception {
		try {
			
			Customer customer1 = customerService.findCustomerByEmail(CUSTOMER1EMAIL);
            Token token = new Token("tokenkey", customer1); 
			
			tokenService.saveToken(token);
			Token token1 = tokenService.findTokenByKey("tokenkey");
			
			//logger.info("token information is fetched again and name is "+token1.getKey());
			tokenService.deleteTokenById(token1.getId());
			
			assert true;
			
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}
	
	@Test(priority=13,dependsOnMethods = {"testSaveToken1"},expectedExceptions=TokenNotFoundException.class) // delete non existimg token
	public void testDeleteTokenById2() throws Exception {
		
			tokenService.deleteTokenById(87899);
			
	}*/



}
