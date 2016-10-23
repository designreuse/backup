package com.nvidia.cosmos.cloud.services.customer.service;

import java.util.List;

import com.nvidia.cosmos.cloud.exceptions.CustomerExistsException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */
public interface CustomerService {
	 
    /**
     * @param id
     * @return
     * @throws CustomerNotFoundException
     */
    Customer findById(int id) throws CustomerNotFoundException;
    /**
     * @param customer
     * @throws CustomerExistsException
     */
    void saveCustomer(Customer customer) throws CustomerExistsException,UserNameExitsException;
    /**
     * @param customer
     * @throws CustomerNotFoundException
     */
    void updateCustomer(Customer customer) throws CustomerNotFoundException;
    /**
     * @param id
     * @throws CustomerNotFoundException
     */
    void deleteCustomerById(int id) throws CustomerNotFoundException;
    /**
     * @return
     */
    List<Customer> findAllCustomers(); 
    /**
     * @param email
     * @return
     * @throws CustomerNotFoundException
     */
    public Customer findCustomerByEmail(String email) throws CustomerNotFoundException;
     
    /**
     * @param name
     * @return
     * @throws CustomerNotFoundException
     */
    public Customer findCustomerByName(String name) throws CustomerNotFoundException;
    
    /**
     * @param user
     * @return
     * @throws CustomerNotFoundException
     */
    public Customer findCustomer(User user) throws CustomerNotFoundException;
    
    public void mergeCustomer(Customer customer)  throws CustomerNotFoundException;
    
   public void deleteCustomer(Customer customer) throws CustomerNotFoundException;

}
