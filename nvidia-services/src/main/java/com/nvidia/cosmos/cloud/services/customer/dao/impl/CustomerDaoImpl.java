package com.nvidia.cosmos.cloud.services.customer.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.dao.AbstractDao;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.customer.dao.CustomerDao;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.CustomerDao#findById(int)
     */
    public Customer findById(int id) {
        return getByKey(id);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.CustomerDao#saveCustomer(com.nvidia.cosmos.cloud.services.model.Customer)
     */
    public void saveCustomer(Customer customer) {
        persist(customer);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.CustomerDao#deleteCustomerBySsn(java.lang.String)
     */
    public void deleteCustomerById(int id) {
    	Customer customer = new Customer();
    	customer.setId(id);
    	Serializable id1 = new Integer(id);
    	deleteById(Customer.class,id1);
    }
 
    /* (non-Javadoc)
     * @see com.nvidia.cosmos.cloud.services.dao.CustomerDao#findAllCustomers()
     */
    @SuppressWarnings("unchecked")
    public List<Customer> findAllCustomers() {
        Criteria criteria = createEntityCriteria();
        return (List<Customer>) criteria.list();
    }
 
    
    public Customer findCustomerByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Customer) criteria.uniqueResult();
    }
    
    public Customer findCustomerByEmail(String email) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("email", email));
    	return (Customer) criteria.uniqueResult();
    }

	@Override
	public Customer findCustomer(User user) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("users", "user");
		criteria.add(Restrictions.eq("user.email", user.getEmail()));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		//Query query = getSession().createSQLQuery("select * from NV_CUSTOMER_MASTER cust,NV_USER_MASTER nvuse where cust.id =nvuse.CUSTOMER_ID and nvuse.EMAIL = :EMAIL");
		//query.setString("EMAIL", user.getEmail());
		return (Customer)criteria.uniqueResult();
	}

	@Override
	public void mergeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		merge(customer);
	}
}