package com.tresbu.nvidia.insert.service;

import java.util.List;

import com.tresbu.nvidia.common.exception.NvidiaServiceException;
import com.tresbu.nvidia.dao.entity.AlertEntity;
import com.tresbu.nvidia.dao.entity.AlertSummaryEntity;
import com.tresbu.nvidia.dao.entity.AttemptEntity;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.CustomerEntity;
import com.tresbu.nvidia.dao.entity.FrequencyEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;
import com.tresbu.nvidia.dao.entity.PolicyEntity;
import com.tresbu.nvidia.dao.entity.ScheduleEntity;
import com.tresbu.nvidia.dao.entity.StatusEntity;
import com.tresbu.nvidia.json.pojo.Node;

public interface NvidiaInsertDataService {

	
	public NodeEntity getNodeEntityBySerialId(String pNodeId) throws NvidiaServiceException;
	
	public ClusterEntity getClusterEntityByClustId(String pClusterId) throws NvidiaServiceException;
	
	public CustomerEntity getCustomerEntityByCustomerId(String pCustomerId) throws NvidiaServiceException;
	
	public void updateNodeData(Node pNode) throws NvidiaServiceException;
	
	public void updateClusterData(ClusterEntity pClusterEntity) throws NvidiaServiceException;
	
	public void insertCustomerDataList(List<CustomerEntity> pCustomerEntityList) throws NvidiaServiceException;

	public void insertCustomerData(CustomerEntity pCustomerEntity) throws NvidiaServiceException;
	
	public void insertClusterDataList(List<ClusterEntity> pClusterEntityList) throws NvidiaServiceException;

	public void insertClusterData(ClusterEntity pClusterEntity) throws NvidiaServiceException;
	
	public void insertNodeDataList(List<NodeEntity> pNodeEntityList) throws NvidiaServiceException;

	public void insertNodeData(Node pNode) throws NvidiaServiceException;

	public void insertJobData(JobEntity pJobEntity) throws NvidiaServiceException;

	public void insertJobDataList(List<JobEntity> pJobEntityList) throws NvidiaServiceException;
	
	//cluster
	
	public ClusterEntity updateNfsDataInCluster(ClusterEntity pClusterEntity) throws NvidiaServiceException;
	
	// Below one are no more required...

	public void insertStatusData(List<StatusEntity> pStatusEntityList);

	public void insertPolicyData(List<PolicyEntity> pPolicyEntityList);

	public void insertScheduleEntity(List<ScheduleEntity> pScheduleEntityList);

	public void insertFrequencyData(List<FrequencyEntity> pFrequencyEntityList);

	public void insertAttemptData(List<AttemptEntity> pAttemptEntityList);

	public void insertAlertSummaryData(List<AlertSummaryEntity> pAlertSummaryEntityList);

	public void insertAlertData(List<AlertEntity> pAlertEntityList);

}
