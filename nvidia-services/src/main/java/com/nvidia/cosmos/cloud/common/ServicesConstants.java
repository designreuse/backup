package com.nvidia.cosmos.cloud.common;

/**
 * @author pbatta
 *
 */
public interface ServicesConstants {

	/**
	 * 
	 */
	public static final String CUSTOMER_TABLE_NAME = "NV_CUSTOMER_MASTER";
	/**
	 * 
	 */
	public static final String ENTITLEMENT_TABLE_NAME = "NV_ENTITLEMENT_MASTER";
	/**
	 * 
	 */
	public static final String CLUSTER_TABLE_NAME = "NV_CLUSTER_MASTER";
	/**
	 * 
	 */
	public static final String ROLE_TABLE_NAME = "NV_ROLE_MASTER";

	/**
	 * 
	 */
	public static final String USERAUTH_TABLE_NAME = "NV_AUTEHNTICATIONS_MASTER";
	/**
	 * 
	 */
	public static final String USER_TABLE_NAME = "NV_USER_MASTER";
	/**
	 * 
	 */
	public static final String EULA_TABLE_NAME = "NV_EULA_MASTER";
	/**
	 * 
	 */
	public static final String JOB_TABLE_NAME = "NV_JOB_MASTER";
	/**
	 * 
	 */
	public static final String APPLIANCE_TABLE_NAME = "NV_APPLIANCE_MASTER";
	/**
	 * 
	 */
	public static final String TOKEN_TABLE_NAME = "NV_TOKEN_MASTER";
	/**
	 * 
	 */
	public static final String NODE_TABLE_NAME = "NV_NODE_MASTER";
	/**
	 * 
	 */
	public static final String DEFAULT_ADMIN_NAME = "NVIDIA";
	/**
	 * 
	 */
	public static final String DEFAULT_ADMIN_USER_NAME = "nvadmin";
	/**
	 * 
	 */
	public static final String DEFAULT_ADMIN_EMAIL = "admin@nvidia.com";

	/**
	 * 
	 */
	public static final String DEFAULT_ADMIN_PHONE = "4082134567";
	
	public static final String NODE_CONNECTED = "connected";
	
	public static final String NODE_DISCONNCETD = "disconnected";
	public static final String NODE_DISCONNCETD_IP = "--";
	public static final String SUPER_ADMIN = "Super Admin";
	public static final String CUSTOMER_ADMIN = "Customer Admin";
	public static final String CUSTOMER_USER = "Customer User";
	
	
	/*public enum UserStatus {
		ACTIVE("ACTIVE"), 
		REGISTERED("REGISTERED");

		private String name;

		UserStatus(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}*/


	/**
	 * 
	 */
	//public static final String[] ROLES = new String[] { "Super Admin", "Customer Admin", "Customer User" };
	/**
	 * 
	 */
	String WE1COME = "We1come!@#";
	public String LOGGED_IN_ROLE_NAME = "LoggedInRoleName";
	public String LOGGED_IN_EMAIL = "LoggedInEmail";
	public String LOGGED_IN_AUTH_KEY = "LoggedInAuthKey";
	public String USER_NAME = "nvadmin";
	
	//grafana and inflexdb usernames and passwords
	public static String grafanaUser = "grafana.username";
	public static String grafanaPwd = "grafana.password";
	public static String influxdbUser = "influxdb.username";
	public static String influxdbPwd = "influxdb.password";

}
