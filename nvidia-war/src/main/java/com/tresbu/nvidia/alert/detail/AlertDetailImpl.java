package com.tresbu.nvidia.alert.detail;

import static com.tresbu.nvidia.common.DateUtil.convertStringToUtilDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.common.util.AppUtilIntrface;
import com.tresbu.nvidia.json.pojo.Acknowledged;
import com.tresbu.nvidia.json.pojo.Alert;
import com.tresbu.nvidia.json.pojo.Child;
import com.tresbu.nvidia.json.pojo.NeedAck;

public class AlertDetailImpl implements AlertDetailIntrface {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(AlertDetailImpl.class.getName());

	private static final String WARNING = "warning";
	private static final String ACKNOWLEDGE = "Acknowledge";
	private static final String NEED_ACK = "NeedAck";
	private static final String UNKNOWN = "unknown";
	private static final String CRITICAL = "critical";
	private static final String CHARACTER_DOUBLE_QUOTE = "";

	@Autowired
	private AppUtilIntrface mAppUtilIntrface;

	@Override
	public AlertDetailData getAllNeedAckAlertDetails(String pNodeName) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pNodeName);
			if (alert != null) {
				this.getNeedAckDetails(alertDetailData, alert, true);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getAllAcknowledgeAlertDetails(String pNodeName) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pNodeName);
			if (alert != null) {
				this.getAcknowledgedDetails(alertDetailData, alert, true);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getAllCriticalAlertDetails(String pNodeName) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pNodeName);
			if (alert != null) {
				// false :- we need all acknowledge with critical
				this.getAcknowledgedDetails(alertDetailData, alert, false);

				// false :- we need all needAcknowledge with critical
				this.getNeedAckDetails(alertDetailData, alert, false);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getNeedAckAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pSerialId);
			if (alert != null) {
				for (NeedAck needAck : alert.getGroups().getNeedAck()) {
					LOGGER.debug(needAck.getChildren());
					for (Child child : needAck.getChildren()) {
						// false :---- AllAlertCount is not required
						this.getChildDetails(alertDetailData, child, false, pSerialId, pClusterId);
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getAcknowledgeAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pSerialId);
			if (alert != null) {
				for (Acknowledged acknowledged : alert.getGroups().getAcknowledged()) {
					LOGGER.debug(acknowledged.getChildren());
					for (Child child : acknowledged.getChildren()) {
						// false :---- AllAlertCount is not required
						this.getChildDetails(alertDetailData, child, false, pSerialId, pClusterId);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getCriticalAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pSerialId);
			if (alert != null) {
				for (Acknowledged acknowledged : alert.getGroups().getAcknowledged()) {
					LOGGER.debug(acknowledged.getChildren());
					for (Child child : acknowledged.getChildren()) {
						this.getChildDetails(alertDetailData, child, false, pSerialId, pClusterId);
						if (child.getStatus().equals(CRITICAL) == true) {
							alertDetailData.addChild(child);
						}
					}
				}

				for (NeedAck needAck : alert.getGroups().getNeedAck()) {
					LOGGER.debug(needAck.getChildren());
					for (Child child : needAck.getChildren()) {
						this.getChildDetails(alertDetailData, child, false, pSerialId, pClusterId);
						if (child.getStatus().equals(CRITICAL) == true) {
							alertDetailData.addChild(child);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getAllAlertDetails(String pClusterId, String pNodeName) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			// TODO pNodeName need to pass
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pNodeName);

			if (alert != null) {
				LOGGER.debug("\n Alert :-------------------------------------" + alert.toString() + "\n");
				alertDetailData.setNodeName(pNodeName);
				alertDetailData.setClusterId(pClusterId);
				alertDetailData.setNeedAckList(alert.getGroups().getNeedAck());
				alertDetailData.setAcknowledgeList(alert.getGroups().getAcknowledged());

				// false :- we need all acknowledge with critical
				this.getAcknowledgedDetails(alertDetailData, alert, false);

				// false :- we need all need acknowledge with critical
				this.getNeedAckDetails(alertDetailData, alert, false);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return alertDetailData;
	}

	@Override
	public AlertDetailData getAllAlertDetailsBySerialIdAndClusterId(String pSerialId, String pClusterId) {
		AlertDetailData alertDetailData = new AlertDetailData();
		try {
			Alert alert = mAppUtilIntrface.getAlertJsonDataFromBosun(pSerialId);
			if (alert != null) {
				LOGGER.debug("\n Alert :" + alert.toString() + "\n");
				// Node name is nothing but pSerialId
				alertDetailData.setNodeName(pSerialId);
				alertDetailData.setClusterId(pClusterId);
				alertDetailData.setNeedAckList(alert.getGroups().getNeedAck());
				alertDetailData.setAcknowledgeList(alert.getGroups().getAcknowledged());

				for (Acknowledged acknowledged : alert.getGroups().getAcknowledged()) {
					LOGGER.debug(acknowledged.getChildren());
					for (Child child : acknowledged.getChildren()) {
						child.setAlertStatus(ACKNOWLEDGE);
						this.getChildDetails(alertDetailData, child, false, pSerialId, pClusterId);
						// if you want critical related child uncomment below
						// lines
						// if (child.getStatus().equals(CRITICAL) == true) {
						// alertDetailData.addChild(child);
						// }
					}

				}

				for (NeedAck needAck : alert.getGroups().getNeedAck()) {
					LOGGER.debug(needAck.getChildren());
					for (Child child : needAck.getChildren()) {
						child.setAlertStatus(NEED_ACK);
						this.getChildDetails(alertDetailData, child, false, pSerialId, pClusterId);
						// if you want critical related child uncomment below
						// lines
						// if (child.getStatus().equals(CRITICAL) == true) {
						// alertDetailData.addChild(child);
						// }
					}
				}
			}

		} catch (Exception e)

		{
			LOGGER.error(e);
		}
		return alertDetailData;

	}

	private void getNeedAckDetails(AlertDetailData pAlertDetailData, Alert pAlert, boolean pIsNeedAck) {
		for (NeedAck needAck : pAlert.getGroups().getNeedAck()) {
			LOGGER.debug(needAck.getChildren());
			for (Child child : needAck.getChildren()) {
				this.getChildDetails(pAlertDetailData, child, true);
				child.setAlertStatus(NEED_ACK);
				pAlertDetailData.addChild(child);
				// if you want critical related child uncomment below lines
				// if (pIsNeedAck == true) {
				// } else if (child.getStatus().equals(CRITICAL) == true) {
				// pAlertDetailData.addChild(child);
				//
				// }

			}
		}
	}

	private void getAcknowledgedDetails(AlertDetailData pAlertDetailData, Alert alert, boolean pIsAcknowledge) {
		for (Acknowledged acknowledged : alert.getGroups().getAcknowledged()) {
			LOGGER.debug(acknowledged.getChildren());
			for (Child child : acknowledged.getChildren()) {
				this.getChildDetails(pAlertDetailData, child, true);
				child.setAlertStatus(ACKNOWLEDGE);
				pAlertDetailData.addChild(child);
				// if you want critical related child uncomment below lines
				// if (pIsAcknowledge == true) {
				// pAlertDetailData.addChild(child);
				// } else if (child.getStatus().equals(CRITICAL) == true) {
				// pAlertDetailData.addChild(child);
				//
				// }
			}
		}
	}

	private Child getChildDetails(AlertDetailData pAlertDetailData, Child pChild, boolean pAllAlertCount, String... pNodeDetails) {

		LOGGER.debug("getChildDetails() method enter...");
		String alertMessage = mAppUtilIntrface.getNodeFromAlertName(pAlertDetailData.getNodeName(), pChild.getAlertKey());
		if (alertMessage != null && !alertMessage.isEmpty()) {

			String pSerialId = pNodeDetails.length != 0 ? pNodeDetails[0] : CHARACTER_DOUBLE_QUOTE;
			String pClusterId = pNodeDetails.length != 0 ? pNodeDetails[1] : CHARACTER_DOUBLE_QUOTE;

			if (pAllAlertCount == true) {
				this.getChildStatusCountDetails(pChild, pAlertDetailData.getClusterId(), pAlertDetailData.getNodeName(), alertMessage,
						pAlertDetailData);
			} else if (pAlertDetailData.getNodeName().equals(pSerialId) == true && pAlertDetailData.getClusterId().equals(pClusterId) == true) {
				this.getChildStatusCountDetails(pChild, pAlertDetailData.getClusterId(), pAlertDetailData.getNodeName(), alertMessage,
						pAlertDetailData);
				pAlertDetailData.addChild(pChild);
			}
		} else {
			String[] clusterSerialArray = mAppUtilIntrface.getNodeAndClusterIdFromAlertName(pChild.getAlertKey());

			String clusterId = clusterSerialArray != null ? clusterSerialArray[0].trim() : CHARACTER_DOUBLE_QUOTE;
			String serialId = clusterSerialArray != null ? clusterSerialArray[1].trim() : CHARACTER_DOUBLE_QUOTE;
			String alertName = clusterSerialArray != null ? clusterSerialArray[2] : CHARACTER_DOUBLE_QUOTE;

			String pSerialId = pNodeDetails.length != 0 ? pNodeDetails[0] : CHARACTER_DOUBLE_QUOTE;
			String pClusterId = pNodeDetails.length != 0 ? pNodeDetails[1] : CHARACTER_DOUBLE_QUOTE;
			if (pAllAlertCount == true) {
				this.getChildStatusCountDetails(pChild, clusterId, serialId, alertName, pAlertDetailData);
			} else if (serialId.equals(pSerialId) == true && clusterId.equals(pClusterId) == true) {
				this.getChildStatusCountDetails(pChild, clusterId, serialId, alertName, pAlertDetailData);
				pAlertDetailData.addChild(pChild);
			}
		}

		LOGGER.debug("getChildDetails() method exit...");
		return pChild;
	}

	private Child getChildStatusCountDetails(Child pChild, String pClusterId, String pSerialId, String pAlertName, AlertDetailData pAlertDetailData) {
		LOGGER.debug("getChildStatusCountDetails() method enter...");
		try {

			if (pChild != null) {

				pChild.setAlertDate(convertStringToUtilDate(pChild.getAgo()));
				pChild.setSerialId(pSerialId);
				pChild.setClusterId(pClusterId);
				pChild.setAlertName(pAlertName);

				if (pChild.getStatus().equals(CRITICAL) == true) {
					pAlertDetailData.addCriticalCount();
				}

				if (pChild.getStatus().equals(UNKNOWN) == true) {
					pAlertDetailData.addUnknownCount();
				}
				if (pChild.getState().isNeedAck() == true) {
					pAlertDetailData.addNeedAckCount();
				} else if (pChild.getState().isNeedAck() == false) {
					pAlertDetailData.addAckCount();
				}
				if (pChild.getStatus().equals(WARNING) == true) {
					pAlertDetailData.addWarningCount();
				}

			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug("getChildStatusCountDetails() method exit...");
		return pChild;
	}

}
