package com.tresbu.nvidia.dao.intrface;

import java.util.List;

import com.tresbu.nvidia.common.data.AlertData;
import com.tresbu.nvidia.common.data.AlertSummaryData;
import com.tresbu.nvidia.common.data.JobData;
import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.exception.NvidiaDaoException;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;

public interface NvidiaDao {

	// public NodeEntity getNodeInfoDataByClusterName(String pClusterName)
	// throws NvidiaDaoException;

	public String getLicenceKeyByNodeId(String pNodeId) throws NvidiaDaoException;

	public List<NodeEntity> getNodeInfoDataListClusterId(String pClusterId) throws NvidiaDaoException;

	public ClusterEntity getClusterDataByClusterId(String pClusterId) throws NvidiaDaoException;

	public LoginData getUserLoginDetails(String pUserName, String pPassword) throws NvidiaDaoException;
	
	public LoginData getUserLoginDetails(String pUserName) throws NvidiaDaoException;

	public List<JobEntity> getJobDataListBySerialId(String pNodeId) throws NvidiaDaoException;

	public NodeEntity getNodeEntityByNodeId(NodeEntity pNodeEntity) throws NvidiaDaoException;

	public NodeEntity getNodeEntityBySerialAndClusterId(String pNodeName, String pClusterId) throws NvidiaDaoException;

	public List<ClusterEntity> getClusterEntities() throws NvidiaDaoException;
	
	public Integer saveAuthToken(String pUsername,String pAuthToken) throws NvidiaDaoException;
	
	public String getAuthTokenByUser(String pUsername) throws NvidiaDaoException;

	// Remove Below Methods no more required
	public List<AlertSummaryData> getAlertSummaryDataList();

	public List<JobData> getJobDataByClusterName(String pClusterName);

	public List<AlertData> getAlertDataByClusterName(String pClusterName);

	public List<AlertData> getAlertDataList();
}
