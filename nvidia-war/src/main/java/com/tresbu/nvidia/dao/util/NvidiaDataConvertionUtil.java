package com.tresbu.nvidia.dao.util;

import java.util.ArrayList;
import java.util.List;

import com.tresbu.nvidia.common.data.NvidiaData;
import com.tresbu.nvidia.dao.entity.ClusterEntity;
import com.tresbu.nvidia.dao.entity.JobEntity;
import com.tresbu.nvidia.dao.entity.NodeEntity;

public class NvidiaDataConvertionUtil {

	public static List<NodeEntity> convertNvidiaDataToNodeEntityList(List<NvidiaData> pNvidiaDataList) {
		List<NodeEntity> nodeEntities = new ArrayList<NodeEntity>();
		for (NvidiaData nvidiaData : pNvidiaDataList) {
			if (nvidiaData instanceof NodeEntity) {
				NodeEntity nodeEntity = (NodeEntity) nvidiaData;
				nodeEntities.add(nodeEntity);
			}
		}
		return nodeEntities;

	}

	public static List<ClusterEntity> convertNvidiaDataToClusterEntityList(List<NvidiaData> pNvidiaDataList) {
		List<ClusterEntity> clusterEntityList = new ArrayList<ClusterEntity>();
		for (NvidiaData nvidiaData : pNvidiaDataList) {
			if (nvidiaData instanceof ClusterEntity) {
				ClusterEntity clusterEntity = (ClusterEntity) nvidiaData;
				clusterEntityList.add(clusterEntity);
			}
		}
		return clusterEntityList;

	}

	public static List<JobEntity> convertNvidiaDataToJobEntityList(List<NvidiaData> pNvidiaDataList) {
		List<JobEntity> jobEntityList = new ArrayList<JobEntity>();
		for (NvidiaData nvidiaData : pNvidiaDataList) {
			if (nvidiaData instanceof JobEntity) {
				JobEntity jobEntity = (JobEntity) nvidiaData;
				jobEntityList.add(jobEntity);
			}
		}
		return jobEntityList;

	}
}
