package com.nvidia.cosmos.cloud.services.eula.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import java.util.Date;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;

@Entity
@Table(name=ServicesConstants.EULA_TABLE_NAME)
public class Eula extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 */
	@Column(name = "FILE_NAME")
	private String fileName;

	/**
	 * 
	 */
	@Column(name = "UPLOAD_FILE")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] eulaContent;

	/**
	 * 
	 */
	@Column(name = "NAME",unique=true)
	private String name;

	/**
	 * 
	 */
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "UPDATED_DATE", nullable = true)
	private Date updatedDate;

	public Eula() {
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the eulaContent
	 */
	public byte[] getEulaContent() {
		return eulaContent;
	}

	/**
	 * @param eulaContent the eulaContent to set
	 */
	public void setEulaContent(byte[] eulaContent) {
		this.eulaContent = eulaContent;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	public Eula( String fileName, byte[] eulaContent, String name) {
		super();
		this.fileName = fileName;
		this.eulaContent = eulaContent;
		this.name = name;
		this.createdDate = new Date();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Eula [id=" + id + ", fileName=" + fileName + ", eulaContent=" + Arrays.toString(eulaContent) + ", name=" + name
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
