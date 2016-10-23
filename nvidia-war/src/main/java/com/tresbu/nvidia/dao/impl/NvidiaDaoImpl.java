package com.tresbu.nvidia.dao.impl;

import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_ENTER;
import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_EXIT;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_LOGIN_DTAT_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_NODE_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_LICENCE_KEY_NOT_FOUND;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_CLUSTER_DATA_BY_CLUSTER_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_CLUSTER_DATA_LIST_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_FROM_LOGIN;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_JOB_DATA_LIST_BY_NODE_ID_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_NODE_DATA_BY_NODE_ID_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_LOGIN_AUTH_TOKEN_BY_USER;

import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_CLUSTER_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_JOB_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_TOKEN_ALREADY_EXIST;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_NODE_DATA_BY_SERIAL_AND_CLUSTER_ID_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_NODE_DATA_LIST_BY_CLUSTER_ID_QUERY;
import static com.tresbu.nvidia.dao.util.NvidiaDataConvertionUtil.convertNvidiaDataToClusterEntityList;
import static com.tresbu.nvidia.dao.util.NvidiaDataConvertionUtil.convertNvidiaDataToJobEntityList;
import static com.tresbu.nvidia.dao.util.NvidiaDataConvertionUtil.convertNvidiaDataToNodeEntityList;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.UPDATE_LOGIN_AUTH_TOKEN;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tresbu.nvidia.common.data.AlertData;
import com.tresbu.nvidia.common.data.AlertSummaryData;
import com.tresbu.nvidia.common.data.JobData;
import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.data.NvidiaData;
import com.tresbu.nvidia.common.exception.NvidiaDBDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoNoDataFoundException;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;
import com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant;
import com.tresbu.nvidia.dao.intrface.NvidiaDao;
import com.tresbu.nvidia.dao.row.mapper.NvidiaDataRowMapper;
import com.tresbu.nvidia.model.store.NvidiaDataModelStore;

