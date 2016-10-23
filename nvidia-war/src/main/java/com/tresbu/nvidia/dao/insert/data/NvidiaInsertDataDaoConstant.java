package com.tresbu.nvidia.dao.insert.data;

public class NvidiaInsertDataDaoConstant {

	// Status table constant
	public static final String LICENCE_KEY = "licence_key";

	public static final String STATUS = "status";

	public static final String INSERT_STATUS_TABLE_QUERY = "insert into status(status_id,status) values(?,?)";

	public static final String SELECT_NODE_DATA_LIST_BY_CLUSTER_ID_QUERY = "select * from node n where n.cluster_id=?";

//	public static final String SELECT_NODE_DATA_BY_NAME_QUERY = "select * from node n where n.cluster_id= ? ";

	public static final String SELECT_NODE_DATA_BY_NODE_ID_QUERY = "select * from node n where n.serialid= ? ";

	public static final String SELECT_NODE_DATA_BY_SERIAL_AND_CLUSTER_ID_QUERY = "select * from node n where n.serialid= ? and n.cluster_id=?";

	public static final String SELECT_JOB_DATA_LIST_BY_NODE_ID_QUERY = "select * from job j where j.serialid= ? ";

	public static final String SELECT_CLUSTER_DATA_LIST_QUERY = "select * from cluster";

	public static final String SELECT_CLUSTER_DATA_BY_CLUSTER_ID = "select cluster_id,cluster_ip_address,nfs_one,nfs_two from cluster c where c.cluster_id=?";

	public static final String SELECT_LICENCE_KEY_BY_NODE_ID = "select licence_key from node n where n.node_id=?";

	public static final String SELECT_CUSTOMER_DATA_BY_CUSTMER_ID = "select customer_id,username,email,password from customer c where c.customer_id=?";

	// Update
	public static final String UPDATE_NODE_DATA = " update node set node_name=?,ip_address=?,tags=?,created_time=?,"
			+ " last_restarted_on_time=?,memory=?,disk_space=?,bios_version=?,status=?,"
			+ " licence_key=?,node_id=?,mode=?,sw_version=?,fw_version=?,ipmi=?,start_time=?,"
			+ " is_leader=?,subnet=?,cluster_group=?,node_network_information=?,gateway=?,node_key=?,"
			+ " cloud_managed=?,first_boot=?,eula_accepted=?,serial_number=?,cloud_status=?,"
			+ " gpu_configuration=?,cloud_group=?,model_name=? where serialid=? and cluster_id=? ";

	public static final String UPDATE_CLUSTER_DATA = "update cluster set cluster_ip_address=?,nfs_one=?, nfs_two=? where cluster_id=? ";

	// Schedule table constant

	public static final String SCHEDULE_ID = "serialid";

	public static final String SCHEDULE = "schedule";

	public static final String INSERT_SCHEDULE_TABLE_QUERY = "insert into schedule(schedule_id,schedule) values(?,?)";

	// Frequency table constant

	public static final String FREQUENCY_ID = "frequency_Id";

	public static final String FREQUENCY = "frequency";

	public static final String INSERT_FREQUENCY_TABLE_QUERY = "insert into frequency(frequency_id,frequency) values(?,?)";

	// Policy table constant
	public static final String POLICY_ID = "policy_Id";

	public static final String POLICY = "policy";

	public static final String INSERT_POLICY_TABLE_QUERY = "insert into policy(policy_id,policy) values(?,?)";

	// Attempts table constant
	public static final String ATTEMPT_ID = "attempts_Id";

	public static final String ATTEMPT = "attempts";

	public static final String INSERT_ATTEMPT_TABLE_QUERY = "insert into attempts(attempts_id,attempts) values(?,?)";

	// Customer table constant
	public static final String CUSTOMER_ID = "customer_Id";

	public static final String USERNAME = "username";

	public static final String EMAIL = "email";

	public static final String PASSWORD = "password";

	public static final String INSERT_CUSTOMER_TABLE_QUERY = "insert into customer(username,email,password) values(?,?,?)";

	// Cluster table constant
	public static final String CLUSTER_ID = "cluster_Id";

	public static final String CLUSTER_NAME = "cluster_name";

	public static final String CLUSTER_IP_ADDRESS = "cluster_ip_address";
	
