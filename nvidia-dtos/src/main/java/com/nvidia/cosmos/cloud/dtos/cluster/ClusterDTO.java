package com.nvidia.cosmos.cloud.dtos.cluster;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.dtos.BaseDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeDTO;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ClusterDTO implements BaseDTO {
	public static final int MAX_LENGTH_NAME = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ClusterDTO() {
	}

	/**
	 * 
	 */
	private String id;
	/**
	 * name of the cluster
	 */
	private String name;

	/**
	 * 
	 */
	private String ipAddress;

	/**
	 * this property is used to update cluster
	 */
	@NotEmpty
	@ApiModelProperty(required = true, value="Mandatory")
	private String nfsDetails;
	/**
	 * 
	 */
	private String serialNumber;

	private String upgradeOnBoot;
	/**
	 * 
	 */
	private List<NodeDTO> nodeDtoList = new LinkedList<NodeDTO>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the nfsDetails
	 */
	public String getNfsDetails() {
		return nfsDetails;
	}

	/**
	 * @param nfsDetails
	 *            the nfsDetails to set
	 */
	public void setNfsDetails(String nfsDetails) {
		this.nfsDetails = nfsDetails;
	}

	/**
	 * @return the nodeDtoList
	 */
	public List<NodeDTO> getNodeDtoList() {
		return nodeDtoList;
	}

	/**
	 * @param nodeDtoList
	 *            the nodeDtoList to set
	 */
	public void setNodeDtoList(List<NodeDTO> nodeDtoList) {
		this.nodeDtoList = nodeDtoList;
	}

	/**
	 * @param nodeDTO
	 *            the nodeDTO to add
	 */
	public void addNodeDTO(NodeDTO nodeDTO) {
		this.nodeDtoList.add(nodeDTO);
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 * @param ipAddress
	 * @param nfsDetails
	 * @param nodeDtoList
	 */
	public ClusterDTO(String name, String ipAddress, String nfsDetails, String serialNumber, List<NodeDTO> nodeDtoList) {
		super();
		this.name = name;
		this.ipAddress = ipAddress;
		this.nfsDetails = nfsDetails;
		this.serialNumber = serialNumber;
		this.nodeDtoList = nodeDtoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClusterDTO [name=" + name + ", ipAddress=" + ipAddress + ", nfsDetails=" + nfsDetails + ", serialNumber=" + serialNumber
				+ ", nodeDtoList=" + nodeDtoList + "]";
	}

	public String getUpgradeOnBoot() {
		return upgradeOnBoot;
	}

	public void setUpgradeOnBoot(String upgradeOnBoot) {
		this.upgradeOnBoot = upgradeOnBoot;
	}

}
