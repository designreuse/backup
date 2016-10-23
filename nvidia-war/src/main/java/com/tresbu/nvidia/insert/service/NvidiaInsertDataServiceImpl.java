package com.tresbu.nvidia.insert.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tresbu.nvidia.common.DateUtil;
import com.tresbu.nvidia.common.exception.NvidiaDBDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoNoDataFoundException;
import com.tresbu.nvidia.common.exception.NvidiaServiceException;
import com.tresbu.nvidia.common.exception.NvidiaServiceNoDataFoundException;
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
import com.tresbu.nvidia.insert.dao.NvidiaInsertDataDao;
import com.tresbu.nvidia.json.pojo.Node;

public class NvidiaInsertDataServiceImpl implements NvidiaInsertDataService {

	@Autowired
	private NvidiaInsertDataDao mNvidiaInsertDataDao = null;

	@Override
	public void insertNodeData(Node pNode) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertNodeData(this.convertNodeToNodeEntity(pNode));
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public void insertCustomerData(CustomerEntity pCustomerEntity) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertCustomerData(pCustomerEntity);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public void insertClusterData(ClusterEntity pClusterEntity) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertClusterData(pClusterEntity);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public void insertJobData(JobEntity pJobEntity) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertJobData(pJobEntity);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public void insertCustomerDataList(List<CustomerEntity> pCustomerEntityList) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertCustomerDataList(pCustomerEntityList);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public void insertClusterDataList(List<ClusterEntity> pClusterEntityList) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertClusterDataList(pClusterEntityList);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}

	}

	@Override
	public void insertNodeDataList(List<NodeEntity> pNodeEntityList) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertNodeDataList(pNodeEntityList);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}

	}

	@Override
	public void insertJobDataList(List<JobEntity> pJobEntityList) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.insertJobDataList(pJobEntityList);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}

	}

	@Override
	public void updateNodeData(Node pNode) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.updateNodeData(this.convertNodeToNodeEntity(pNode));
		} catch (NvidiaDBDaoException e) {
			throw new NvidiaServiceException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public void updateClusterData(ClusterEntity pClusterEntity) throws NvidiaServiceException {
		try {
			mNvidiaInsertDataDao.updateClusterData(pClusterEntity);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
	}

	@Override
	public CustomerEntity getCustomerEntityByCustomerId(String pCustomerId) throws NvidiaServiceException {
		CustomerEntity customerEntity = null;
		try {
			customerEntity = mNvidiaInsertDataDao.getCustomerEntityByCustId(pCustomerId);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		return customerEntity;
	}

	@Override
	public ClusterEntity getClusterEntityByClustId(String pClusterId) throws NvidiaServiceException {
		ClusterEntity clusterEntity = null;
		try {
			clusterEntity = mNvidiaInsertDataDao.getClusterEntityByClustId(pClusterId);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		return clusterEntity;
	}

	// cluster NFS

	@Override
	public ClusterEntity updateNfsDataInCluster(ClusterEntity pClusterEntity) throws NvidiaServiceException {
		ClusterEntity clusterEntity = null;
		try {
			clusterEntity = mNvidiaInsertDataDao.updateNfsDataInCluster(pClusterEntity);
		} catch (NvidiaDBDaoException e) {
			throw new NvidiaServiceException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}
		return clusterEntity;

	}

	@Override
	public NodeEntity getNodeEntityBySerialId(String pSerialId) throws NvidiaServiceException {
		NodeEntity nodeEntity = null;
		try {
			nodeEntity = mNvidiaInsertDataDao.getNodeEntityBySerialId(pSerialId);
		} catch (NvidiaDaoNoDataFoundException e) {
			throw new NvidiaServiceNoDataFoundException(e);
		} catch (NvidiaDaoException e) {
			throw new NvidiaServiceException(e);
		}

		return nodeEntity;
	}

	private NodeEntity convertNodeToNodeEntity(Node pNode) {
		NodeEntity nodeEntity = new NodeEntity();
		nodeEntity.setSerialId(pNode.getNodeStatus().getSerialid());
		nodeEntity.setNodeName(pNode.getNodeStatus().getNodeName());
		nodeEntity.setNodeId(pNode.getNodeStatus().getNodeId());
		nodeEntity.setIpAddress(pNode.getNodeStatus().getIpAddress());
		nodeEntity.setTags(pNode.getNodeStatus().getTags());

		Timestamp newCreatedDate = DateUtil.convertStringToTimeStamp(pNode.getNodeStatus().getCreatedTime());
		if (newCreatedDate != null) {
			nodeEntity.setCreatedTime(newCreatedDate);
		}

		Timestamp newLastRestartDate = DateUtil
				.convertStringToTimeStamp(pNode.getNodeStatus().getLastRestartedOnTime());
		if (newLastRestartDate != null) {
			nodeEntity.setLastRestartedOnTime(newLastRestartDate);
		}
		nodeEntity.setMemory(pNode.getNodeStatus().getMemory());
		nodeEntity.setDiskSpace(pNode.getNodeStatus().getDiskSpace());
		nodeEntity.setBiosVersion(pNode.getNodeStatus().getBiosVersion());
		nodeEntity.setStatus(pNode.getNodeStatus().getStatus());
		nodeEntity.setLicenceKey(pNode.getNodeStatus().getLicenceKey());
		nodeEntity.setClusterId(pNode.getNodeStatus().getClusterId());
		nodeEntity.setMode(pNode.getNodeStatus().getMode());
		nodeEntity.setSwVersion(pNode.getNodeStatus().getSwVersion());
		nodeEntity.setFwVersion(pNode.getNodeStatus().getFwVersion());
		nodeEntity.setIpmi(pNode.getNodeStatus().getIpmi());
		nodeEntity.setStartTime(pNode.getNodeStatus().getStartTime());
		nodeEntity.setIsLeader(pNode.getNodeStatus().getIsLeader());
		nodeEntity.setSubnet(pNode.getNodeStatus().getSubnet());
		nodeEntity.setClusterGroup(pNode.getNodeStatus().getClusterGroup());
		nodeEntity.setGateway(pNode.getNodeStatus().getGateway());
		nodeEntity.setKey(pNode.getKey());
		nodeEntity.setCloudManaged(pNode.getNodeStatus().getCloudManaged());
		nodeEntity.setFirstBoot(pNode.getNodeStatus().getFirstBoot());
		nodeEntity.setEulaAccepted(pNode.getNodeStatus().getEulaAccepted());
		nodeEntity.setSerialNumber(pNode.getNodeStatus().getSerialNumber());
		nodeEntity.setCloudStatus(pNode.getNodeStatus().getCloudStatus());
		nodeEntity.setGpuConfiguration(pNode.getNodeStatus().getGpuConfiguration());
		nodeEntity.setCloudGroup(pNode.getNodeStatus().getCloudGroup());
		nodeEntity.setModelName(pNode.getNodeStatus().getModelName());
		return nodeEntity;
	}

	// Below methods are no more required...
	@Override
	public void insertStatusData(List<StatusEntity> pStatusEntityList) {
		mNvidiaInsertDataDao.insertStatusData(pStatusEntityList);

	}

	@Override
	public void insertPolicyData(List<PolicyEntity> pPolicyEntityList) {
		mNvidiaInsertDataDao.insertPolicyData(pPolicyEntityList);
	}

	@Override
	public void insertScheduleEntity(List<ScheduleEntity> pScheduleEntityList) {
		mNvidiaInsertDataDao.insertScheduleEntity(pScheduleEntityList);
	}

	@Override
	public void insertFrequencyData(List<FrequencyEntity> pFrequencyEntityList) {
		mNvidiaInsertDataDao.insertFrequencyData(pFrequencyEntityList);
	}

	@Override
	public void insertAttemptData(List<AttemptEntity> pAttemptEntityList) {
		mNvidiaInsertDataDao.insertAttemptData(pAttemptEntityList);

	}

	@Override
	public void insertAlertSummaryData(List<AlertSummaryEntity> pAlertSummaryEntityList) {
		mNvidiaInsertDataDao.insertAlertSummaryData(pAlertSummaryEntityList);

	}

	@Override
	public void insertAlertData(List<AlertEntity> pAlertEntityList) {
		mNvidiaInsertDataDao.insertAlertData(pAlertEntityList);

	}

}
