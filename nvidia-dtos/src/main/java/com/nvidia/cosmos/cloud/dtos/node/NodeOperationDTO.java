/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos.node;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ram
 *
 */
public class NodeOperationDTO {

	@NotNull(message = "Command must not be Null!")
	@NotBlank(message = "Command must not be blank!")
	@NotEmpty(message = "Command must not be Empty!")
	@JsonProperty("command")
	private String command;

	@NotNull(message = "Serial Number must not be Null!")
	@NotBlank(message = "Serial Number must not be blank!")
	@NotEmpty(message = "Serial Number must not be Empty!")
	@JsonProperty("serial_number")
	private String serialNumber;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public NodeOperationDTO() {
	}
	
	public NodeOperationDTO(String command, String serialNumber) {
		super();
		this.command = command;
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "NodeOperationDTO [command=" + command + ", serialNumber=" + serialNumber + "]";
	}

	
}
