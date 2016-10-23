package com.tresbu.nvidia.common.util;

import java.util.List;

import com.tresbu.nvidia.json.pojo.Alert;
import com.tresbu.nvidia.json.pojo.job.JobDetails;
import com.tresbu.nvidia.json.pojo.njob.Request;

public interface AppUtilIntrface {

	public Alert getAlertJsonDataFromBosun(String pNodeName) throws Exception;

	public String getAlertActionResponse(String pAlertKey,String pAlertAction) throws Exception;
	
	public String getAcknowledgeStatus(String pAlertKey) ;
	
	public List<JobDetails> getJobDetailsFromJsonData() throws Exception;
	
	public String[]  getNodeAndClusterIdFromAlertName(String pAlertName);
	
	public String  getNodeFromAlertName(String pNodeName,String pAlertName);
	
	public String getServerIpAddress();
	
	public String getNvidiaHost();
	
	public String getBosunHost();
	
	public String getGrafanaHost();
	
	public String getResubmissionJobApi(String pjobHost,Request jobRequest);
	
	public String deleteJobApi(String pId);
	
	public String getNameFromTheJobResponse(String pIpAddress, String puniqueName);
	
	public String getContainerStream(String pjobHost,String pContainerName) throws Exception;
}