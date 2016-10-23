/**
 * 
 */
package com.nvidia.cosmos.cloud.services.customer.dao;

import java.util.List;

import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */
public interface CustomerDao {
 
    /**
     * @param id
     * @return
     */
    Customer findById(int id);
 
    /**
     * @param customers
     */
    void saveCustomer(Customer customer);
     
    /**
     * @param ssn
     */
    void deleteCustomerById(int id);
     
    /**
     * @return
     */
    List<Customer> findAllCustomers();
 
    /**
     * @param name
     * @return
     */
    Customer findCustomerByName(String name);
    /**
     * @param email
     * @return
     */
    Customer findCustomerByEmail(String email);
    
    /**
     * @param user
     * @return
     */
    Customer findCustomer(User user);
    
    public void mergeCustomer(Customer customer);
    
}
