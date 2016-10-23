package com.tresbu.nvidia.dao.insert.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tresbu.nvidia.common.data.NodeInfoData;
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
import com.tresbu.nvidia.insert.service.NvidiaInsertDataService;
import com.tresbu.nvidia.model.store.NvidiaDataModelStore;

public class NvidiaInsertDataWrapper {

	@Autowired
	private NvidiaInsertDataService mNvidiaInsertDataService;

	public void insertNvidiaData() {

		this.insertCustomerEntityData();
		this.insertStatusEntityData();
		this.insertClusterEntityData();
		
		
		this.insertNodeEntityData();
		
		
		this.insertAlertSummaryEntityData();
		this.insertJobEntityData();
		this.insertAlertEntityData();
		this.insertPolicyEntityData();
		this.insertScheduleEntityData();
		this.insertFrequencyEntityData();
		this.inserAttemptsEntityData();
	}

	private void insertStatusEntityData() {

		List<StatusEntity> statusList = new ArrayList<StatusEntity>();
		StatusEntity statusEntity = new StatusEntity();
		statusEntity.setStatus("Running");
		statusList.add(statusEntity);

		statusEntity = new StatusEntity();
		statusEntity.setStatus("Stopped");
		statusList.add(statusEntity);

		statusEntity = new StatusEntity();
		statusEntity.setStatus("NA");
		statusList.add(statusEntity);

		mNvidiaInsertDataService.insertStatusData(statusList);
	}

	private void insertPolicyEntityData() {

		List<PolicyEntity> policyList = new ArrayList<PolicyEntity>();
		PolicyEntity policyEntity = new PolicyEntity();
		policyEntity.setPolicy("Full");
		policyList.add(policyEntity);

		policyEntity = new PolicyEntity();
		policyEntity.setPolicy("NY-BACk02-SYSTEM");
		policyList.add(policyEntity);

		policyEntity = new PolicyEntity();
		policyEntity.setPolicy("Windows-D2D2T");
		policyList.add(policyEntity);
		mNvidiaInsertDataService.insertPolicyData(policyList);
	}

	private void insertScheduleEntityData() {

		List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		scheduleEntity.setSchedule("Full");
		scheduleList.add(scheduleEntity);

		scheduleEntity = new ScheduleEntity();
		scheduleEntity.setSchedule("Triggered-bkup");
		scheduleList.add(scheduleEntity);

		scheduleEntity = new ScheduleEntity();
		scheduleEntity.setSchedule("LINUX-Selections");
		scheduleList.add(scheduleEntity);
		mNvidiaInsertDataService.insertScheduleEntity(scheduleList);
	}

	private void insertFrequencyEntityData() {

		List<FrequencyEntity> frequencyList = new ArrayList<FrequencyEntity>();
		FrequencyEntity frequencyEntity = new FrequencyEntity();
		frequencyEntity.setFrequency("1");
		frequencyList.add(frequencyEntity);

		frequencyEntity = new FrequencyEntity();
		frequencyEntity.setFrequency("7");
		frequencyList.add(frequencyEntity);

		frequencyEntity = new FrequencyEntity();
		frequencyEntity.setFrequency("100");
		frequencyList.add(frequencyEntity);
		mNvidiaInsertDataService.insertFrequencyData(frequencyList);
	}

	private void inserAttemptsEntityData() {

		List<AttemptEntity> attemptList = new ArrayList<AttemptEntity>();
		AttemptEntity attemptEntity = new AttemptEntity();
		attemptEntity.setAttempts("1");
		attemptList.add(attemptEntity);

		attemptEntity = new AttemptEntity();
		attemptEntity.setAttempts("2");
		attemptList.add(attemptEntity);

		attemptEntity = new AttemptEntity();
		attemptEntity.setAttempts("3");
		attemptList.add(attemptEntity);
		mNvidiaInsertDataService.insertAttemptData(attemptList);
	}

	private void insertCustomerEntityData() {

		List<CustomerEntity> customerList = new ArrayList<CustomerEntity>();
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerId("Custom1");
		customerEntity.setUsername("tresbu");
		customerEntity.setEmail("tresbu@tresbu.com");
		customerEntity.setPassword("Welcome");
		customerList.add(customerEntity);
		//mNvidiaInsertDataService.insertCustomerDataList(customerList);
	}

