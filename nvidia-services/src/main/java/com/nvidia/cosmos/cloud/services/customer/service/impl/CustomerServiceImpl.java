package com.nvidia.cosmos.cloud.services.customer.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.exceptions.CustomerExistsException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.services.customer.dao.CustomerDao;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.role.dao.RoleDao;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.dao.UserDao;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.util.EncryptionUtil;
 
/**
 * 
 *
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
 
	private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	MessageSource messageSource;
    /**
     * 
     */
    @Autowired
    private CustomerDao dao;
    /**
     * 
     */
    @Autowired
    private UserDao userdao;
    @Autowired
    private RoleDao roledao;
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.customer.service.CustomerService#findById(int)
     */
    public Customer findById(int id) throws CustomerNotFoundException{
    	Customer entity = dao.findById(id);
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
        return entity;
    }      
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.customer.service.CustomerService#saveCustomer(com.nvidia.cosmos.cloud.services.customer.model.Customer)
     */
    public void saveCustomer(Customer customer) throws CustomerExistsException,UserNameExitsException{
    	Customer exists = dao.findCustomerByEmail(customer.getEmail());
    	if(exists!=null){
    		throw new CustomerExistsException(messageSource.getMessage("customer.exists.email", new String[]{""+customer.getEmail()}, new Locale("en", "US")));
    	}
    	dao.saveCustomer(customer);
    	customer = dao.findCustomerByEmail(customer.getEmail());
    	Role role =null;
    	if(customer.getEmail().equalsIgnoreCase(ServicesConstants.DEFAULT_ADMIN_EMAIL)){
    		role = roledao.findRoleByName(ServicesConstants.SUPER_ADMIN);
    	}else{
    		role = roledao.findRoleByName(ServicesConstants.CUSTOMER_ADMIN);
    	}
    	User user = new User(customer.getName(), customer.getEmail(), customer.getPhone(), role, customer);
    	if(customer.getEmail().equals(ServicesConstants.DEFAULT_ADMIN_EMAIL)){
    		user.setUserName(ServicesConstants.DEFAULT_ADMIN_USER_NAME);
    		try {
    			user.setPassword(EncryptorFactory.getBCryptEncryptor().encode(ServicesConstants.WE1COME));
    			user.setgId(1L);
    	    	user.setuId(1L);
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		user.setStatus(UserStatusEnum.ACTIVE.toString());
    	}
    	
    	userdao.mergeUser(user);
    	logger.info("Customer is saved successfully..");
    }
 
    
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.customer.service.CustomerService#updateCustomer(com.nvidia.cosmos.cloud.services.customer.model.Customer)
     */
    public void updateCustomer(Customer customer)  throws CustomerNotFoundException{
    	Customer entity = dao.findById(customer.getId());
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+customer.getId()}, new Locale("en", "US")));
    	} else {
            entity.setName(customer.getName());
            entity.setEmail(customer.getEmail());
            entity.setPhone(customer.getPhone());
            entity.setClientId(customer.getClientId());
            entity.setUpdatedDate(new Date());
            entity.setGrafanaAuthToken(customer.getGrafanaAuthToken());
            entity.setGrafanaOrgId(customer.getGrafanaOrgId());
            
            User user = userdao.findUserByEmail(customer.getEmail());
            user.setName(customer.getName());
            user.setEmail(customer.getEmail());
            user.setPhone(customer.getPhone());
            user.setUpdatedDate(new Date());
            userdao.saveUser(user);
            logger.info("Customer information is updated..");
        }
        dao.saveCustomer(entity);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.customer.service.CustomerService#deleteCustomerById(int)
     */
    public void deleteCustomerById(int id) throws CustomerNotFoundException{
    	Customer entity = dao.findById(id);
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+id}, new Locale("en", "US")));
    	}
    	List<User> users = userdao.findAllUsers(id);
    	for(User user : users){
    		userdao.deleteUserById(user.getId());
    	}
    	logger.info("All Users are cleared..");
        dao.deleteCustomerById(id);
    }
     
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.customer.service.CustomerService#findAllCustomers()
     */
    public List<Customer> findAllCustomers() {
        return dao.findAllCustomers();
    }
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.customer.service.CustomerService#findCustomerByEmail(java.lang.String)
     */
    public Customer findCustomerByEmail(String email){
    	Customer entity = dao.findCustomerByEmail(email);
//    	if(entity==null){
//    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.email", new String[]{email}, new Locale("en", "US")));
//    	}
    	return entity;
    }

	@Override
	public Customer findCustomerByName(String name) throws CustomerNotFoundException {
		Customer entity = dao.findCustomerByName(name);
		
		return entity;
	}

	@Override
	public Customer findCustomer(User user) throws CustomerNotFoundException {
		Customer entity = dao.findCustomer(user);
		return entity;
	}

	@Override
	public void mergeCustomer(Customer customer) throws CustomerNotFoundException {
		if(dao.findById(customer.getId())==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+customer.getId()}, new Locale("en", "US")));
    	} else {
    		dao.mergeCustomer(customer);
        }
	}
 
	public void deleteCustomer(Customer customer) throws CustomerNotFoundException{
    	Customer entity = dao.findById(customer.getId());
    	if(entity==null){
    		throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.id", new String[]{""+customer.getId()}, new Locale("en", "US")));
    	}
    	dao.deleteCustomerById(entity.getId());
    }

}