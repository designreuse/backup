package com.tresbu.nvidia.insert.dao;

import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_ALERT_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_ATTEMPTS_SUMMARY_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_ATTEMPT_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_CLUSTER_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_CUSTOMER_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_FREQUENCY_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_JOB_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_NODE_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_POLICY_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_SCHEDULE_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.INSERT_STATUS_TABLE_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_CLUSTER_DATA_BY_CLUSTER_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_CUSTOMER_DATA_BY_CUSTMER_ID;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.SELECT_NODE_DATA_BY_NODE_ID_QUERY;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.UPDATE_CLUSTER_DATA;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.UPDATE_NODE_DATA;
import static com.tresbu.nvidia.dao.insert.data.NvidiaInsertDataDaoConstant.UPDATE_NFS_DETAILS_OF_CLUSTER;
import static com.tresbu.nvidia.insert.dao.NvidiaDataToObjectArrayWrapper.convertNvidiaDataToObjectArray;

import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_CLUSTER_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_CUSTOMER_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_NODE_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_JOB_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_CUSTOMER_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_CLUSTER_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaControllerErrorDefinition.NVIDIA_NODE_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_ENTER;
import static com.tresbu.nvidia.common.AppConstant.DEBUG_TRACE_METHOD_EXIT;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tresbu.nvidia.common.DBConnection;
import com.tresbu.nvidia.common.exception.NvidiaDBDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoException;
import com.tresbu.nvidia.common.exception.NvidiaDaoNoDataFoundException;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
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
import com.tresbu.nvidia.dao.row.mapper.NvidiaDataRowMapper;

