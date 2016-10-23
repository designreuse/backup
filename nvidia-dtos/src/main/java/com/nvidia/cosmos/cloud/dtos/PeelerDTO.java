package com.nvidia.cosmos.cloud.dtos;

/**
 * This DTO will help to pass the Peeler URL for job and URI for stdout and stderr
 *
 * 
 */
public class PeelerDTO implements BaseDTO{

	private static final long serialVersionUID = 1L;

	private String messSlaveDir;
	
	private String host;
	
	private String dockerContainerName;
	
	public String getDockerContainerName() {
		return dockerContainerName;
	}

	public void setDockerContainerName(String dockerContainerName) {
		this.dockerContainerName = dockerContainerName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMessSlaveDir() {
		return messSlaveDir;
	}

	public void setMessSlaveDir(String messSlaveDir) {
		this.messSlaveDir = messSlaveDir;
	}

	private String pailerUri;
	
	
	public String getPailerUri() {
		return pailerUri;
	}

	public void setPailerUri(String pailerUri) {
		this.pailerUri = pailerUri;
	}

	public String getPailerHtml() {
		return pailerHtml;
	}

	public void setPailerHtml(String pailerHtml) {
		this.pailerHtml = pailerHtml;
	}

	public String getPailerLogs() {
		return pailerLogs;
	}

	public void setPailerLogs(String pailerLogs) {
		this.pailerLogs = pailerLogs;
	}

	private String pailerHtml;
	
	private String pailerLogs;

	
	
	
	
}
