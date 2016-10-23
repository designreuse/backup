package com.nvidia.cosmos.cloud.services.user.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nvidia.cosmos.cloud.common.model.AbstractModel;

//@Entity
//@Table(name=ServicesConstants.NV_GID)
public class GIDSequenceNumber extends AbstractModel {
	/**
	 * 
	 */
	public GIDSequenceNumber(){
		
	}
	private static final long serialVersionUID = 1L;
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}
}