public class NvidiaInsertDataDaoImpl extends DBConnection implements NvidiaInsertDataDao {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaInsertDataDaoImpl.class.getName());
	@Autowired
	private JdbcTemplate mJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return mJdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate pJdbcTemplate) {
		this.mJdbcTemplate = pJdbcTemplate;
	}

	@Override
	public void updateClusterData(ClusterEntity pClusterEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.updateClusterData()");
		try {
			mJdbcTemplate.update(UPDATE_CLUSTER_DATA, convertNvidiaDataToObjectArray(pClusterEntity));
			LOGGER.info("Cluster data updated successfully into database...");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Cluster ID:" + pClusterEntity.getClusterId() + ", IP Address:" + pClusterEntity.getClusterIpAddress();
			throw new NvidiaDBDaoException(NVIDIA_CLUSTER_ALREADY_EXIST.getType(), NVIDIA_CLUSTER_ALREADY_EXIST.getSummary(),
					NVIDIA_CLUSTER_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.updateClusterData()");
	}

	@Override
	public void updateNodeData(NodeEntity pNodeEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.updateNodeData()");
		try {

			mJdbcTemplate.update(UPDATE_NODE_DATA,convertNvidiaDataToObjectArray(pNodeEntity));
			LOGGER.info("Node data updated successfully into database...");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Node ID:" + pNodeEntity.getNodeName() + ", Cluster ID:" + pNodeEntity.getClusterId();
			throw new NvidiaDBDaoException(NVIDIA_NODE_ALREADY_EXIST.getType(), NVIDIA_NODE_ALREADY_EXIST.getSummary(),
					NVIDIA_NODE_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.updateNodeData()");
	}

	@Override
	public void insertCustomerData(CustomerEntity pCustomerEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertCustomerData()");
		try {

			mJdbcTemplate.update(INSERT_CUSTOMER_TABLE_QUERY, convertNvidiaDataToObjectArray(pCustomerEntity));

			LOGGER.info("Customer data inserted successfully into database...");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Username:" + pCustomerEntity.getUsername();
			throw new NvidiaDBDaoException(NVIDIA_CUSTOMER_ALREADY_EXIST.getType(), NVIDIA_CUSTOMER_ALREADY_EXIST.getSummary(),
					NVIDIA_CUSTOMER_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertCustomerData()");
	}

	@Override
	public void insertCustomerDataList(List<CustomerEntity> pCustomerEntityList) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertCustomerDataList()");
		try {
			for (CustomerEntity customerEntity : pCustomerEntityList) {
				try {
					mJdbcTemplate.update(INSERT_CUSTOMER_TABLE_QUERY, convertNvidiaDataToObjectArray(customerEntity));
					LOGGER.debug("Customer data inserted successfully into database...");
				} catch (DuplicateKeyException e) {
					String errorDetail = "Username:" + customerEntity.getUsername();
					throw new NvidiaDBDaoException(NVIDIA_CUSTOMER_ALREADY_EXIST.getType(), NVIDIA_CUSTOMER_ALREADY_EXIST.getSummary(),
							NVIDIA_CUSTOMER_ALREADY_EXIST.getDescription(errorDetail));
				}

			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertCustomerDataList()");

	}

	@Override
	public void insertClusterData(ClusterEntity pClusterEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertClusterData()");
		try {

			mJdbcTemplate.update(INSERT_CLUSTER_TABLE_QUERY, convertNvidiaDataToObjectArray(pClusterEntity));
			LOGGER.debug("Cluster data inserted successfully into database...");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Cluster ID:" + pClusterEntity.getClusterId() + ", IP Address:" + pClusterEntity.getClusterIpAddress();
			throw new NvidiaDBDaoException(NVIDIA_CLUSTER_ALREADY_EXIST.getType(), NVIDIA_CLUSTER_ALREADY_EXIST.getSummary(),
					NVIDIA_CLUSTER_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertClusterData()");
	}

	@Override
	public void insertClusterDataList(List<ClusterEntity> pClusterEntityList) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertClusterDataList()");
		try {
			for (ClusterEntity clusterEntity : pClusterEntityList) {
				try {
					mJdbcTemplate.update(INSERT_CLUSTER_TABLE_QUERY, convertNvidiaDataToObjectArray(clusterEntity));
					LOGGER.debug("Cluster data inserted successfully into database...");
				} catch (DuplicateKeyException e) {
					String errorDetail = "Cluster ID:" + clusterEntity.getClusterId() + ", IP Address:" + clusterEntity.getClusterIpAddress();
					throw new NvidiaDBDaoException(NVIDIA_CLUSTER_ALREADY_EXIST.getType(), NVIDIA_CLUSTER_ALREADY_EXIST.getSummary(),
							NVIDIA_CLUSTER_ALREADY_EXIST.getDescription(errorDetail));
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertClusterDataList()");

	}

	@Override
	public void insertNodeDataList(List<NodeEntity> pNodeEntityList) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertNodeDataList()");
		try {

			for (NodeEntity nodeEntity : pNodeEntityList) {
				try {
					mJdbcTemplate.update(INSERT_NODE_TABLE_QUERY, convertNvidiaDataToObjectArray(nodeEntity));
					LOGGER.debug("Node data inserted successfully into database...");
				} catch (DuplicateKeyException e) {
					String errorDetail = "Node ID:" + nodeEntity.getNodeName() + ", Cluster ID:" + nodeEntity.getClusterId();
					throw new NvidiaDBDaoException(NVIDIA_NODE_ALREADY_EXIST.getType(), NVIDIA_NODE_ALREADY_EXIST.getSummary(),
							NVIDIA_NODE_ALREADY_EXIST.getDescription(errorDetail));
				}

			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertNodeDataList()");

	}

	@Override
	public void insertNodeData(NodeEntity pNodeEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertNodeData()");
		try {
			mJdbcTemplate.update(INSERT_NODE_TABLE_QUERY, convertNvidiaDataToObjectArray(pNodeEntity));
			LOGGER.debug("Node data inserted successfully into database...");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Node ID:" + pNodeEntity.getNodeName() + ", Cluster ID:" + pNodeEntity.getClusterId();
			throw new NvidiaDBDaoException(NVIDIA_NODE_ALREADY_EXIST.getType(), NVIDIA_NODE_ALREADY_EXIST.getSummary(),
					NVIDIA_NODE_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertNodeData()");
	}

	@Override
	public void insertJobData(JobEntity pJobEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertJobData()");
		try {

			mJdbcTemplate.update(INSERT_JOB_TABLE_QUERY, convertNvidiaDataToObjectArray(pJobEntity));

			LOGGER.debug("Job data inserted successfully into Job table...");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Job Name:" + pJobEntity.getJobName() + ", Node ID:" + pJobEntity.getNodeId();
			throw new NvidiaDBDaoException(NVIDIA_JOB_ALREADY_EXIST.getType(), NVIDIA_JOB_ALREADY_EXIST.getSummary(),
					NVIDIA_JOB_ALREADY_EXIST.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertJobData()");
	}

	@Override
	public void insertJobDataList(List<JobEntity> pJobEntityList) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.insertJobDataList()");
		try {
			for (JobEntity jobEntity : pJobEntityList) {
				try {
					mJdbcTemplate.update(INSERT_JOB_TABLE_QUERY, convertNvidiaDataToObjectArray(jobEntity));
					LOGGER.debug("Job data inserted successfully into Job table...");
				} catch (DuplicateKeyException e) {
					String errorDetail = "Job Name:" + jobEntity.getJobName() + ", Node ID:" + jobEntity.getNodeId();
					throw new NvidiaDBDaoException(NVIDIA_JOB_ALREADY_EXIST.getType(), NVIDIA_JOB_ALREADY_EXIST.getSummary(),
							NVIDIA_JOB_ALREADY_EXIST.getDescription(errorDetail));
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}

		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.insertJobDataList()");

	}

	@Override
	public CustomerEntity getCustomerEntityByCustId(String pCustomerId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.getCustomerEntityByCustId()");
		CustomerEntity customerEntity = null;
		try {

			LOGGER.debug("Customer ID:" + pCustomerId);
			Object[] params = { pCustomerId };
			customerEntity = (CustomerEntity) mJdbcTemplate.queryForObject(SELECT_CUSTOMER_DATA_BY_CUSTMER_ID, params,
					new NvidiaDataRowMapper(new CustomerEntity()));
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Username: " + pCustomerId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_CUSTOMER_DATA_NOT_FOUND.getType(), NVIDIA_CUSTOMER_DATA_NOT_FOUND.getSummary(),
					NVIDIA_CUSTOMER_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.getCustomerEntityByCustId()");
		return customerEntity;
	}

	@Override
	public ClusterEntity getClusterEntityByClustId(String pClusterId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.getClusterEntityByClustId()");
		ClusterEntity clusterEntity = null;
		try {
			Object[] params = { pClusterId };
			clusterEntity = (ClusterEntity) mJdbcTemplate.queryForObject(SELECT_CLUSTER_DATA_BY_CLUSTER_ID, params,
					new NvidiaDataRowMapper(new ClusterEntity()));
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "Cluster: " + pClusterId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_CLUSTER_DATA_NOT_FOUND.getType(), NVIDIA_CLUSTER_DATA_NOT_FOUND.getSummary(),
					NVIDIA_CLUSTER_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.getClusterEntityByClustId()");
		return clusterEntity;
	}

	@Override
	public NodeEntity getNodeEntityBySerialId(String pSerialId) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.getNodeEntityBySerialId()");
		NodeEntity nodeEntity = null;
		try {
			LOGGER.debug("SerialId:" + pSerialId);
			Object[] params = { pSerialId };
			nodeEntity = (NodeEntity) mJdbcTemplate.queryForObject(SELECT_NODE_DATA_BY_NODE_ID_QUERY, params,
					new NvidiaDataRowMapper(new NodeEntity()));
		} catch (EmptyResultDataAccessException e) {
			String errorDetail = "SerialId: " + pSerialId;
			throw new NvidiaDaoNoDataFoundException(NVIDIA_NODE_DATA_NOT_FOUND.getType(), NVIDIA_NODE_DATA_NOT_FOUND.getSummary(),
					NVIDIA_NODE_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			throw new NvidiaDaoException(e);
		} finally {
		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.getNodeEntityBySerialId()");
		return nodeEntity;
	}
	
	@Override
	public ClusterEntity updateNfsDataInCluster(ClusterEntity pClusterEntity) throws NvidiaDaoException {
		LOGGER.debug(DEBUG_TRACE_METHOD_ENTER + "NvidiaInsertDataDaoImpl.updateNfsDataInCluster()");
		ClusterEntity  clusterEntity = null;
		try {
			mJdbcTemplate.update(UPDATE_NFS_DETAILS_OF_CLUSTER, convertNvidiaDataToObjectArray(pClusterEntity));
			Object []params={pClusterEntity.getClusterId()};
			clusterEntity = (ClusterEntity) mJdbcTemplate.queryForObject(SELECT_CLUSTER_DATA_BY_CLUSTER_ID, params,
					new NvidiaDataRowMapper(new ClusterEntity()));
			LOGGER.info(" ClusterEntity Object " + clusterEntity );
			LOGGER.info(" NFS data of cluster as been updated successfully into database... ");
		} catch (DuplicateKeyException e) {
			String errorDetail = "Cluster ID:" + pClusterEntity.getClusterId();
			throw new NvidiaDBDaoException(NVIDIA_CLUSTER_DATA_NOT_FOUND.getType(),
					NVIDIA_CLUSTER_DATA_NOT_FOUND.getSummary(),
					NVIDIA_CLUSTER_DATA_NOT_FOUND.getDescription(errorDetail));
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new NvidiaDaoException(e);
		} finally {

		}
		LOGGER.debug(DEBUG_TRACE_METHOD_EXIT + "NvidiaInsertDataDaoImpl.updateNfsDataInCluster()");
		return clusterEntity;
	}

	@Override
	public void insertAlertData(List<AlertEntity> pAlertEntityList) {

		try {

			for (AlertEntity alertEntity : pAlertEntityList) {
				mPreparedStatement = prepareStatement(INSERT_ALERT_TABLE_QUERY);
				mPreparedStatement.setInt(1, alertEntity.getAlertId());
				mPreparedStatement.setString(2, alertEntity.getCluster().getClusterId());
				mPreparedStatement.setString(3, alertEntity.getNode().getSerialId());
				mPreparedStatement.setInt(4, alertEntity.getPolicyId());
				mPreparedStatement.setInt(5, alertEntity.getScheduleId());
				mPreparedStatement.setInt(6, alertEntity.getFrequencyId());
				mPreparedStatement.setInt(7, alertEntity.getAttemptsId());
				mPreparedStatement.execute();

			}

			System.out.println("alert data inserted successfully into database........");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

	@Override
	public void insertAlertSummaryData(List<AlertSummaryEntity> pAlertSummaryEntityList) {
		try {

			for (AlertSummaryEntity alertSummaryEntity : pAlertSummaryEntityList) {
				mPreparedStatement = prepareStatement(INSERT_ATTEMPTS_SUMMARY_TABLE_QUERY);
				mPreparedStatement.setString(1, alertSummaryEntity.getAlertMessage());
				mPreparedStatement.setTimestamp(2, alertSummaryEntity.getAlertDate());
				mPreparedStatement.setString(3, alertSummaryEntity.getNode().getNodeName());
				mPreparedStatement.setInt(5, alertSummaryEntity.getAlertSummaryId());
				mPreparedStatement.execute();

			}

			System.out.println("alertSummary data inserted successfully into database........");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

	@Override
	public void insertStatusData(List<StatusEntity> pStatusEntityList) {
		try {
			int i = 1;
			for (StatusEntity statusEntity : pStatusEntityList) {
				mPreparedStatement = prepareStatement(INSERT_STATUS_TABLE_QUERY);
				mPreparedStatement.setInt(1, i);
				mPreparedStatement.setString(2, statusEntity.getStatus());
				mPreparedStatement.execute();
				i++;
			}

			System.out.println("Status data inserted successfully into database........");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void insertPolicyData(List<PolicyEntity> pPolicyEntityList) {
		try {
			int i = 1;
			for (PolicyEntity policyEntity : pPolicyEntityList) {
				mPreparedStatement = prepareStatement(INSERT_POLICY_TABLE_QUERY);
				mPreparedStatement.setInt(1, i);
				mPreparedStatement.setString(2, policyEntity.getPolicy());
				mPreparedStatement.execute();
				i++;
			}
			System.out.println("policy data inserted successfully into database........");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void insertScheduleEntity(List<ScheduleEntity> pScheduleEntityList) {
		try {
			int i = 1;
			for (ScheduleEntity scheduleEntity : pScheduleEntityList) {
				mPreparedStatement = prepareStatement(INSERT_SCHEDULE_TABLE_QUERY);
				mPreparedStatement.setInt(1, i);
				mPreparedStatement.setString(2, scheduleEntity.getSchedule());
				mPreparedStatement.execute();
				i++;
			}

			System.out.println("schedule data inserted successfully into database........");

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void insertFrequencyData(List<FrequencyEntity> pFrequencyEntityList) {
		try {
			for (int i = 1; i <= 10; i++) {
				mPreparedStatement = prepareStatement(INSERT_FREQUENCY_TABLE_QUERY);
				mPreparedStatement.setInt(1, i);
				mPreparedStatement.setString(2, Integer.toString(i));
				mPreparedStatement.execute();
			}

			System.out.println("frequency data inserted successfully into database........");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void insertAttemptData(List<AttemptEntity> pAttemptEntityList) {
		try {
			for (int i = 1; i <= 10; i++) {
				mPreparedStatement = prepareStatement(INSERT_ATTEMPT_TABLE_QUERY);
				mPreparedStatement.setInt(1, i);
				mPreparedStatement.setString(2, Integer.toString(i));
				mPreparedStatement.execute();
			}

			System.out.println("attempts data inserted successfully into database........");

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

}
