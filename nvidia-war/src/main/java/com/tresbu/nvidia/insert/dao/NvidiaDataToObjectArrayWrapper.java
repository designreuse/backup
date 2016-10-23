package com.tresbu.nvidia.insert.dao;

import com.tresbu.nvidia.common.data.NvidiaData;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.CustomerEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;

public class NvidiaDataToObjectArrayWrapper {

	public static Object[] convertNvidiaDataToObjectArray(NvidiaData pNvidiaData) {
		Object[] params = null;
		if (pNvidiaData instanceof NodeEntity) {

			NodeEntity nodeEntity = (NodeEntity) pNvidiaData;
			// Update query
			// set serialid=?,node_name=?,ip_address=?,tags=?,created_time=?,
			// last_restarted_on_time=?,memory=?,disk_space=?,bios_version=?,status=?,
			// cluster_id=?,licence_key=?,node_id=?,mode=?,sw_version=?,
			// fw_version=?,ipmi=?,start_time=?,is_leader=?,subnet=?,cluster_group=?,
			// node_network_information=?,gateway=?,node_key=?,host_name=?

			// Insert query
			// serialid,node_name,ip_address,tags,created_time,last_restarted_on_time,
			// memory,disk_space,bios_version,status,cluster_id,licence_key,node_id,
			// mode,sw_version,fw_version,ipmi,start_time,is_leader,subnet,cluster_group,
			// node_network_information,gateway,node_key,"
			// cloud_managed=?,first_boot=?,
			// eula_accepted=?,serial_number=?,cloud_status=?,"
			// gpu_configuration=?,cloud_group=?,model_name=?";

			params = new Object[] {  nodeEntity.getNodeName(), nodeEntity.getIpAddress(), nodeEntity.getTags(),
					nodeEntity.getCreatedTime(), nodeEntity.getLastRestartedOnTime(), nodeEntity.getMemory(),
					nodeEntity.getDiskSpace(), nodeEntity.getBiosVersion(), nodeEntity.getStatus(),
					nodeEntity.getLicenceKey(), nodeEntity.getNodeId(), nodeEntity.getMode(), nodeEntity.getSwVersion(), nodeEntity.getFwVersion(),
					nodeEntity.getIpmi(), nodeEntity.getStartTime(), nodeEntity.getIsLeader(), nodeEntity.getSubnet(), nodeEntity.getClusterGroup(),
					nodeEntity.getNodenetworkinformation(), nodeEntity.getGateway(), nodeEntity.getKey(), nodeEntity.getCloudManaged(),
					nodeEntity.getFirstBoot(), nodeEntity.getEulaAccepted(), nodeEntity.getSerialNumber(), nodeEntity.getCloudStatus(),
					nodeEntity.getGpuConfiguration(), nodeEntity.getCloudGroup(), nodeEntity.getModelName(),nodeEntity.getSerialId(),nodeEntity.getClusterId() };

		} else if (pNvidiaData instanceof ClusterEntity) {
			// cluster_id=?,cluster_ip_address=?
			ClusterEntity clusterEntity = (ClusterEntity) pNvidiaData;
			params = new Object[] { clusterEntity.getClusterIpAddress(), clusterEntity.getNfsone(), clusterEntity.getNfstwo(), clusterEntity.getClusterId() };

		} else if (pNvidiaData instanceof JobEntity) {
			// insert query
			// node_id,job_name,last_run_date, next_run_date, status

			JobEntity jobEntity = (JobEntity) pNvidiaData;
			params = new Object[] { jobEntity.getJobId(), jobEntity.getJobName(), jobEntity.getLastRunDate(), jobEntity.getNextRunDate(),
					jobEntity.getStatus() };
		} else if (pNvidiaData instanceof CustomerEntity) {
			// username,email,password
			CustomerEntity customerEntity = (CustomerEntity) pNvidiaData;
			params = new Object[] { customerEntity.getUsername(), customerEntity.getEmail(), customerEntity.getPassword() };
		}
		return params;

	}
}