    public static final String NFS_ONE = "nfs_one";
	
	public static final String NFS_TWO = "nfs_two";

	public static final String UPDATE_NFS_DETAILS_OF_CLUSTER = "update cluster set cluster_ip_address=?, nfs_one=?, nfs_two=? where cluster_Id=?";

	public static final String INSERT_CLUSTER_TABLE_QUERY = "insert into cluster(cluster_ip_address,nfs_one,nfs_two,cluster_id) values(?,?,?,?)";

	// Node table constant
	public static final String SERIAL_ID = "serialid";

	public static final String NODE_ID = "node_id";

	public static final String IP_ADDRESS = "ip_address";

	public static final String TAGS = "tags";

	public static final String CREATED_TIME = "created_time";

	public static final String LAST_RESTARTED_ON_TIME = "last_restarted_on_time";

	public static final String MEMORY = "memory";

	public static final String DISK_SPACE = "disk_space";

	public static final String BIOS_VERSION = "bios_version";

	public static final String MODE = "mode";

	public static final String SW_VERSION = "sw_version";

	public static final String FW_VERSION = "fw_version";

	public static final String IPMI = "ipmi";

	public static final String STARTTIME = "start_time";

	public static final String ISLEADER = "is_leader";
	public static final String SUBNET = "subnet";
	public static final String CLUSTERGROUP = "cluster_group";
	public static final String NODE_NETWORK_INFORMATION = "node_network_information";
	public static final String GATEWAY = "gateway";
	public static final String NODE_KEY = "node_key";
	public static final String NODE_NAME = "node_name";
	
	public static final String CLOUD_MANAGED="cloud_managed";
	public static final String FIRST_BOOT="first_boot";
	public static final String EULA_ACCEPTED="eula_accepted";
	public static final String SERIAL_NUMBER="serial_number";
	public static final String CLOUD_STATUS="cloud_status";
	public static final String GPU_CONFIGURATION="gpu_configuration";
	public static final String CLOUD_GROUP="cloud_group";
	public static final String MODEL_NAME="model_name";
	

	public static final String INSERT_NODE_TABLE_QUERY = "insert into node(node_name,"
			+ " ip_address,tags,created_time,last_restarted_on_time,memory,"
			+ " disk_space,bios_version,status,licence_key,node_id,mode,"
			+ " sw_version,fw_version,ipmi,start_time,is_leader,subnet,"
			+ " cluster_group,node_network_information,gateway,"
			+ " node_key,cloud_managed,first_boot,eula_accepted,serial_number,"
			+ " cloud_status,gpu_configuration,cloud_group,model_name,serialid,cluster_id)"
			+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	// AttemptsSummary table constant
	public static final String ALERT_MESSAGE = "alert_message";

	public static final String ALERT_DATE = "alert_date";

	public static final String ALERT_SUMMARY_ID = "alert_summary_Id";

	public static final String INSERT_ATTEMPTS_SUMMARY_TABLE_QUERY = "insert into alert_summary(alert_message,alert_date,alert_summary_id, node_id, cluster_id) values(?,?,?,?,?)";

	// Job table constant
	public static final String JOB_ID = "job_Id";

	public static final String JOB_NAME = "job_name";

	public static final String LAST_RUN_DATE = "last_run_date";

	public static final String NEXT_RUN_DATE = "next_run_date";

	public static final String INSERT_JOB_TABLE_QUERY = "insert into job(node_id,job_name,last_run_date, next_run_date, status) values(?,?,?,?,?)";

	// Alert table constant
	public static final String ALERT_ID = "alert_Id";

	public static final String INSERT_ALERT_TABLE_QUERY = "insert into alert(alert_id,cluster_id,node_id,policy_id,schedule_id,frequency_id,attempts_id) values(?,?,?,?,?,?,?)";

	public static final String SELECT_FROM_LOGIN = "select login.email,login.password from nvidia.login login where login.email=? and login.password=? ";
	public static final String SELECT_FROM_LOGIN_USER = "select login.email,login.password from nvidia.login login where login.email=? ";
	
	public static final String UPDATE_LOGIN_AUTH_TOKEN="update login set token=? where login.email=?";
	
	public static final String SELECT_LOGIN_AUTH_TOKEN_BY_USER="select login.token from login login where login.email=?";
}