	private void insertClusterEntityData() {

		List<ClusterEntity> clusterList = new ArrayList<ClusterEntity>();
		ClusterEntity clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("Cluster:Tresbu");
		clusterList.add(clusterEntity);

		clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("Cluster:skyhigh");
		clusterList.add(clusterEntity);

		//mNvidiaInsertDataService.insertClusterDataList(clusterList);

	}

	private void insertNodeEntityData() {
		
		List<NodeEntity> nodeList = new ArrayList<NodeEntity>();
		for (NodeInfoData infoData : NvidiaDataModelStore.getNodeInfoList()) {
			NodeEntity nodeEntity = new NodeEntity();
			nodeEntity.setBiosVersion(infoData.getBiosVersion());
			nodeEntity.setClusterId("1");
			//nodeEntity.setCreatedTime(DateUtil.convertStringToTimeStamp(infoData.getCreatedTime()).toString());
			nodeEntity.setDiskSpace(infoData.getDiskSpace());
			nodeEntity.setIpAddress(infoData.getIpAddress());
			//nodeEntity.setLastRestartedOnTime(DateUtil.convertStringToTimeStamp(infoData.getLastRestartedonTime()).toString());
			nodeEntity.setMemory(infoData.getMemory());
			nodeEntity.setNodeName(infoData.getName());
			nodeEntity.setStatus(infoData.getStatus());
			nodeEntity.setTags(infoData.getTags());
			nodeEntity.setMode(infoData.getMode());
			nodeEntity.setSwVersion(infoData.getSwVersion());
			nodeEntity.setFwVersion(infoData.getFwVersion());
			nodeEntity.setIpmi(infoData.getIpmi());
			nodeEntity.setStartTime(infoData.getStartTime());
			nodeEntity.setIsLeader(infoData.getIsLeader());
			nodeEntity.setSubnet(infoData.getSubnet());
			nodeEntity.setClusterGroup(infoData.getClusterGroup());
			nodeEntity.setNodenetworkinformation(infoData.getNodenetworkinformation());
			nodeEntity.setGateway(infoData.getGateway());
			nodeEntity.setKey(infoData.getNodeKey());
			nodeList.add(nodeEntity);
		}

		//mNvidiaInsertDataService.insertNodeDataList(nodeList);

	}

	private void insertAlertSummaryEntityData() {
		List<AlertSummaryEntity> alertSummaryList = new ArrayList<AlertSummaryEntity>();

		AlertSummaryEntity alertSummaryEntity = new AlertSummaryEntity();
		alertSummaryEntity.setAlertMessage("Data Backup failed due to 1% disk capacity.");
		alertSummaryEntity.setAlertDate(Timestamp.valueOf("2016-03-19 04:41:33"));
		ClusterEntity clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("121");
		alertSummaryEntity.setCluster(clusterEntity);
		NodeEntity nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("1");
		alertSummaryEntity.setNode(nodeEntity);
		alertSummaryEntity.setAlertSummaryId(1);
		alertSummaryList.add(alertSummaryEntity);

		alertSummaryEntity = new AlertSummaryEntity();
		alertSummaryEntity.setAlertMessage("Server not able to ping subnetmask");
		alertSummaryEntity.setAlertDate(Timestamp.valueOf("2016-03-19 04:41:33"));
		clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("121");
		alertSummaryEntity.setCluster(clusterEntity);
		nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("2");
		alertSummaryEntity.setNode(nodeEntity);
		alertSummaryEntity.setAlertSummaryId(2);
		alertSummaryList.add(alertSummaryEntity);

		alertSummaryEntity = new AlertSummaryEntity();
		alertSummaryEntity.setAlertMessage("Server slow in responding after image update");
		alertSummaryEntity.setAlertDate(Timestamp.valueOf("2016-03-19 04:41:33"));
		clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("121");
		alertSummaryEntity.setCluster(clusterEntity);
		nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("3");
		alertSummaryEntity.setNode(nodeEntity);
		alertSummaryEntity.setAlertSummaryId(3);
		alertSummaryList.add(alertSummaryEntity);

		mNvidiaInsertDataService.insertAlertSummaryData(alertSummaryList);

	}

