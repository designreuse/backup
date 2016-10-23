package com.tresbu.nvidia.model.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tresbu.nvidia.common.data.AlertData;
import com.tresbu.nvidia.common.data.AlertSummaryData;
import com.tresbu.nvidia.common.data.JobData;
import com.tresbu.nvidia.common.data.NodeInfoData;

public class NvidiaDataModelStore {

	private static final List<String> CLUSTER_LIST;

	static {
		List<String> aList = new ArrayList<String>();
		aList.add("Zury");
		aList.add("Tiger");
		aList.add("Hummer");
		aList.add("Agnes");
		CLUSTER_LIST = Collections.unmodifiableList(aList);
	}

	private static final Map<String, NodeInfoData> NODE_INFO_MAP;

	static {

		Map<String, NodeInfoData> aMap = new HashMap<String, NodeInfoData>();

		NodeInfoData nodeInfoData = new NodeInfoData();
		nodeInfoData.setName("Zury");
		nodeInfoData.setIpAddress("172.20.16.178");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setMemory("20GB");
		nodeInfoData.setDiskSpace("2TB");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		aMap.put("Zury", nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setName("Tiger");
		nodeInfoData.setIpAddress("172.20.16.178");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setMemory("20GB");
		nodeInfoData.setDiskSpace("2TB");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		aMap.put("Tiger", nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setName("Hummer");
		nodeInfoData.setIpAddress("172.20.16.178");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setMemory("20GB");
		nodeInfoData.setDiskSpace("2TB");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		aMap.put("Hummer", nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setName("Agnes");
		nodeInfoData.setIpAddress("172.20.16.178");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setMemory("20GB");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("2TB");
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		aMap.put("Agnes", nodeInfoData);

		NODE_INFO_MAP = Collections.unmodifiableMap(aMap);
	}

	private static final Map<String, AlertSummaryData> ALERT_SUMMARY_DATA_MAP;

	static {

		Map<String, AlertSummaryData> aMap = new HashMap<String, AlertSummaryData>();

		AlertSummaryData alertSummaryData = new AlertSummaryData();
		alertSummaryData.setClusterName("Magna");
		alertSummaryData.setAlertMessage("Node not able to ping subnetmask");
		alertSummaryData.setAlertDateAndTime("Today 2:33 pm- 11.06.2014");
		alertSummaryData.setAlertTime("1m ago");
		aMap.put("Magna", alertSummaryData);

		alertSummaryData = new AlertSummaryData();
		alertSummaryData.setClusterName("Zury");
		alertSummaryData.setAlertMessage("Data backup failed due to 1% disk capacity");
		alertSummaryData.setAlertDateAndTime("Today 5:60 pm- 12.06.2014");
		alertSummaryData.setAlertTime("20m ago");
		aMap.put("Zury", alertSummaryData);

		alertSummaryData = new AlertSummaryData();
		alertSummaryData.setClusterName("Tiger");
		alertSummaryData.setAlertMessage("Node slow in responding after image update");
		alertSummaryData.setAlertDateAndTime("Yesterday 8:48 pm- 10.06.2014");
		alertSummaryData.setAlertTime("50m ago");
		aMap.put("Tiger", alertSummaryData);

		ALERT_SUMMARY_DATA_MAP = Collections.unmodifiableMap(aMap);

	}

	private static final List<NodeInfoData> NODE_INFO_LIST = new ArrayList<NodeInfoData>();

	static {

		NodeInfoData nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.45");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Zury");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");

		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.46");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Tiger");
		nodeInfoData.setStatus("Stopped");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.47");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Hummer");
		nodeInfoData.setStatus("Deleted");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.48");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Agnes");
		nodeInfoData.setStatus("Starting...");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.49");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Magna");
		nodeInfoData.setStatus("Stopped");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.52");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Oram");
		nodeInfoData.setStatus("Deleted");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.49");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Casper");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);

		nodeInfoData = new NodeInfoData();
		nodeInfoData.setBiosVersion("UIWEBASDSAHDSD");
		nodeInfoData.setCreatedTime("09/03/2016 22:08:09");
		nodeInfoData.setDiskSpace("4TB");
		nodeInfoData.setIpAddress("172.142.23.52");
		nodeInfoData.setMemory("10GB");
		nodeInfoData.setName("Windows");
		nodeInfoData.setStatus("Running");
		nodeInfoData.setLastRestartedonTime("09/03/2016 22:08:09");
		nodeInfoData.setTags("AXZ, Oracle, RDS");
		NODE_INFO_LIST.add(nodeInfoData);
	}

	private static final List<JobData> JOB_DATA_LIST = new ArrayList<JobData>();

	static {
		JobData jobData = new JobData();
		jobData.setClusterID("CID1234");
		jobData.setNodeName("Zury");
		jobData.setJobName("Clear Cache");
		jobData.setJobDataId(1);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1234");
		jobData.setNodeName("Zury");
		jobData.setJobName("Clean Up Old process Handles");
		jobData.setJobDataId(2);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1234");
		jobData.setNodeName("Zury");
		jobData.setJobName("Mark Old Nodes as read");
		jobData.setJobDataId(3);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1234");
		jobData.setNodeName("Zury");
		jobData.setJobName("DB Backups");
		jobData.setJobDataId(4);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1234");
		jobData.setNodeName("Zury");
		jobData.setJobName("Auto Publish Pending issues");
		jobData.setJobDataId(5);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Stopped");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1236");
		jobData.setNodeName("Tiger");
		jobData.setJobName("DIGITS");
		jobData.setJobDataId(6);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Suspended");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1235");
		jobData.setNodeName("Hummer");
		jobData.setJobName("Tensorflow");
		jobData.setJobDataId(9);
		jobData.setLasRun("2016-03-06 11:23:1");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("NA");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1238");
		jobData.setNodeName("Casper");
		jobData.setJobName("Torch");
		jobData.setJobDataId(10);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1239");
		jobData.setNodeName("Casper");
		jobData.setJobName("Theano");
		jobData.setJobDataId(7);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1232");
		jobData.setNodeName("Magna");
		jobData.setJobName("DL application");
		jobData.setJobDataId(8);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Stopped");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

		jobData = new JobData();
		jobData.setClusterID("CID1236");
		jobData.setNodeName("Zury");
		jobData.setJobName("iRAY");
		jobData.setJobDataId(11);
		jobData.setLasRun("2016-03-06 11:23:11");
		jobData.setNextRun("2016-03-06 11:33:00");
		jobData.setStatus("Running");
		jobData.setOption("");
		JOB_DATA_LIST.add(jobData);

	}

	private static final List<AlertData> ALERT_DATA_LIST = new ArrayList<AlertData>();

	static {

		AlertData alertData = new AlertData();
		alertData.setNode("Oram");
		alertData.setPolicy("NY-BACk02-SYSTEM");
		alertData.setSchedule("Full");
		alertData.setAttempts(1);
		alertData.setFrequency(2);
		ALERT_DATA_LIST.add(alertData);

		alertData = new AlertData();
		alertData.setNode("Tiger");
		alertData.setPolicy("Full");
		alertData.setSchedule("LINUX-Selections");
		alertData.setAttempts(1);
		alertData.setFrequency(1);
		ALERT_DATA_LIST.add(alertData);

		alertData = new AlertData();
		alertData.setNode("Tiger");
		alertData.setPolicy("NY-BACk02-SYSTEM");
		alertData.setSchedule("Full");
		alertData.setAttempts(1);
		alertData.setFrequency(2);
		ALERT_DATA_LIST.add(alertData);

		alertData = new AlertData();
		alertData.setNode("Hummer");
		alertData.setPolicy("NY-BACk02-SYSTEM");
		alertData.setSchedule("Full");
		alertData.setAttempts(1);
		alertData.setFrequency(2);
		ALERT_DATA_LIST.add(alertData);

		alertData = new AlertData();
		alertData.setNode("Magna");
		alertData.setPolicy("NY-BACk02-SYSTEM");
		alertData.setSchedule("Full");
		alertData.setAttempts(1);
		alertData.setFrequency(2);
		
		alertData = new AlertData();
		alertData.setNode("Zury");
		alertData.setPolicy("NY-BACk02-SYSTEM");
		alertData.setSchedule("Full");
		alertData.setAttempts(2);
		alertData.setFrequency(4);
		
		alertData = new AlertData();
		alertData.setNode("Zury");
		alertData.setPolicy("LINUX-Selections");
		alertData.setSchedule("Full");
		alertData.setAttempts(5);
		alertData.setFrequency(1);
		ALERT_DATA_LIST.add(alertData);

	}

	public static List<JobData> getJobDataList() {
		return JOB_DATA_LIST;
	}

	public static List<AlertData> getAlertDataList() {
		return ALERT_DATA_LIST;
	}

	public static List<NodeInfoData> getNodeInfoList() {
		return NODE_INFO_LIST;
	}

	public static List<String> getClusterList() {
		return CLUSTER_LIST;
	}

	public static Map<String, AlertSummaryData> getAlertSummaryDataMap() {
		return ALERT_SUMMARY_DATA_MAP;
	}

	public static Map<String, NodeInfoData> getNodeInfoMap() {
		return NODE_INFO_MAP;
	}


}
