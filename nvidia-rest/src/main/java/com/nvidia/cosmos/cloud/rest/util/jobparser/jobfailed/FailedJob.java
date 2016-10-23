
package com.nvidia.cosmos.cloud.rest.util.jobparser.jobfailed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "attributes", "build_date", "build_time", "build_user", "completed_frameworks", "flags", "frameworks", "git_sha", "git_tag",
		"hostname", "id", "log_dir", "master_hostname", "pid", "resources", "start_time", "version" })
public class FailedJob {

	@JsonProperty("attributes")
	private Attributes attributes;
	@JsonProperty("build_date")
	private String buildDate;
	@JsonProperty("build_time")
	private Double buildTime;
	@JsonProperty("build_user")
	private String buildUser;
	@JsonProperty("completed_frameworks")
	private List<CompletedFramework> completedFrameworks = new ArrayList<CompletedFramework>();
	@JsonProperty("flags")
	private Flags flags;
	@JsonProperty("frameworks")
	private List<Object> frameworks = new ArrayList<Object>();
	@JsonProperty("git_sha")
	private String gitSha;
	@JsonProperty("git_tag")
	private String gitTag;
	@JsonProperty("hostname")
	private String hostname;
	@JsonProperty("id")
	private String id;
	@JsonProperty("log_dir")
	private String logDir;
	@JsonProperty("master_hostname")
	private String masterHostname;
	@JsonProperty("pid")
	private String pid;
	@JsonProperty("resources")
	private Resources resources;
	@JsonProperty("start_time")
	private Double startTime;
	@JsonProperty("version")
	private String version;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The attributes
	 */
	@JsonProperty("attributes")
	public Attributes getAttributes() {
		return attributes;
	}

	/**
	 * 
	 * @param attributes
	 *            The attributes
	 */
	@JsonProperty("attributes")
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * 
	 * @return The buildDate
	 */
	@JsonProperty("build_date")
	public String getBuildDate() {
		return buildDate;
	}

	/**
	 * 
	 * @param buildDate
	 *            The build_date
	 */
	@JsonProperty("build_date")
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	/**
	 * 
	 * @return The buildTime
	 */
	@JsonProperty("build_time")
	public Double getBuildTime() {
		return buildTime;
	}

	/**
	 * 
	 * @param buildTime
	 *            The build_time
	 */
	@JsonProperty("build_time")
	public void setBuildTime(Double buildTime) {
		this.buildTime = buildTime;
	}

	/**
	 * 
	 * @return The buildUser
	 */
	@JsonProperty("build_user")
	public String getBuildUser() {
		return buildUser;
	}

	/**
	 * 
	 * @param buildUser
	 *            The build_user
	 */
	@JsonProperty("build_user")
	public void setBuildUser(String buildUser) {
		this.buildUser = buildUser;
	}

	/**
	 * 
	 * @return The completedFrameworks
	 */
	@JsonProperty("completed_frameworks")
	public List<CompletedFramework> getCompletedFrameworks() {
		return completedFrameworks;
	}

	/**
	 * 
	 * @param completedFrameworks
	 *            The completed_frameworks
	 */
	@JsonProperty("completed_frameworks")
	public void setCompletedFrameworks(List<CompletedFramework> completedFrameworks) {
		this.completedFrameworks = completedFrameworks;
	}

	/**
	 * 
	 * @return The flags
	 */
	@JsonProperty("flags")
	public Flags getFlags() {
		return flags;
	}

	/**
	 * 
	 * @param flags
	 *            The flags
	 */
	@JsonProperty("flags")
	public void setFlags(Flags flags) {
		this.flags = flags;
	}

	/**
	 * 
	 * @return The frameworks
	 */
	@JsonProperty("frameworks")
	public List<Object> getFrameworks() {
		return frameworks;
	}

	/**
	 * 
	 * @param frameworks
	 *            The frameworks
	 */
	@JsonProperty("frameworks")
	public void setFrameworks(List<Object> frameworks) {
		this.frameworks = frameworks;
	}

	/**
	 * 
	 * @return The gitSha
	 */
	@JsonProperty("git_sha")
	public String getGitSha() {
		return gitSha;
	}

	/**
	 * 
	 * @param gitSha
	 *            The git_sha
	 */
	@JsonProperty("git_sha")
	public void setGitSha(String gitSha) {
		this.gitSha = gitSha;
	}

	/**
	 * 
	 * @return The gitTag
	 */
	@JsonProperty("git_tag")
	public String getGitTag() {
		return gitTag;
	}

	/**
	 * 
	 * @param gitTag
	 *            The git_tag
	 */
	@JsonProperty("git_tag")
	public void setGitTag(String gitTag) {
		this.gitTag = gitTag;
	}

	/**
	 * 
	 * @return The hostname
	 */
	@JsonProperty("hostname")
	public String getHostname() {
		return hostname;
	}

	/**
	 * 
	 * @param hostname
	 *            The hostname
	 */
	@JsonProperty("hostname")
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The logDir
	 */
	@JsonProperty("log_dir")
	public String getLogDir() {
		return logDir;
	}

	/**
	 * 
	 * @param logDir
	 *            The log_dir
	 */
	@JsonProperty("log_dir")
	public void setLogDir(String logDir) {
		this.logDir = logDir;
	}

	/**
	 * 
	 * @return The masterHostname
	 */
	@JsonProperty("master_hostname")
	public String getMasterHostname() {
		return masterHostname;
	}

	/**
	 * 
	 * @param masterHostname
	 *            The master_hostname
	 */
	@JsonProperty("master_hostname")
	public void setMasterHostname(String masterHostname) {
		this.masterHostname = masterHostname;
	}

	/**
	 * 
	 * @return The pid
	 */
	@JsonProperty("pid")
	public String getPid() {
		return pid;
	}

	/**
	 * 
	 * @param pid
	 *            The pid
	 */
	@JsonProperty("pid")
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 
	 * @return The resources
	 */
	@JsonProperty("resources")
	public Resources getResources() {
		return resources;
	}

	/**
	 * 
	 * @param resources
	 *            The resources
	 */
	@JsonProperty("resources")
	public void setResources(Resources resources) {
		this.resources = resources;
	}

	/**
	 * 
	 * @return The startTime
	 */
	@JsonProperty("start_time")
	public Double getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime
	 *            The start_time
	 */
	@JsonProperty("start_time")
	public void setStartTime(Double startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return The version
	 */
	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version
	 *            The version
	 */
	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