	private void insertJobEntityData() {
		List<JobEntity> jobList = new ArrayList<JobEntity>();

		JobEntity jobEntity = new JobEntity();
		jobEntity.setJobId(1);
		jobEntity.setJobName("DIGITS");
		jobEntity.setLastRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		jobEntity.setNextRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		NodeEntity nodeEntity = new NodeEntity();
		nodeEntity.setNodeName("Tiger");
		nodeEntity.setSerialId("2");
		jobEntity.setNode(nodeEntity);
		jobEntity.setStatus("Stopped");
		jobList.add(jobEntity);

		jobEntity = new JobEntity();
		jobEntity.setJobId(2);
		jobEntity.setJobName("Tensorflow");
		jobEntity.setLastRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		jobEntity.setNextRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		nodeEntity = new NodeEntity();
		nodeEntity.setNodeName("Hummer");
		nodeEntity.setSerialId("3");
		jobEntity.setNode(nodeEntity);
		jobEntity.setStatus("NA");
		jobList.add(jobEntity);

		jobEntity = new JobEntity();
		jobEntity.setJobId(3);
		jobEntity.setJobName("Torch");
		jobEntity.setLastRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		jobEntity.setNextRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		nodeEntity = new NodeEntity();
		nodeEntity.setNodeName("Casper");
		nodeEntity.setSerialId("7");	
		jobEntity.setNode(nodeEntity);
		jobEntity.setStatus("Running");
		jobList.add(jobEntity);

		jobEntity = new JobEntity();
		jobEntity.setJobId(4);
		jobEntity.setJobName("Theano");
		jobEntity.setLastRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		jobEntity.setNextRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		nodeEntity = new NodeEntity();
		nodeEntity.setNodeName("Oram");
		nodeEntity.setSerialId("6");
		jobEntity.setNode(nodeEntity);
		jobEntity.setStatus("Running");
		jobList.add(jobEntity);

		jobEntity = new JobEntity();
		jobEntity.setJobId(5);
		jobEntity.setJobName("DL application");
		jobEntity.setLastRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		jobEntity.setNextRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		nodeEntity = new NodeEntity();
		nodeEntity.setNodeName("Magna");
		nodeEntity.setSerialId("5");
		jobEntity.setNode(nodeEntity);
		jobEntity.setStatus("Stopped");
		jobList.add(jobEntity);

		jobEntity = new JobEntity();
		jobEntity.setJobId(6);
		jobEntity.setJobName("iRAY");
		jobEntity.setLastRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		jobEntity.setNextRunDate(Timestamp.valueOf("2016-03-19 04:41:33").toString());
		nodeEntity = new NodeEntity();
		nodeEntity.setNodeName("Zury");
		nodeEntity.setSerialId("1");
		jobEntity.setNode(nodeEntity);
		jobEntity.setStatus("Running");
		jobList.add(jobEntity);
		//mNvidiaInsertDataService.insertJobDataList(jobList);
	}

	private void insertAlertEntityData() {

		List<AlertEntity> alertList = new ArrayList<AlertEntity>();

		AlertEntity alertEntity = new AlertEntity();
		alertEntity.setAlertId(11);
		ClusterEntity clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("121");
		alertEntity.setCluster(clusterEntity);
		alertEntity.setFrequencyId(121);
		NodeEntity nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("121");
		alertEntity.setNode(nodeEntity);
		alertEntity.setPolicyId(121);
		alertEntity.setScheduleId(121);
		alertList.add(alertEntity);

		alertEntity = new AlertEntity();
		alertEntity.setAlertId(12);
		clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("122");
		alertEntity.setCluster(clusterEntity);
		alertEntity.setFrequencyId(8);
		nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("122");
		alertEntity.setNode(nodeEntity);
		alertEntity.setPolicyId(15);
		alertEntity.setScheduleId(6);
		alertList.add(alertEntity);

		alertEntity = new AlertEntity();
		clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("122");
		alertEntity.setCluster(clusterEntity);
		nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("122");
		alertEntity.setNode(nodeEntity);
		alertEntity.setFrequencyId(9);
		alertEntity.setAlertId(10);
		alertEntity.setPolicyId(12);
		alertEntity.setScheduleId(8);
		alertList.add(alertEntity);

		alertEntity = new AlertEntity();
		clusterEntity = new ClusterEntity();
		clusterEntity.setClusterId("122");
		alertEntity.setCluster(clusterEntity);
		nodeEntity = new NodeEntity();
		nodeEntity.setSerialId("122");
		alertEntity.setNode(nodeEntity);
		alertEntity.setFrequencyId(8);
		alertEntity.setAlertId(15);
		alertEntity.setPolicyId(4);
		alertEntity.setScheduleId(6);
		alertList.add(alertEntity);

		mNvidiaInsertDataService.insertAlertData(alertList);
	}

}
