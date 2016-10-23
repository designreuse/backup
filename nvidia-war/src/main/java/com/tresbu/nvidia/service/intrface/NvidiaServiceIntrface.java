package com.tresbu.nvidia.service.intrface;

import java.util.List;

import com.tresbu.nvidia.common.data.AlertData;
import com.tresbu.nvidia.common.data.AlertSummaryData;
import com.tresbu.nvidia.common.data.ClusterData;
import com.tresbu.nvidia.common.data.JobData;
import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.data.NodeInfoData;
import com.tresbu.nvidia.common.exception.NvidiaServiceException;

public interface NvidiaServiceIntrface {

	public List<NodeInfoData> getNodeInfoDataListByCluster(String pClusterId) throws NvidiaServiceException;

	public List<ClusterData> getClusterDataList() throws NvidiaServiceException;

	public ClusterData getClusterDataByClusterId(String pClusterId) throws NvidiaServiceException;

	// public NodeInfoData getNodeInfoDataByClusterName(String pClusterName)
	// throws NvidiaServiceException;

	public NodeInfoData getNodeInfoDataByNodeName(String pNodeId, String pClusterId) throws NvidiaServiceException;

	public String getLicenceKeyByNodeId(String pNodeId) throws NvidiaServiceException;

	public LoginData getUserLoginDetails(String pUserName, String pPassword) throws NvidiaServiceException;
	public LoginData getUserLoginDetails(String pUserName) throws NvidiaServiceException;

	public Integer saveAuthToken(String pUsername,String pAuthToken) throws NvidiaServiceException;
	
	public String getAuthTokenByUser(String pUsername) throws NvidiaServiceException;

	// No more required below methods

	public List<JobData> getJobDataListByNode(String pNodeId) throws NvidiaServiceException;

	public List<AlertData> getAlertDataByClusterName(String pClusterName);

	public List<JobData> getJobDataByClusterName(String pClusterName);

	public List<AlertData> getAlertDataList();

	public List<AlertSummaryData> getAlertSummaryDataList();
}