public class NvidiaDaoImpl implements NvidiaDao {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate mJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return mJdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate pJdbcTemplate) {
		this.mJdbcTemplate = pJdbcTemplate;
	}

	@Override
	public List<NodeEntity> getNodeInfoDataListClusterId(String pClusterId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getNodeInfoDataListClusterId()");
		List<NodeEntity> nodeEntities = null;
		try {
			LOGGER.debug("Cluster ID:" + pClusterId);
			Object[] parameters = { pClusterId };
			List<NvidiaData> nodeList = mJdbcTemplate.query(SELECT_NODE_DATA_LIST_BY_CLUSTER_ID_QUERY, parameters,
					new NvidiaDataRowMapper(new NodeEntity()));
			nodeEntities = convertNvidiaDataToNodeEntityList(nodeList);
			if (nodeEntities == null || nodeEntities.isEmpty()) {
				String errorDetail = "Cluster ID: " + pClusterId;
				throw new NvidiaDaoNoDataFoundException(NVIDIA_NODE_DATA_NOT_FOUND.getType(), NVIDIA_NODE_DATA_NOT_FOUND.getSummary(),
						NVIDIA_NODE_DATA_NOT_FOUND.getDescription(errorDetail));
			}
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Cluster ID: " + pClusterId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_NODE_DATA_NOT_FOUND.getType(), NVIDIA_NODE_DATA_NOT_FOUND.getSummary(),
					NVIDIA_NODE_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getNodeInfoDataListClusterId()");
		return nodeEntities;

	}

	// @Override
	// public NodeEntity getNodeInfoDataByClusterName(String pClusterName)
	// throws NvidiaDaoException {
	// LOGGER.debug(DEBUG_TRACE_METHOD_ENTER +
	// "NvidiaDaoImpl.getNodeInfoDataByClusterName()");
	// NodeEntity nodeEntity = null;
	// try {
	// LOGGER.debug("Cluster ID:" + pClusterName);
	// Object[] parameters = { pClusterName };
	// nodeEntity = (NodeEntity)
	// mJdbcTemplate.queryForObject(SELECT_NODE_DATA_BY_NAME_QUERY, parameters,
	// new NvidiaDataRowMapper(new NodeEntity()));
	//
	// if (nodeEntity == null) {
	// throw new NvidiaDaoNoDataFoundException("");
	// }
	// } catch (EmptyResultDataAccessException e) {
	// String errorDetail = "Cluster ID: " + pClusterName;
	// throw new
	// NvidiaDaoNoDataFoundException(NVIDIA_NODE_DATA_NOT_FOUND.getType(),
	// NVIDIA_NODE_DATA_NOT_FOUND.getSummary(),
	// NVIDIA_NODE_DATA_NOT_FOUND.getDescription(errorDetail));
	// } catch (DataAccessException e) {
	// throw new NvidiaDaoException(e);
	// } finally {
	// }
	// LOGGER.debug(DEBUG_TRACE_METHOD_EXIT +
	// "NvidiaDaoImpl.getNodeInfoDataByClusterName()");
	// return nodeEntity;
	// }

	@Override
	public NodeEntity getNodeEntityByNodeId(NodeEntity pNodeEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getNodeEntityByNodeId()");
		NodeEntity nodeEntity = null;
		try {

			LOGGER.debug("Serial ID:" + pNodeEntity.getSerialId());
			Object[] parameters = { pNodeEntity.getSerialId() };
			nodeEntity = (NodeEntity) mJdbcTemplate.queryForObject(SELECT_NODE_DATA_BY_NODE_ID_QUERY, parameters,
					new NvidiaDataRowMapper(new NodeEntity()));

		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "NodeName: " + pNodeEntity.getNodeName() + ",Cluster ID:" + pNodeEntity.getClusterId();
			throw new NvidiaDaoNoDataFoundException(NVIDIA_NODE_DATA_NOT_FOUND.getType(), NVIDIA_NODE_DATA_NOT_FOUND.getSummary(),
					NVIDIA_NODE_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getNodeEntityByNodeId()");
		return nodeEntity;

	}

	@Override
	public NodeEntity getNodeEntityBySerialAndClusterId(String pSerialId, String pClusterId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getNodeEntityBySerialAndClusterId()");
		NodeEntity nodeEntity = null;
		try {
			LOGGER.debug("SerialId:" + pSerialId + "ClusterId:" + pClusterId);
			Object[] parameters = { pSerialId, pClusterId };
			nodeEntity = (NodeEntity) mJdbcTemplate.queryForObject(SELECT_NODE_DATA_BY_SERIAL_AND_CLUSTER_ID_QUERY, parameters,
					new NvidiaDataRowMapper(new NodeEntity()));

		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Serial ID: " + pSerialId + ",Cluster ID:" + pClusterId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_NODE_DATA_NOT_FOUND.getType(), NVIDIA_NODE_DATA_NOT_FOUND.getSummary(),
					NVIDIA_NODE_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getNodeEntityBySerialAndClusterId()");
		return nodeEntity;
	}

	@Override
	public ClusterEntity getClusterDataByClusterId(String pClusterId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getClusterDataByClusterId()");
		ClusterEntity clusterEntity = null;
		try {
			LOGGER.debug("Cluster ID:" + pClusterId);
			Object[] parameters = { pClusterId };
			clusterEntity = (ClusterEntity) mJdbcTemplate.queryForObject(SELECT_CLUSTER_DATA_BY_CLUSTER_ID, parameters,
					new NvidiaDataRowMapper(new ClusterEntity()));

		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Cluster ID:" + pClusterId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_CLUSTER_DATA_NOT_FOUND.getType(), NVIDIA_CLUSTER_DATA_NOT_FOUND.getSummary(),
					NVIDIA_CLUSTER_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getClusterDataByClusterId()");
		return clusterEntity;
	}

	// Note When we are inserting data then catch duplicate exception
	// DuplicateKeyException
	@Override
	public LoginData getUserLoginDetails(String pUserName, String pPassword) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getUserLoginDetails()");
		LoginData loginData = null;
		try {
			LOGGER.debug("Username: " + pUserName + ",Password: " + pPassword);
			Object[] parameters = { pUserName, pPassword };
			loginData = (LoginData) mJdbcTemplate.queryForObject(SELECT_FROM_LOGIN, parameters, new NvidiaDataRowMapper(new LoginData()));
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Username: " + pUserName + ",Password: " + pPassword;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_LOGIN_DTAT_NOT_FOUND.getType(), NVIDIA_LOGIN_DTAT_NOT_FOUND.getSummary(),
					NVIDIA_LOGIN_DTAT_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getUserLoginDetails()");
		return loginData;
	}
	
	@Override
	public LoginData getUserLoginDetails(String pUserName) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getUserLoginDetails()");
		LoginData loginData = null;
		try {
			LOGGER.debug("Username: " + pUserName );
			Object[] parameters = { pUserName };
			loginData = (LoginData) mJdbcTemplate.queryForObject(NvidiaInsertDataDaoConstant.SELECT_FROM_LOGIN_USER, parameters, new NvidiaDataRowMapper(new LoginData()));
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Username: " + pUserName ;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_LOGIN_DTAT_NOT_FOUND.getType(), NVIDIA_LOGIN_DTAT_NOT_FOUND.getSummary(),
					NVIDIA_LOGIN_DTAT_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getUserLoginDetails()");
		return loginData;
	}


	@Override
	public List<ClusterEntity> getClusterEntities() throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getClusterEntities()");
		List<ClusterEntity> clusterEntities;
		try {
			List<NvidiaData> nvidiaDataList = mJdbcTemplate.query(SELECT_CLUSTER_DATA_LIST_QUERY, new NvidiaDataRowMapper(new ClusterEntity()));
			clusterEntities = convertNvidiaDataToClusterEntityList(nvidiaDataList);

			if (clusterEntities == null || clusterEntities.isEmpty()) {
				String errorDetail = "Cluster Details Data Not Found";
				throw new NvidiaDaoNoDataFoundException(NVIDIA_CLUSTER_DATA_NOT_FOUND.getType(), NVIDIA_CLUSTER_DATA_NOT_FOUND.getSummary(),
						NVIDIA_CLUSTER_DATA_NOT_FOUND.getDescription(errorDetail));
			}
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Cluster Details List Data Not Found";
			throw new NvidiaDaoNoDataFoundException(NVIDIA_CLUSTER_DATA_NOT_FOUND.getType(), NVIDIA_CLUSTER_DATA_NOT_FOUND.getSummary(),
					NVIDIA_CLUSTER_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getClusterEntities()");
		return clusterEntities;
	}

	@Override
	public List<JobEntity> getJobDataListBySerialId(String pSerialId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getJobDataListBySerialId()");
		List<JobEntity> jobEntities = null;
		try {
			LOGGER.debug("Serial ID:" + pSerialId);
			Object[] parameters = { pSerialId };
			List<NvidiaData> nvidiaDataList = mJdbcTemplate.query(SELECT_JOB_DATA_LIST_BY_NODE_ID_QUERY, parameters,
					new NvidiaDataRowMapper(new JobEntity()));
			jobEntities = convertNvidiaDataToJobEntityList(nvidiaDataList);

		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "SerialID:" + pSerialId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_JOB_DATA_NOT_FOUND.getType(), NVIDIA_JOB_DATA_NOT_FOUND.getSummary(),
					NVIDIA_JOB_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getJobDataListBySerialId()");
		return jobEntities;
	}

	@Override
	public String getLicenceKeyByNodeId(String pNodeId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getLicenceKeyByNodeId()");
		String licenceKey = null;
		try {
			LOGGER.debug("Node ID:" + pNodeId);
			Object[] parameters = { pNodeId };
			licenceKey = mJdbcTemplate.queryForObject(SELECT_FROM_LOGIN, parameters, String.class);
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Node ID:" + pNodeId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_LICENCE_KEY_NOT_FOUND.getType(), NVIDIA_LICENCE_KEY_NOT_FOUND.getSummary(),
					NVIDIA_LICENCE_KEY_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getLicenceKeyByNodeId()");
		return licenceKey;
	}

	@Override
	public Integer saveAuthToken(String pUsername, String pAuthToken) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.saveAuthToken()");
		Integer loginId = 0;
		try {
			LOGGER.debug("Auth Token:" + pAuthToken);
			Object[] parameters = { pAuthToken, pUsername };
			loginId = mJdbcTemplate.update(UPDATE_LOGIN_AUTH_TOKEN, parameters);
		} catch (DuplicateKeyException e) {
			String errorDetail = "UserName:" + pUsername;
			throw new NvidiaDBDaoException(NVIDIA_TOKEN_ALREADY_EXIST.getType(), NVIDIA_TOKEN_ALREADY_EXIST.getSummary(),
					NVIDIA_TOKEN_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.saveAuthToken()");
		return loginId;
	}

	@Override
	public String getAuthTokenByUser(String pUsername) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaDaoImpl.getAuthTokenByUser()");
		String authToken = null;
		try {
			LOGGER.debug("Username:" + pUsername);
			Object[] parameters = { pUsername };
			authToken = mJdbcTemplate.queryForObject(SELECT_LOGIN_AUTH_TOKEN_BY_USER, parameters, String.class);
		} catch (DuplicateKeyException e) {
			String errorDetail = "Username:" + pUsername;
			throw new NvidiaDBDaoException(NVIDIA_TOKEN_ALREADY_EXIST.getType(), NVIDIA_TOKEN_ALREADY_EXIST.getSummary(),
					NVIDIA_TOKEN_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaDaoImpl.getAuthTokenByUser()");
		return authToken;
	}

	@Override
	public List<AlertSummaryData> getAlertSummaryDataList() {
		return new ArrayList<AlertSummaryData>(NvidiaDataModelStore.getAlertSummaryDataMap().values());
	}

	@Override
	public List<AlertData> getAlertDataList() {
		return NvidiaDataModelStore.getAlertDataList();
	}

	@Override
	public List<AlertData> getAlertDataByClusterName(String pClusterName) {
		List<AlertData> alertDatas = new ArrayList<AlertData>();
		for (AlertData alertData : NvidiaDataModelStore.getAlertDataList()) {
			if (alertData.getNode().equals(pClusterName) == true) {
				alertDatas.add(alertData);
			}
		}
		return alertDatas;
	}

	@Override
	public List<JobData> getJobDataByClusterName(String pClusterName) {
		List<JobData> jobDatas = new ArrayList<JobData>();
		for (JobData jobData : NvidiaDataModelStore.getJobDataList()) {
			if (jobData.getNodeName().equals(pClusterName) == true) {
				jobDatas.add(jobData);
			}
		}
		return jobDatas;
	}

}
