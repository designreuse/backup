package com.tresbu.nvidia.insert.dao;

import java.util.List;

import com.tresbu.nvidia.common.exception.NvidiaDaoException;
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

public interface NvidiaInsertDataDao {
		
	public void updateClusterData(ClusterEntity pClusterEntity) throws NvidiaDaoException;

	public void updateNodeData(NodeEntity pNodeEntity) throws NvidiaDaoException;

	public void insertJobDataList(List<JobEntity> pJobEntityList) throws NvidiaDaoException;

	public void insertJobData(JobEntity pJobEntity)throws NvidiaDaoException;

	public void insertNodeDataList(List<NodeEntity> pNodeEntityList)throws NvidiaDaoException;

	public void insertNodeData(NodeEntity pNodeEntity)throws NvidiaDaoException;

	public void insertCustomerDataList(List<CustomerEntity> pCustomerEntityList)throws NvidiaDaoException;

	public void insertCustomerData(CustomerEntity pCustomerEntity)throws NvidiaDaoException;

	public void insertClusterDataList(List<ClusterEntity> pClusterEntityList)throws NvidiaDaoException;

	public void insertClusterData(ClusterEntity pClusterEntity)throws NvidiaDaoException;

	public NodeEntity getNodeEntityBySerialId(String pNodeId)throws NvidiaDaoException;
	
	public CustomerEntity getCustomerEntityByCustId(String pCustomerId)throws NvidiaDaoException;
	
	public ClusterEntity getClusterEntityByClustId(String pClusterId)throws NvidiaDaoException;
	
	//cluster
	public ClusterEntity updateNfsDataInCluster(ClusterEntity pClusterEntity) throws NvidiaDaoException;
	
	// Below methods no more required...
	public void insertStatusData(List<StatusEntity> pStatusEntityList);

	public void insertPolicyData(List<PolicyEntity> pPolicyEntityList);

	public void insertScheduleEntity(List<ScheduleEntity> pScheduleEntityList);

	public void insertFrequencyData(List<FrequencyEntity> pFrequencyEntityList);

	public void insertAttemptData(List<AttemptEntity> pAttemptEntityList);

	public void insertAlertSummaryData(List<AlertSummaryEntity> pAlertSummaryEntityList);

	public void insertAlertData(List<AlertEntity> pAlertEntityList);
}
