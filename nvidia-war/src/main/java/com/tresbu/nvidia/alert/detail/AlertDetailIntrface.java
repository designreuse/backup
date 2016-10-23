package com.tresbu.nvidia.alert.detail;

public interface AlertDetailIntrface {

	public AlertDetailData getAllAlertDetails(String pClusterId,String pNodeName);

	public AlertDetailData getAllNeedAckAlertDetails(String pNodeName);

	public AlertDetailData getAllAcknowledgeAlertDetails(String pNodeName);

	public AlertDetailData getAllCriticalAlertDetails(String pNodeName);

	public AlertDetailData getNeedAckAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId);

	public AlertDetailData getAcknowledgeAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId);

	public AlertDetailData getCriticalAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId);
	
	public AlertDetailData getAllAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId);
}
