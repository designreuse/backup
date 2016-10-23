package com.tresbu.nvidia.alert.detail;

import java.util.ArrayList;
import java.util.List;

import com.tresbu.nvidia.json.pojo.Acknowledged;
import com.tresbu.nvidia.json.pojo.Child;
import com.tresbu.nvidia.json.pojo.NeedAck;

public class AlertDetailData {

	private String nodeName;
	private String clusterId;
	private List<NeedAck> needAckList = new ArrayList<NeedAck>();
	private List<Child> childList = new ArrayList<Child>();
	private List<Acknowledged> acknowledgeList = new ArrayList<Acknowledged>();
	private Long criticalCount = 0l;
	private Long ackCount = 0l;
	private Long needAckCount = 0l;
	private Long unknownCount = 0l;
	private Long warningCount = 0l;

	/**
	 * @return the clusterId
	 */
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * @param clusterId
	 *            the clusterId to set
	 */
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName
	 *            the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<NeedAck> getNeedAckList() {
		return needAckList;
	}

	public void setNeedAckList(List<NeedAck> needAckList) {
		this.needAckList = needAckList;
	}

	public List<Acknowledged> getAcknowledgeList() {
		return acknowledgeList;
	}

	public void setAcknowledgeList(List<Acknowledged> acknowledgeList) {
		this.acknowledgeList = acknowledgeList;
	}

	public List<Child> getChildList() {
		return childList;
	}

	public void setChildList(List<Child> childList) {
		this.childList = childList;
	}

	public void addChild(Child pChild) {
		this.childList.add(pChild);
	}

	public Long getCriticalCount() {
		return criticalCount;
	}

	public void setCriticalCount(Long criticalCount) {
		this.criticalCount = criticalCount;
	}

	public void addCriticalCount() {
		this.criticalCount++;
	}

	public Long getAckCount() {
		return ackCount;
	}

	public void setAckCount(Long ackCount) {
		this.ackCount = ackCount;
	}

	public void addAckCount() {
		this.ackCount++;
	}

	public Long getNeedAckCount() {
		return needAckCount;
	}

	public void setNeedAckCount(Long needAckCount) {
		this.needAckCount = needAckCount;
	}

	public void addNeedAckCount() {
		this.needAckCount++;
	}

	public Long getUnknownCount() {
		return unknownCount;
	}

	public void setUnknownCount(Long unknownCount) {
		this.unknownCount = unknownCount;
	}

	public void addUnknownCount() {
		this.unknownCount++;
	}

	/**
	 * @return the warningCount
	 */
	public Long getWarningCount() {
		return warningCount;
	}

	/**
	 * @param warningCount
	 *            the warningCount to set
	 */
	public void setWarningCount(Long warningCount) {
		this.warningCount = warningCount;
	}

	public void addWarningCount() {
		this.warningCount++;
	}

}
