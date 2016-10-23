package com.tresbu.nvidia.web.controller;

import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_ENTER;
import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_EXIT;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tresbu.nvidia.alert.detail.AlertDetailData;
import com.tresbu.nvidia.alert.detail.AlertDetailIntrface;
import com.tresbu.nvidia.common.AppConstant;
import com.tresbu.nvidia.common.data.ClusterData;
import com.tresbu.nvidia.common.data.JobTaskData;
import com.tresbu.nvidia.common.data.LicenceKeyData;
import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.data.NodeInfoData;
import com.tresbu.nvidia.common.exception.NvidiaServiceException;
import com.tresbu.nvidia.common.exception.NvidiaServiceNoDataFoundException;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.common.util.AppUtilImpl;
import com.tresbu.nvidia.common.util.AppUtilIntrface;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.CustomerEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;
import com.tresbu.nvidia.dao.entity.Response;
import com.tresbu.nvidia.insert.service.NvidiaInsertDataService;
import com.tresbu.nvidia.json.pojo.Alert;
import com.tresbu.nvidia.json.pojo.ImagesClient;
import com.tresbu.nvidia.json.pojo.Node;
import com.tresbu.nvidia.json.pojo.job.AlertVerify;
import com.tresbu.nvidia.json.pojo.job.App;
import com.tresbu.nvidia.json.pojo.job.ContainerStream;
import com.tresbu.nvidia.json.pojo.job.JobDetails;
import com.tresbu.nvidia.json.pojo.job.LastTaskFailure;
import com.tresbu.nvidia.json.pojo.job.Task;
import com.tresbu.nvidia.json.pojo.job.Token;
import com.tresbu.nvidia.json.pojo.njob.Parameters;
import com.tresbu.nvidia.json.pojo.njob.Request;
import com.tresbu.nvidia.service.intrface.NvidiaServiceIntrface;
import com.tresbu.nvidia.utility.DataListWrapper;

