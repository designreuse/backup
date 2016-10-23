package com.tresbu.nvidia.dao.row.mapper;

import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_ENTER;
import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_EXIT;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.BIOS_VERSION;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CLOUD_GROUP;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CLOUD_MANAGED;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CLOUD_STATUS;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CLUSTERGROUP;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CLUSTER_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CLUSTER_IP_ADDRESS;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CREATED_TIME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.CUSTOMER_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.DISK_SPACE;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.EMAIL;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.EULA_ACCEPTED;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.FIRST_BOOT;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.FW_VERSION;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.GATEWAY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.GPU_CONFIGURATION;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.IPMI;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.IP_ADDRESS;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.ISLEADER;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.JOB_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.JOB_NAME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.LAST_RESTARTED_ON_TIME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.LAST_RUN_DATE;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.LICENCE_KEY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.MEMORY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.MODE;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.MODEL_NAME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NEXT_RUN_DATE;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NODE_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NODE_KEY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NODE_NAME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NODE_NETWORK_INFORMATION;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.PASSWORD;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SERIAL_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SERIAL_NUMBER;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.STARTTIME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.STATUS;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SUBNET;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SW_VERSION;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.TAGS;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.USERNAME;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NFS_ONE;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.NFS_TWO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.data.NvidiaData;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.CustomerEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;

public class NvidiaDataRowMapper implements RowMapper<NvidiaData> {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaDataRowMapper.class.getName());

	private NvidiaData mNvidiaData;

	public NvidiaDataRowMapper(NvidiaData pNvidiaData) {
		this.mNvidiaData = pNvidiaData;
	}

	@Override
	public NvidiaData mapRow(ResultSet pResultSet, int rowNum) throws SQLException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDataRowMapper.mapRow()");
		if (mNvidiaData instanceof LoginData) {

			LoginData loginData = new LoginData();
			loginData.setEmail(pResultSet.getString(EMAIL));
			loginData.setPassword(pResultSet.getString(PASSWORD));
			return loginData;

		} else if (mNvidiaData instanceof NodeEntity) {

			NodeEntity nodeEntity = new NodeEntity();
			nodeEntity.setSerialId(pResultSet.getString(SERIAL_ID));
			nodeEntity.setNodeName(pResultSet.getString(NODE_NAME));
			nodeEntity.setIpAddress(pResultSet.getString(IP_ADDRESS));
			nodeEntity.setTags(pResultSet.getString(TAGS));
			nodeEntity.setCreatedTime(pResultSet.getTimestamp(CREATED_TIME));
			nodeEntity.setLastRestartedOnTime(pResultSet.getTimestamp(LAST_RESTARTED_ON_TIME));
			nodeEntity.setMemory(pResultSet.getString(MEMORY));
			nodeEntity.setDiskSpace(pResultSet.getString(DISK_SPACE));
			nodeEntity.setBiosVersion(pResultSet.getString(BIOS_VERSION));
			nodeEntity.setStatus(pResultSet.getString(STATUS));
			nodeEntity.setLicenceKey(pResultSet.getString(LICENCE_KEY));
			nodeEntity.setClusterId(pResultSet.getString(CLUSTER_ID));
			nodeEntity.setMode(pResultSet.getString(MODE));
			nodeEntity.setSwVersion(pResultSet.getString(SW_VERSION));
			nodeEntity.setFwVersion(pResultSet.getString(FW_VERSION));
			nodeEntity.setIpmi(pResultSet.getString(IPMI));
			nodeEntity.setStartTime(pResultSet.getString(STARTTIME));
			nodeEntity.setIsLeader(pResultSet.getString(ISLEADER));
			nodeEntity.setSubnet(pResultSet.getString(SUBNET));
			nodeEntity.setClusterGroup(pResultSet.getString(CLUSTERGROUP));
			nodeEntity.setNodeId(pResultSet.getString(NODE_ID));
			nodeEntity.setNodenetworkinformation(pResultSet.getString(NODE_NETWORK_INFORMATION));
			nodeEntity.setGateway(pResultSet.getString(GATEWAY));
			nodeEntity.setKey(pResultSet.getString(NODE_KEY));
			nodeEntity.setCloudManaged(pResultSet.getString(CLOUD_MANAGED));
			nodeEntity.setFirstBoot(pResultSet.getString(FIRST_BOOT));
			nodeEntity.setEulaAccepted(pResultSet.getString(EULA_ACCEPTED));
			nodeEntity.setSerialNumber(pResultSet.getString(SERIAL_NUMBER));
			nodeEntity.setCloudStatus(pResultSet.getString(CLOUD_STATUS));
			nodeEntity.setGpuConfiguration(pResultSet.getString(GPU_CONFIGURATION));
			nodeEntity.setCloudGroup(pResultSet.getString(CLOUD_GROUP));
			nodeEntity.setModelName(pResultSet.getString(MODEL_NAME));
			return nodeEntity;

		} else if (mNvidiaData instanceof ClusterEntity) {
			ClusterEntity clusterEntity = new ClusterEntity();
			clusterEntity.setClusterId(pResultSet.getString(CLUSTER_ID));
			clusterEntity.setClusterIpAddress(pResultSet.getString(CLUSTER_IP_ADDRESS));
			clusterEntity.setNfsone(pResultSet.getString(NFS_ONE));
			clusterEntity.setNfstwo(pResultSet.getString(NFS_TWO));
			return clusterEntity;
		} else if (mNvidiaData instanceof JobEntity) {
			JobEntity jobEntity = new JobEntity();
			jobEntity.setJobId(pResultSet.getInt(JOB_ID));
			jobEntity.setJobName(pResultSet.getString(JOB_NAME));
			jobEntity.setLastRunDate(pResultSet.getTimestamp(LAST_RUN_DATE).toString());
			jobEntity.setNextRunDate(pResultSet.getTimestamp(NEXT_RUN_DATE).toString());

			NodeEntity nodeEntity = new NodeEntity();
			nodeEntity.setSerialId(pResultSet.getString(SERIAL_ID));

			jobEntity.setNode(nodeEntity);
			jobEntity.setStatus(pResultSet.getString(STATUS));
			System.out.println(jobEntity.toString());
			return jobEntity;
		} else if (mNvidiaData instanceof CustomerEntity) {
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setCustomerId(pResultSet.getString(CUSTOMER_ID));
			customerEntity.setEmail(pResultSet.getString(EMAIL));
			customerEntity.setUsername(pResultSet.getString(USERNAME));
			return customerEntity;
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDataRowMapper.mapRow()");
		return null;
	}

}
