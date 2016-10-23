/**
 * 
 */
package com.nvidia.cosmos.cloud.factory;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.config.HibernateConfiguration;
import com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.dashboard.service.DashBoardService;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
import com.nvidia.cosmos.cloud.services.eula.service.EulaService;
import com.nvidia.cosmos.cloud.services.job.service.JobService;
import com.nvidia.cosmos.cloud.services.node.service.NodeService;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
import com.nvidia.cosmos.cloud.services.token.service.TokenService;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;

/**
 * @author pbatta
 *
 */
public class ServicesFactory {

	private static ApplicationContext _applicationContex;
	private static Logger logger = LoggerFactory.getLogger(ServicesFactory.class);

	/**
	 * 
	 */
	private static ServicesFactory _instance;

	/**
	 * 
	 */
	private ServicesFactory() {
		_applicationContex = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
		try{
			RoleService roleService = getRoleService();
			ArrayList<String> roles = new ArrayList<String>();
			roles.add(ServicesConstants.SUPER_ADMIN);
			roles.add(ServicesConstants.CUSTOMER_ADMIN);
			roles.add(ServicesConstants.CUSTOMER_USER);
			for(String roleName : roles){
				Role role = null;
				if(roleService.findRoleByName(roleName) == null){
					role = new Role(roleName, roleName);
					roleService.saveRole(role);
					logger.info("Role is saved with name "+roleName);
				} else {
					logger.debug("Role is already present with name {} " ,roleName);
				}
			}
			CustomerService customerService = getCustomerService();
			Customer customer = new Customer(ServicesConstants.DEFAULT_ADMIN_NAME, ServicesConstants.DEFAULT_ADMIN_EMAIL, ServicesConstants.DEFAULT_ADMIN_PHONE);
			if(customerService.findCustomerByEmail(ServicesConstants.DEFAULT_ADMIN_EMAIL) == null){
				customerService.saveCustomer(customer);
				logger.debug("First customer is created..");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public static ServicesFactory getInstance() {
		if (_instance == null) {
			return _instance = new ServicesFactory();
		} else {
			return _instance;
		}
	}

	/**
	 * @return
	 */
	public UserService getUserService() {
		return (UserService) _applicationContex.getBean(UserService.class);
	}
	/**
	 * @return
	 */
	public CustomerService getCustomerService() {
		return (CustomerService) _applicationContex.getBean(CustomerService.class);
	}
	/**
	 * @return
	 */
	public RoleService getRoleService() {
		return (RoleService) _applicationContex.getBean(RoleService.class);
	}
	
	
	public DashBoardService getDashBoardService() {
		return (DashBoardService) _applicationContex.getBean(DashBoardService.class);
	}
	/**
	 * @return
	 */
	public EulaService getEulaService() {
		return (EulaService) _applicationContex.getBean(EulaService.class);
	}
	/**
	 * @return
	 */
	public NodeService getNodeService() {
		return (NodeService) _applicationContex.getBean(NodeService.class);
	}
	/**
	 * @return
	 */
	public ClusterService getClusterService() {
		return (ClusterService) _applicationContex.getBean(ClusterService.class);
	}
	/**
	 * @return
	 */
	public UserAuthService getUserAuthService() {
		return (UserAuthService) _applicationContex.getBean(UserAuthService.class);
	}
	/**
	 * @return
	 */
	public EntitlementService getEntitlementService() {
		return (EntitlementService) _applicationContex.getBean(EntitlementService.class);
	}
	/**
	 * @return
	 */
	public ApplianceService getApplianceService() {
		return (ApplianceService) _applicationContex.getBean(ApplianceService.class);
	}
	/**
	 * @return
	 */
	public TokenService getTokenService() {
		return (TokenService) _applicationContex.getBean(TokenService.class);
	}
	
	/**
	 * @return
	 */
	public JobService getJobService() {
		return (JobService) _applicationContex.getBean(JobService.class);
	}
}