@Controller
public class NvidiaController {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaController.class.getName());

	@Autowired
	private AppUtilIntrface mAppUtilIntrface;

	@Autowired
	private AlertDetailIntrface mAlertDetailIntrface;

	@Autowired
	private NvidiaServiceIntrface mNvidiaServiceIntrface;

	@Autowired
	private NvidiaInsertDataService mNvidiaInsertDataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap pModel) {
		pModel.addAttribute("message", "Nvidia");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String printLogOut(ModelMap pModel) {
		pModel.addAttribute("message", "Nvidia");
		return "login";
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ModelAndView jobsList(ModelMap pModel) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.jobsList()");
		pModel.addAttribute("message", "Nvidia");
		DataListWrapper dataListWrapper = new DataListWrapper();

		this.callNodeView(dataListWrapper, false);
		dataListWrapper.setDockerImages(ImagesClient.fetchIDockermages());
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.jobsList()");
		return new ModelAndView("jobs", "dataListWrapper", dataListWrapper);
	}

	@RequestMapping(value = "/nfsDetails", method = RequestMethod.GET)
	public ModelAndView nfsDetails(ModelMap pModel) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.nfsDetails()");
		pModel.addAttribute("message", "Nvidia");
		DataListWrapper dataListWrapper = new DataListWrapper();

		this.callNodeView(dataListWrapper, false);
		dataListWrapper.setDockerImages(ImagesClient.fetchIDockermages());
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.nfsDetails()");
		return new ModelAndView("nfsDetails", "dataListWrapper", dataListWrapper);
	}

	@RequestMapping(value = "/refresh/api/jobs", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView searchJobsList(ModelMap pModel) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.searchJobsList()");
		DataListWrapper dataListWrapper = new DataListWrapper();
		dataListWrapper.setDockerImages(ImagesClient.fetchIDockermages());
		LOGGER.info("*****************Reloaded Docker Images***********************");
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.searchJobsList()");
		return new ModelAndView("jobsview", "dataListWrapper", dataListWrapper);
	}

	@RequestMapping(value = "/rest/alert/create", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response createAlert(@RequestBody Alert pAlert) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.createAlert()");
		Response response = new Response();
		pAlert.getGroups();
		LOGGER.debug(pAlert.toString());

		response.setMessage("json object sucessfully");
		response.setStatusCode(200);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.createAlert()");
		return response;
	}

	@RequestMapping(value = "/rest/node/create", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response createNode(@RequestBody Node pNode) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.createNode()");

		Response response = new Response();

		LOGGER.debug(pNode.toString());

		LicenceKeyData licenceKeyData = AppUtilImpl.getLicenceKeyInfo(pNode.getNodeStatus().getLicenceKey());
		LOGGER.debug(" data " + licenceKeyData);
		try {
			CustomerEntity customerEntity = mNvidiaInsertDataService.getCustomerEntityByCustomerId(licenceKeyData.getCustomerId());

			if (customerEntity != null && customerEntity.getCustomerId() != null) {

				pNode.getNodeStatus().setClusterId(licenceKeyData.getClusterId());
				pNode.getNodeStatus().setSerialid(licenceKeyData.getSerialId());

				try {
					ClusterEntity clusterEntity = mNvidiaInsertDataService.getClusterEntityByClustId(licenceKeyData.getClusterId());

					if (clusterEntity != null && clusterEntity.getClusterId() != null) {
						try {
							clusterEntity.setClusterId(licenceKeyData.getClusterId());
							mNvidiaInsertDataService.updateClusterData(clusterEntity);
							NodeEntity nodeEntity2 = mNvidiaInsertDataService.getNodeEntityBySerialId(licenceKeyData.getSerialId());
							if (nodeEntity2 != null && nodeEntity2.getSerialId() != null) {
								LOGGER.debug("------------------------------------Node Details Begin--------------------------------------");
								LOGGER.debug("NodeEntity" + nodeEntity2.toString());
								LOGGER.debug("Status" + pNode.getNodeStatus().getStatus());
								LOGGER.debug("--------------------------------------Node Details End--------------------------------------");
								mNvidiaInsertDataService.updateNodeData(pNode);
							}
						} catch (NvidiaServiceNoDataFoundException e) {
							LOGGER.error(e.getMessage());
							LOGGER.debug("---------------------------------------");
							LOGGER.debug("Node data inserting into database...");
							LOGGER.debug("---------------------------------------");
							mNvidiaInsertDataService.insertNodeData(pNode);
						} catch (NvidiaServiceException e) {
							LOGGER.error(e);
						}
					}
				} catch (NvidiaServiceNoDataFoundException e) {
					LOGGER.error(e.getMessage());
					try {
						LOGGER.debug("---------------------------------------");
						LOGGER.debug("Cluster data inserting into database...");
						LOGGER.debug("---------------------------------------");
						ClusterEntity clusterEntity2 = new ClusterEntity();
						clusterEntity2.setClusterId(licenceKeyData.getClusterId());
						mNvidiaInsertDataService.insertClusterData(clusterEntity2);

						LOGGER.debug("---------------------------------------");
						LOGGER.debug("Node data inserting into database...");
						LOGGER.debug("---------------------------------------");
						mNvidiaInsertDataService.insertNodeData(pNode);
					} catch (NvidiaServiceException e1) {
						LOGGER.error(e1);
					}
				} catch (NvidiaServiceException e) {
					LOGGER.error(e.getMessage());
				}
				response.setMessage("node successfully created");
				response.setStatusCode(200);
			}
		} catch (NvidiaServiceNoDataFoundException e) {
			LOGGER.error(e.getMessage());
			response.setMessage("Customer does not exist...");
			response.setStatusCode(200);
		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
			response.setMessage(e.getMessage());
			response.setStatusCode(200);
		}

		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.createNode()");
		return response;

	}

	@RequestMapping(value = "/rest/cluster/create", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response createCluster(@RequestBody ClusterEntity pClusterEntity) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.createCluster()");
		Response response = new Response();
		try {
			LOGGER.debug(pClusterEntity.toString());

			mNvidiaInsertDataService.insertClusterData(pClusterEntity);
		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
		}
		response.setMessage("Cluster successfully created");
		response.setStatusCode(200);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.createCluster()");
		return response;

	}

	// Cluster NFS
	@RequestMapping(value = "/cluster/updateNfs", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ClusterEntity updateNfsDataToCluster(@RequestBody ClusterEntity pClusterEntity) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.updateNfsDataInCluster()");
		Response response = new Response();
		ClusterEntity clusterEntity = null;
		try {
			LOGGER.debug(pClusterEntity.toString());
			clusterEntity = mNvidiaInsertDataService.updateNfsDataInCluster(pClusterEntity);
			response.setMessage("Nfs Data successfully updated");
			response.setStatusCode(200);
		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
			response.setMessage("Nfs Data not successfully updated");
			response.setStatusCode(200);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.updateNfsDataToCluster()");
		return clusterEntity;

	}

	@RequestMapping(value = "/rest/job/create", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response createJob(@RequestBody JobEntity pJobEntity) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.createJob()");
		Response response = new Response();

		LOGGER.debug(pJobEntity.toString());
		try {
			mNvidiaInsertDataService.insertJobData(pJobEntity);
		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
		}
		response.setMessage("job successfully created");
		response.setStatusCode(200);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.createJob()");
		return response;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginToNvidia(@ModelAttribute("login") LoginData pLogin, Model pModelMap, HttpServletRequest request) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.loginToNvidia()");
		try {
			LoginData loginData = mNvidiaServiceIntrface.getUserLoginDetails(pLogin.getEmail(), pLogin.getPassword());
			LOGGER.debug(loginData);
			DataListWrapper dataListWrapper = new DataListWrapper();
			this.callNodeView(dataListWrapper, false);
			LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.loginToNvidia()");
			return new ModelAndView("nvidia", "dataListWrapper", dataListWrapper);
		} catch (NvidiaServiceNoDataFoundException e) {
			String IpAddress = request.getRemoteAddr();
			LOGGER.error("The IP Address of the user " + IpAddress);
			LOGGER.error(e.getMessage());
			String name = "Invalid Credentials";
			pModelMap.addAttribute("name", name);
			return new ModelAndView("login");
		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
			String name = "Invalid Credentials";
			pModelMap.addAttribute("name", name);
			return new ModelAndView("login");
		}

	}
	//
	// HttpServletRequest request) {
	//

	@RequestMapping(value = "/node/{nodeId}&{clusterId}", method = RequestMethod.GET)
	public ModelAndView getNodeByName(@PathVariable String nodeId, @PathVariable String clusterId, Model pModelMap) {

		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getNodeByName()");
		LOGGER.debug("NodeId----------" + nodeId + ", ClusterId:" + clusterId);

		DataListWrapper dataListWrapper = new DataListWrapper();
		
		//dataListWrapper.setServerIpAddress(mAppUtilIntrface.getServerIpAddress());
		// Get Server IP address
		dataListWrapper.setNvidiaHost(mAppUtilIntrface.getNvidiaHost());
		dataListWrapper.setBosunHost(mAppUtilIntrface.getBosunHost());
		dataListWrapper.setGrafanaHost(mAppUtilIntrface.getGrafanaHost());

		try {
			NodeInfoData nodeInfoData = mNvidiaServiceIntrface.getNodeInfoDataByNodeName(nodeId, clusterId);
			LOGGER.debug("Node Info data:--------------" + nodeInfoData);

			ClusterData clusterData = mNvidiaServiceIntrface.getClusterDataByClusterId(nodeInfoData.getClusterId());
			dataListWrapper.setClusterData(clusterData);

			pModelMap.addAttribute("clusterData", clusterData);
			pModelMap.addAttribute("nodeInfoData", nodeInfoData);

			this.getSerialIdJobDetailsByIpAddress(dataListWrapper, nodeInfoData.getNodeKey(), nodeInfoData.getIpAddress());
			AlertDetailData alertDetailData = mAlertDetailIntrface.getAllAlertDetailsBySerialIdAndClusterId(nodeInfoData.getName(), clusterId);
			this.convertAlertDataToDataListWrapper(dataListWrapper, alertDetailData);
			
		} catch (NvidiaServiceNoDataFoundException e) {
			LOGGER.error(e.getMessage());

		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
		}

		try {
			List<ClusterData> clusterDatas = mNvidiaServiceIntrface.getClusterDataList();
			dataListWrapper.setClusterDataList(clusterDatas);

			for (ClusterData clusterDataObject : clusterDatas) {
				try {
					List<NodeInfoData> nodeInfoDataList = mNvidiaServiceIntrface.getNodeInfoDataListByCluster(clusterDataObject.getClusterId());
					dataListWrapper.addAllNodeDataList(nodeInfoDataList);
				} catch (NvidiaServiceNoDataFoundException e) {
					LOGGER.error(e.getMessage());

				} catch (NvidiaServiceException e) {
					LOGGER.error(e);
				}
			}
		} catch (NvidiaServiceNoDataFoundException e) {
			LOGGER.error(e.getMessage());

		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getNodeByName()");
		return new ModelAndView("node", "dataListWrapper", dataListWrapper);
	}

	@RequestMapping(value = "/api/ack/key", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response getAcknowledgeStatusByKeyId(@RequestBody String pAlertKey) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getAcknowledgeStatusByKeyId()");
		Response response = new Response();

		LOGGER.debug(pAlertKey);

		String name = mAppUtilIntrface.getAcknowledgeStatus(pAlertKey);
		response.setMessage(name);
		response.setStatusCode(200);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getAcknowledgeStatusByKeyId()");
		return response;
	}

	@RequestMapping(value = "/rest/job/api", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response getResubmissionJobApi(@RequestParam String jobHost, @RequestBody Request jobRequest) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getResubmissionJobApi()");
		Response response = new Response();

		LOGGER.info(jobRequest.getId() + "----" + jobRequest.getContainer().getDocker().getImage());
		// + "------ContainerPort:"
		// + jobRequest.getPortDefinitions().get(0).getPort());

		String name = mAppUtilIntrface.getResubmissionJobApi(jobHost, jobRequest);
		response.setMessage(name);
		response.setStatusCode(200);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getResubmissionJobApi()");
		return response;
	}

	@RequestMapping(value = "/rest/job/delete", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Response deleteJobApi(@RequestBody String pId) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.deleteJobApi()");
		Response response = new Response();

		LOGGER.debug(pId);

		String name = mAppUtilIntrface.deleteJobApi(pId);
		response.setMessage(name);
		response.setStatusCode(200);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.deleteJobApi()");
		return response;
	}

	// / action/{keyId}&{actionId}

	@RequestMapping(value = "/rest/alert/notify", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response getAlertStatusByKeyId(@RequestBody AlertVerify pAlertVerify) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getAlertStatusByKeyId()");
		Response response = new Response();
		try {
			String name = mAppUtilIntrface.getAlertActionResponse(pAlertVerify.getKeyId(), pAlertVerify.getActionId());
			response.setMessage(name);
			response.setStatusCode(200);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getAlertStatusByKeyId()");
		return response;
	}

	@RequestMapping(value = "/nvidia", method = RequestMethod.GET)
	public ModelAndView getNvidia(@ModelAttribute("name") String name) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getNvidia()");
		DataListWrapper dataListWrapper = new DataListWrapper();

		this.callNodeView(dataListWrapper, false);
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getNvidia()");
		return new ModelAndView("nvidia", "dataListWrapper", dataListWrapper);
	}

	@RequestMapping(value = "/container", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response fetchContainerStream(@RequestBody ContainerStream pContainerStream) {
		LOGGER.info(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.fetchContainerStream()");
		Response response = new Response();
		try {
			String asyMsg = mAppUtilIntrface.getContainerStream(pContainerStream.getJobHost(), pContainerStream.getContainerName());
			response.setStatusCode(200);
			response.setMessage(asyMsg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			
		}
		LOGGER.info(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.fetchContainerStream()");
		return response;
	}

	@RequestMapping(value = "/rest/save/token", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response saveAuthToken(@RequestBody Token pToken) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.saveAuthToken()");
		Response response = new Response();
		try {
			Integer loginId = mNvidiaServiceIntrface.saveAuthToken(pToken.getUsername(), pToken.getToken());
			LOGGER.debug(loginId);
			response.setMessage("Token is Successfully saved...");
			response.setStatusCode(200);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.saveAuthToken()");
		return response;
	}

	@RequestMapping(value = "/rest/login/token", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Response getAuthToken(@RequestBody Token pToken) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getAuthToken()");
		Response response = new Response();
		try {
			String authToken = mNvidiaServiceIntrface.getAuthTokenByUser(pToken.getUsername());
			LOGGER.debug(authToken);
			response.setMessage(authToken);
			response.setStatusCode(200);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getAuthToken()");
		return response;
	}

	@RequestMapping(value = "/clusterInfo/{pClusterId}", method = RequestMethod.POST)
	public @ResponseBody List<String> getNodeInfo(@PathVariable String pClusterId) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getNodeInfo()");
		List<String> nodeNames = new LinkedList<String>();
		List<NodeInfoData> nodeInfo = null;
		try {
			ClusterData clusterData = mNvidiaServiceIntrface.getClusterDataByClusterId(pClusterId);
			nodeInfo = mNvidiaServiceIntrface.getNodeInfoDataListByCluster(pClusterId);
			for (NodeInfoData nodeInfoData : nodeInfo) {
				// ${nodeInfoData.name}~${nodeInfoData.gpuConfiguration}:${clusterData.nfs_one}
				String nodeName = nodeInfoData.getName() + AppConstant.CHARACTER_TILDE + nodeInfoData.getGpuConfiguration()
						+ AppConstant.CHARACTER_COLON + clusterData.getNfsone() + AppConstant.CHARACTER_HYPHEN + nodeInfoData.getIpAddress();
				nodeNames.add(nodeName);
			}
		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getNodeInfo()");
		return nodeNames;

	}

	private void callNodeView(DataListWrapper dataListWrapper, boolean clusterIdIsPassed) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.callNodeView()");
		try {
			List<ClusterData> clusterDataList = mNvidiaServiceIntrface.getClusterDataList();
			dataListWrapper.setClusterDataList(clusterDataList);

			dataListWrapper.setNvidiaHost(mAppUtilIntrface.getNvidiaHost());
			dataListWrapper.setBosunHost(mAppUtilIntrface.getBosunHost());
			dataListWrapper.setGrafanaHost(mAppUtilIntrface.getGrafanaHost());

			for (ClusterData clusterData : clusterDataList) {
				try {
					List<NodeInfoData> nodeInfoDataList = mNvidiaServiceIntrface.getNodeInfoDataListByCluster(clusterData.getClusterId());
					dataListWrapper.addAllNodeDataList(nodeInfoDataList);
					// for (NodeInfoData nodeInfoData : nodeInfoDataList) {
					// List<JobData> jobDatas =
					// mNvidiaServiceIntrface.getJobDataListByNode(nodeInfoData.getSerialId());
					// dataListWrapper.addAllJobDataList(jobDatas);
					// }
					if (clusterIdIsPassed != true) {
						LOGGER.debug(clusterData.getClusterId());
						// dataListWrapper.setClusterData(mNvidiaServiceIntrface.getClusterDataByClusterId(clusterData.getClusterId()));
						dataListWrapper.setClusterData(clusterData);
						clusterIdIsPassed = true;
					}
					for (NodeInfoData nodeInfoData : nodeInfoDataList) {
						// TODO need to pass NodeId here
						AlertDetailData alertDetailData = mAlertDetailIntrface.getAllAlertDetails(clusterData.getClusterId(), nodeInfoData.getName());

						this.convertAlertDataToDataListWrapper(dataListWrapper, alertDetailData);
						this.getJobDetails(dataListWrapper, nodeInfoData.getIpAddress());
					}

				} catch (NvidiaServiceNoDataFoundException e) {
					LOGGER.error(e.getMessage());

				} catch (NvidiaServiceException e) {
					LOGGER.error(e);
				}
			}

			//this.getJobDetails(dataListWrapper);

		} catch (NvidiaServiceNoDataFoundException e) {
			LOGGER.error(e.getMessage());

		} catch (NvidiaServiceException e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.callNodeView()");
	}

	private void convertAlertDataToDataListWrapper(DataListWrapper dataListWrapper, AlertDetailData pAlertDetailData) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.convertAlertDataToDataListWrapper()");
		if (pAlertDetailData != null) {

			dataListWrapper.addAllNeedAckList(pAlertDetailData.getNeedAckList());
			dataListWrapper.addAllAcknowledgeList(pAlertDetailData.getAcknowledgeList());
			dataListWrapper.addAllChildList(pAlertDetailData.getChildList());

			Long newCriticalCount = dataListWrapper.getCriticalCount() + pAlertDetailData.getCriticalCount();
			dataListWrapper.setCriticalCount(newCriticalCount);

			Long newAckCount = dataListWrapper.getAckCount() + pAlertDetailData.getAckCount();
			dataListWrapper.setAckCount(newAckCount);

			Long newNeedAckCount = dataListWrapper.getNeedAckCount() + pAlertDetailData.getNeedAckCount();
			dataListWrapper.setNeedAckCount(newNeedAckCount);

			Long newUnknownCount = dataListWrapper.getUnknownCount() + pAlertDetailData.getUnknownCount();
			dataListWrapper.setUnknownCount(newUnknownCount);

			Long newWarningCount = dataListWrapper.getWarningCount() + pAlertDetailData.getWarningCount();
			dataListWrapper.setWarningCount(newWarningCount);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.convertAlertDataToDataListWrapper()");
	}

	private void getJobDetails(DataListWrapper dataListWrapper, String pIpAddress) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getJobDetails()");
		try {
			List<JobDetails> jobDetailsList = mAppUtilIntrface.getJobDetailsFromJsonData();
			if (jobDetailsList != null) {
				this.getJobDetailsValues(dataListWrapper, jobDetailsList, true, pIpAddress);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getJobDetails()");
	}

	private void getSerialIdJobDetailsByIpAddress(DataListWrapper pDataListWrapper, String pNodeKey, String pIpAddress ) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getSerialIdJobDetailsByIpAddress()");
		try {
			List<JobDetails> jobDetailsList = mAppUtilIntrface.getJobDetailsFromJsonData();
			if (jobDetailsList != null) {
				this.getJobDetailsValues(pDataListWrapper, jobDetailsList, false, pIpAddress);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getSerialIdJobDetailsByIpAddress()");
	}

	private void getJobDetailsValues(DataListWrapper pDataListWrapper, List<JobDetails> pJobDetailsList, boolean pIsBasedOnClusterId, String pIpAddress) {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaController.getJobDetailsValues()");
		for (JobDetails jobDetails : pJobDetailsList) {
			if (jobDetails != null) {
				App app = jobDetails.getApp();
				if (app != null) {

					JobTaskData jobTaskData = new JobTaskData();
					jobTaskData.setAppId(app.getId());
					// TODO need add owner of job
					jobTaskData.setOwner("David Williams.");

					LastTaskFailure lastTaskFailure = app.getLastTaskFailure();
					if (lastTaskFailure != null) {
						jobTaskData.setHost(lastTaskFailure.getHost());
						jobTaskData.setSlaveId(lastTaskFailure.getSlaveId());
						jobTaskData.setState(lastTaskFailure.getState());
						jobTaskData.setTaskId(lastTaskFailure.getTaskId());
						jobTaskData.setVersion(lastTaskFailure.getVersion());
						String text = lastTaskFailure.getMessage();
						text = text != null ? text.replace(AppConstant.CHARACTER_NEWLINE, AppConstant.CHARACTER_EMPTY)
								.replace(AppConstant.CHARACTER_DOUBLE_QUOTES, AppConstant.CHARACTER_EMPTY) : AppConstant.CHARACTER_EMPTY;
						jobTaskData.setMessage(text.trim());
						jobTaskData.setTimestamp(lastTaskFailure.getTimestamp());
					}

					if (app.getTasksStaged() == 0 && app.getTasksRunning() == 0) {
						jobTaskData.setStatus("Failed to launch");
					}
					if (app.getContainer() != null && app.getContainer().getDocker() != null) {
						jobTaskData.setImage(app.getContainer().getDocker().getImage());
						// jobTaskData.setNetwork(app.getContainer().getDocker().getNetwork());
						for (Parameters parameter : app.getContainer().getDocker().getParameters()) {
							if (parameter.getKey().equals("label")) {
								// parameter.getValue();
								jobTaskData.setUniquename(parameter.getValue());
								break;
							}
						}
					}

					for (Task task : app.getTasks()) {
						if (task != null) {
							jobTaskData.setHost(task.getHost());
							if (task.getPorts() != null && !task.getPorts().isEmpty()) {
								// TODO review PORT index
								jobTaskData.setPort(task.getPorts().get(0));
							}

							// Note: If tasks are empty then no need to show job
							// details into ui
							// taskRunning=1 status-Launched,taskStaged=1
							// status=In
							// queue,taskRunning=0 status=Failed to launch
							// LastTaskFailure is required to show in ui
							if (app.getTasksStaged() == 1) {
								// If Started At is null when job taskStaged=1
								jobTaskData.setStartedAt(task.getStagedAt());
								jobTaskData.setStatus("In queue");
							} else if (app.getTasksRunning() == 1) {
								jobTaskData.setStartedAt(task.getStartedAt());
								jobTaskData.setStatus("Launched");

								//String pjobHost = jobTaskData.getHost();
								String puniqueName = jobTaskData.getUniquename();
								String name = mAppUtilIntrface.getNameFromTheJobResponse(pIpAddress, puniqueName);
								name = name != null ? name.replace(AppConstant.CHARACTER_DOUBLE_QUOTES, AppConstant.CHARACTER_EMPTY)
										.replace(AppConstant.CHARACTER_SLASH, AppConstant.CHARACTER_EMPTY) : AppConstant.CHARACTER_EMPTY;
								jobTaskData.setContainerName(name);
							} else {
								// TODO Need to implement Stopped status also
							}
						}
					}
					if (jobTaskData.getHost() != null && !jobTaskData.getHost().isEmpty()) {
						pDataListWrapper.addJobTaskData(jobTaskData);
					} else if (pIsBasedOnClusterId == true && app.getTasksStaged() == 0 && app.getTasksRunning() == 0 && lastTaskFailure == null
							&& app.getTasks().isEmpty()) {
						jobTaskData.setStatus("Waiting");
						pDataListWrapper.addJobTaskData(jobTaskData);
					}
				}

				pDataListWrapper.addJobDetails(jobDetails);
			}
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaController.getJobDetailsValues()");
	}

}
