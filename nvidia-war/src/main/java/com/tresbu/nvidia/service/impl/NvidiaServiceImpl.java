package com.tresbu.nvidia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tresbu.nvidia.common.DateUtil;
import com.tresbu.nvidia.common.data.AlertData;
import com.tresbu.nvidia.common.data.AlertSummaryData;
import com.tresbu.nvidia.common.data.ClusterData;
import com.tresbu.nvidia.common.data.JobData;
import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.data.NodeInfoData;
import com.tresbu.nvidia.common.exception.NvidiaDBDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoNoDataFoundException;
import com.tresbu.nvidia.common.exception.NvidiaServiceException;
import com.tresbu.nvidia.common.exception.NvidiaServiceNoDataFoundException;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;
import com.tresbu.nvidia.dao.intrface.NvidiaDao;
import com.tresbu.nvidia.service.intrface.NvidiaServiceIntrface;

import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_ENTER;
import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_EXIT;

public class NvidiaServiceImpl implements NvidiaServiceIntrface {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaServiceImpl.class.getName());

	@Autowired
	private NvidiaDao mNvidiaDaoIntrface;

	@Override
	public List<NodeInfoData> getNodeInfoDataListByCluster(String pClusterId) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getNodeInfoDataListByCluster()");
		List<NodeInfoData> nodeInfoDatas = new ArrayList<NodeInfoData>();
		try {
			for (NodeEntity nodeEntity : mNvidiaDaoIntrface.getNodeInfoDataListClusterId(pClusterId)) {

				NodeInfoData nodeInfoData = new NodeInfoData();
				nodeInfoData.setBiosVersion(nodeEntity.getBiosVersion());
				String createdDate = DateUtil.timestampToString(nodeEntity.getCreatedTime());
				if (createdDate != null && !createdDate.isEmpty()) {
					nodeInfoData.setCreatedTime(createdDate);
				}
				nodeInfoData.setDiskSpace(nodeEntity.getDiskSpace());
				nodeInfoData.setIpAddress(nodeEntity.getIpAddress());
				String lastRestartDate = DateUtil.timestampToString(nodeEntity.getLastRestartedOnTime());
				if (lastRestartDate != null && !lastRestartDate.isEmpty()) {
					nodeInfoData.setLastRestartedonTime(lastRestartDate);
				}
				nodeInfoData.setMemory(nodeEntity.getMemory());
				nodeInfoData.setName(nodeEntity.getNodeName());
				nodeInfoData.setStatus(nodeEntity.getStatus());
				nodeInfoData.setTags(nodeEntity.getTags());
				nodeInfoData.setSerialId(nodeEntity.getSerialId());
				nodeInfoData.setClusterId(nodeEntity.getClusterId());
				nodeInfoData.setIsLeader(nodeEntity.getIsLeader());
				nodeInfoData.setNodeKey(nodeEntity.getKey());
				nodeInfoData.setMode(nodeEntity.getMode());
				nodeInfoData.setClusterGroup(nodeEntity.getClusterGroup());
				nodeInfoData.setGateway(nodeEntity.getGateway());
				nodeInfoData.setGpuConfiguration(nodeEntity.getGpuConfiguration() != null ? Integer.parseInt(nodeEntity.getGpuConfiguration()) : 0);

				nodeInfoDatas.add(nodeInfoData);
			}
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getNodeInfoDataListByCluster()");
		return nodeInfoDatas;
	}

	@Override
	public List<ClusterData> getClusterDataList() throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getClusterDataList()");
		List<ClusterData> clusterDataList = new ArrayList<ClusterData>();
		try {
			for (ClusterEntity clusterEntity : mNvidiaDaoIntrface.getClusterEntities()) {
				ClusterData clusterData = new ClusterData();
				clusterData.setClusterId(clusterEntity.getClusterId());
				clusterData.setNfsone(clusterEntity.getNfsone());
				clusterDataList.add(clusterData);

			}
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getClusterDataList()");
		return clusterDataList;
	}

	@Override
	public NodeInfoData getNodeInfoDataByNodeName(String pSerialId, String pClusterId) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getNodeInfoDataByNodeName()");
		NodeInfoData nodeInfoData = new NodeInfoData();
		try {
			NodeEntity nodeEntity = mNvidiaDaoIntrface.getNodeEntityBySerialAndClusterId(pSerialId, pClusterId);
			nodeInfoData.setBiosVersion(nodeEntity.getBiosVersion());
			nodeInfoData.setCreatedTime(nodeEntity.getCreatedTime() != null ? nodeEntity.getCreatedTime().toString() : "");
			nodeInfoData.setDiskSpace(nodeEntity.getDiskSpace());
			nodeInfoData.setIpAddress(nodeEntity.getIpAddress());
			nodeInfoData.setLastRestartedonTime(nodeEntity.getLastRestartedOnTime() != null ? nodeEntity.getLastRestartedOnTime().toString() : "");
			nodeInfoData.setMemory(nodeEntity.getMemory());
			nodeInfoData.setName(nodeEntity.getNodeName());
			nodeInfoData.setStatus(nodeEntity.getStatus());
			nodeInfoData.setTags(nodeEntity.getTags());
			nodeInfoData.setSerialId(nodeEntity.getSerialId());
			nodeInfoData.setClusterId(nodeEntity.getClusterId());
			nodeInfoData.setFwVersion(nodeEntity.getFwVersion());
			nodeInfoData.setSwVersion(nodeEntity.getSwVersion());
			nodeInfoData.setIpmi(nodeEntity.getIpmi());
			nodeInfoData.setSubnet(nodeEntity.getSubnet());
			nodeInfoData.setIsLeader(nodeEntity.getIsLeader());
			nodeInfoData.setMode(nodeEntity.getMode());
			nodeInfoData.setClusterGroup(nodeEntity.getClusterGroup());
			nodeInfoData.setGateway(nodeEntity.getGateway());
			nodeInfoData.setNodeKey(nodeEntity.getKey());
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getNodeInfoDataByNodeName()");
		return nodeInfoData;
	}

	@Override
	public LoginData getUserLoginDetails(String pUserName, String pPassword) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getUserLoginDetails()");
		LoginData loginData = null;
		try {
			loginData = mNvidiaDaoIntrface.getUserLoginDetails(pUserName, pPassword);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getUserLoginDetails()");
		return loginData;
	}

	@Override
	public LoginData getUserLoginDetails(String pUserName) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getUserLoginDetails()");
		LoginData loginData = null;
		try {
			loginData = mNvidiaDaoIntrface.getUserLoginDetails(pUserName);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getUserLoginDetails()");
		return loginData;
	}

	@Override
	public List<JobData> getJobDataListByNode(String pNodeId) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getJobDataListByNode()");
		List<JobData> jobDatas = new ArrayList<JobData>();
		try {
			for (JobEntity jobEntity : mNvidiaDaoIntrface.getJobDataListBySerialId(pNodeId)) {
				JobData jobData = new JobData();
				jobData.setJobDataId(jobEntity.getJobId());
				jobData.setJobName(jobEntity.getJobName());
				jobData.setLasRun(jobEntity.getLastRunDate().toString());
				jobData.setNextRun(jobEntity.getNextRunDate().toString());
				try {
					// Pass nodeId and get node details
					NodeEntity nodeEntity = mNvidiaDaoIntrface.getNodeEntityByNodeId(jobEntity.getNode());
					jobData.setNodeName(nodeEntity.getNodeName());
				} catch (NvidiaDaoNoDataFoundException e) {
					throw new NvidiaServiceNoDataFoundException(e);
				} catch (NvidiaDaoException e) {
					throw new NvidiaServiceException(e);
				}
				jobData.setStatus(jobEntity.getStatus());
				jobDatas.add(jobData);

			}
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getJobDataListByNode()");
		return jobDatas;
	}

	@Override
	public ClusterData getClusterDataByClusterId(String pClusterId) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getClusterDataByClusterId()");
		ClusterData clusterData = new ClusterData();
		try {
			ClusterEntity clusterEntity = mNvidiaDaoIntrface.getClusterDataByClusterId(pClusterId);
			clusterData.setClusterId(clusterEntity.getClusterId());
			clusterData.setNfsone(clusterEntity.getNfsone());
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getClusterDataByClusterId()");
		return clusterData;
	}

	@Override
	public String getLicenceKeyByNodeId(String pNodeId) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getLicenceKeyByNodeId()");
		String licenceKey = null;
		try {
			licenceKey = mNvidiaDaoIntrface.getLicenceKeyByNodeId(pNodeId);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getLicenceKeyByNodeId()");
		return licenceKey;
	}

	@Override
	public Integer saveAuthToken(String pUsername, String pAuthToken) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.saveAuthToken()");
		Integer loginId = 0;
		try {
			loginId = mNvidiaDaoIntrface.saveAuthToken(pUsername, pAuthToken);
		} catch (NvidiaDBDaoException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.saveAuthToken()");
		return loginId;
	}

	@Override
	public String getAuthTokenByUser(String pUsername) throws NvidiaServiceException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaServiceImpl.getAuthTokenByUser()");
		String authToken = null;
		try {
			authToken = mNvidiaDaoIntrface.getAuthTokenByUser(pUsername);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaServiceImpl.getAuthTokenByUser()");
		return authToken;
	}

	@Override
	public List<AlertData> getAlertDataList() {
		return mNvidiaDaoIntrface.getAlertDataList();
	}

	@Override
	public List<AlertData> getAlertDataByClusterName(String pClusterName) {
		return mNvidiaDaoIntrface.getAlertDataByClusterName(pClusterName);
	}

	@Override
	public List<JobData> getJobDataByClusterName(String pClusterName) {
		return mNvidiaDaoIntrface.getJobDataByClusterName(pClusterName);
	}

	@Override
	public List<AlertSummaryData> getAlertSummaryDataList() {
		return mNvidiaDaoIntrface.getAlertSummaryDataList();
	}

	// @Override
	// public NodeInfoData getNodeInfoDataByClusterName(String pClusterName)
	// throws NvidiaServiceException {
	// NodeInfoData nodeInfoData = new NodeInfoData();
	// try {
	// NodeEntity nodeEntity =
	// mNvidiaDaoIntrface.getNodeInfoDataByClusterName(pClusterName);
	// nodeInfoData.setBiosVersion(nodeEntity.getBiosVersion());
	// nodeInfoData.setCreatedTime(nodeEntity.getCreatedTime().toString());
	// nodeInfoData.setDiskSpace(nodeEntity.getDiskSpace());
	// nodeInfoData.setIpAddress(nodeEntity.getIpAddress());
	// nodeInfoData.setLastRestartedonTime(nodeEntity.getLastRestartedOnTime().toString());
	// nodeInfoData.setMemory(nodeEntity.getMemory());
	// nodeInfoData.setName(nodeEntity.getNodeName());
	// nodeInfoData.setStatus(nodeEntity.getStatus());
	// nodeInfoData.setTags(nodeEntity.getTags());
	// nodeInfoData.setSerialId(nodeEntity.getSerialId());
	// nodeInfoData.setClusterId(nodeEntity.getClusterId());
	// nodeInfoData.setNodeKey(nodeEntity.getKey());
	// } catch (NvidiaDaoNoDataFoundException e) {
	// throw new NvidiaServiceNoDataFoundException(e);
	// } catch (NvidiaDaoException e) {
	// throw new NvidiaServiceException(e);
	// }
	// return nodeInfoData;
	// }

}
