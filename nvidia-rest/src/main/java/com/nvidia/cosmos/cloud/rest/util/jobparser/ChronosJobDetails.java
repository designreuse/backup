package com.nvidia.cosmos.cloud.rest.util.jobparser;

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
import com.nvidia.cosmos.cloud.dtos.job.Fetch;
import com.nvidia.cosmos.cloud.dtos.job.choronos.ChronosContainer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "command",
    "shell",
    "epsilon",
    "executor",
    "executorFlags",
    "taskInfoData",
    "retries",
    "owner",
    "ownerName",
    "description",
    "async",
    "successCount",
    "errorCount",
    "lastSuccess",
    "lastError",
    "cpus",
    "disk",
    "mem",
    "disabled",
    "softError",
    "dataProcessingJobType",
    "errorsSinceLastSuccess",
    "fetch",
    "uris",
    "environmentVariables",
    "arguments",
    "highPriority",
    "runAsUser",
    "container",
    "constraints",
    "schedule",
    "scheduleTimeZone"
})
public class ChronosJobDetails {

    @JsonProperty("name")
    private String name;
    @JsonProperty("command")
    private String command;
    @JsonProperty("shell")
    private Boolean shell;
    @JsonProperty("epsilon")
    private String epsilon;
    @JsonProperty("executor")
    private String executor;
    @JsonProperty("executorFlags")
    private String executorFlags;
    @JsonProperty("taskInfoData")
    private String taskInfoData;
    @JsonProperty("retries")
    private Integer retries;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("async")
    private Boolean async;
    @JsonProperty("successCount")
    private Integer successCount;
    @JsonProperty("errorCount")
    private Integer errorCount;
    @JsonProperty("lastSuccess")
    private String lastSuccess;
    @JsonProperty("lastError")
    private String lastError;
    @JsonProperty("cpus")
    private Double cpus;
    @JsonProperty("disk")
    private Double disk;
    @JsonProperty("mem")
    private Double mem;
    @JsonProperty("disabled")
    private Boolean disabled;
    @JsonProperty("softError")
    private Boolean softError;
    @JsonProperty("dataProcessingJobType")
    private Boolean dataProcessingJobType;
    @JsonProperty("errorsSinceLastSuccess")
    private Integer errorsSinceLastSuccess;
    @JsonProperty("fetch")
    private List<Fetch> fetch = new ArrayList<Fetch>();
    @JsonProperty("uris")
    private List<String> uris = new ArrayList<String>();
    @JsonProperty("environmentVariables")
    private List<Object> environmentVariables = new ArrayList<Object>();
    @JsonProperty("arguments")
    private List<Object> arguments = new ArrayList<Object>();
    @JsonProperty("highPriority")
    private Boolean highPriority;
    @JsonProperty("runAsUser")
    private String runAsUser;
    @JsonProperty("container")
    private ChronosContainer container;
    @JsonProperty("constraints")
    private List<Object> constraints = new ArrayList<Object>();
    @JsonProperty("schedule")
    private String schedule;
    @JsonProperty("scheduleTimeZone")
    private String scheduleTimeZone;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The command
     */
    @JsonProperty("command")
    public String getCommand() {
        return command;
    }

    /**
     * 
     * @param command
     *     The command
     */
    @JsonProperty("command")
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * 
     * @return
     *     The shell
     */
    @JsonProperty("shell")
    public Boolean getShell() {
        return shell;
    }

    /**
     * 
     * @param shell
     *     The shell
     */
    @JsonProperty("shell")
    public void setShell(Boolean shell) {
        this.shell = shell;
    }

    /**
     * 
     * @return
     *     The epsilon
     */
    @JsonProperty("epsilon")
    public String getEpsilon() {
        return epsilon;
    }

    /**
     * 
     * @param epsilon
     *     The epsilon
     */
    @JsonProperty("epsilon")
    public void setEpsilon(String epsilon) {
        this.epsilon = epsilon;
    }

    /**
     * 
     * @return
     *     The executor
     */
    @JsonProperty("executor")
    public String getExecutor() {
        return executor;
    }

    /**
     * 
     * @param executor
     *     The executor
     */
    @JsonProperty("executor")
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    /**
     * 
     * @return
     *     The executorFlags
     */
    @JsonProperty("executorFlags")
    public String getExecutorFlags() {
        return executorFlags;
    }

    /**
     * 
     * @param executorFlags
     *     The executorFlags
     */
    @JsonProperty("executorFlags")
    public void setExecutorFlags(String executorFlags) {
        this.executorFlags = executorFlags;
    }

    /**
     * 
     * @return
     *     The taskInfoData
     */
    @JsonProperty("taskInfoData")
    public String getTaskInfoData() {
        return taskInfoData;
    }

    /**
     * 
     * @param taskInfoData
     *     The taskInfoData
     */
    @JsonProperty("taskInfoData")
    public void setTaskInfoData(String taskInfoData) {
        this.taskInfoData = taskInfoData;
    }

    /**
     * 
     * @return
     *     The retries
     */
    @JsonProperty("retries")
    public Integer getRetries() {
        return retries;
    }

    /**
     * 
     * @param retries
     *     The retries
     */
    @JsonProperty("retries")
    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    /**
     * 
     * @return
     *     The owner
     */
    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The ownerName
     */
    @JsonProperty("ownerName")
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 
     * @param ownerName
     *     The ownerName
     */
    @JsonProperty("ownerName")
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The async
     */
    @JsonProperty("async")
    public Boolean getAsync() {
        return async;
    }

    /**
     * 
     * @param async
     *     The async
     */
    @JsonProperty("async")
    public void setAsync(Boolean async) {
        this.async = async;
    }

    /**
     * 
     * @return
     *     The successCount
     */
    @JsonProperty("successCount")
    public Integer getSuccessCount() {
        return successCount;
    }

    /**
     * 
     * @param successCount
     *     The successCount
     */
    @JsonProperty("successCount")
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    /**
     * 
     * @return
     *     The errorCount
     */
    @JsonProperty("errorCount")
    public Integer getErrorCount() {
        return errorCount;
    }

    /**
     * 
     * @param errorCount
     *     The errorCount
     */
    @JsonProperty("errorCount")
    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * 
     * @return
     *     The lastSuccess
     */
    @JsonProperty("lastSuccess")
    public String getLastSuccess() {
        return lastSuccess;
    }

    /**
     * 
     * @param lastSuccess
     *     The lastSuccess
     */
    @JsonProperty("lastSuccess")
    public void setLastSuccess(String lastSuccess) {
        this.lastSuccess = lastSuccess;
    }

    /**
     * 
     * @return
     *     The lastError
     */
    @JsonProperty("lastError")
    public String getLastError() {
        return lastError;
    }

    /**
     * 
     * @param lastError
     *     The lastError
     */
    @JsonProperty("lastError")
    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    /**
     * 
     * @return
     *     The cpus
     */
    @JsonProperty("cpus")
    public Double getCpus() {
        return cpus;
    }

    /**
     * 
     * @param cpus
     *     The cpus
     */
    @JsonProperty("cpus")
    public void setCpus(Double cpus) {
        this.cpus = cpus;
    }

    /**
     * 
     * @return
     *     The disk
     */
    @JsonProperty("disk")
    public Double getDisk() {
        return disk;
    }

    /**
     * 
     * @param disk
     *     The disk
     */
    @JsonProperty("disk")
    public void setDisk(Double disk) {
        this.disk = disk;
    }

    /**
     * 
     * @return
     *     The mem
     */
    @JsonProperty("mem")
    public Double getMem() {
        return mem;
    }

    /**
     * 
     * @param mem
     *     The mem
     */
    @JsonProperty("mem")
    public void setMem(Double mem) {
        this.mem = mem;
    }

    /**
     * 
     * @return
     *     The disabled
     */
    @JsonProperty("disabled")
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 
     * @param disabled
     *     The disabled
     */
    @JsonProperty("disabled")
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 
     * @return
     *     The softError
     */
    @JsonProperty("softError")
    public Boolean getSoftError() {
        return softError;
    }

    /**
     * 
     * @param softError
     *     The softError
     */
    @JsonProperty("softError")
    public void setSoftError(Boolean softError) {
        this.softError = softError;
    }

    /**
     * 
     * @return
     *     The dataProcessingJobType
     */
    @JsonProperty("dataProcessingJobType")
    public Boolean getDataProcessingJobType() {
        return dataProcessingJobType;
    }

    /**
     * 
     * @param dataProcessingJobType
     *     The dataProcessingJobType
     */
    @JsonProperty("dataProcessingJobType")
    public void setDataProcessingJobType(Boolean dataProcessingJobType) {
        this.dataProcessingJobType = dataProcessingJobType;
    }

    /**
     * 
     * @return
     *     The errorsSinceLastSuccess
     */
    @JsonProperty("errorsSinceLastSuccess")
    public Integer getErrorsSinceLastSuccess() {
        return errorsSinceLastSuccess;
    }

    /**
     * 
     * @param errorsSinceLastSuccess
     *     The errorsSinceLastSuccess
     */
    @JsonProperty("errorsSinceLastSuccess")
    public void setErrorsSinceLastSuccess(Integer errorsSinceLastSuccess) {
        this.errorsSinceLastSuccess = errorsSinceLastSuccess;
    }

    /**
     * 
     * @return
     *     The fetch
     */
    @JsonProperty("fetch")
    public List<Fetch> getFetch() {
        return fetch;
    }

    /**
     * 
     * @param fetch
     *     The fetch
     */
    @JsonProperty("fetch")
    public void setFetch(List<Fetch> fetch) {
        this.fetch = fetch;
    }

    /**
     * 
     * @return
     *     The uris
     */
    @JsonProperty("uris")
    public List<String> getUris() {
        return uris;
    }

    /**
     * 
     * @param uris
     *     The uris
     */
    @JsonProperty("uris")
    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    /**
     * 
     * @return
     *     The environmentVariables
     */
    @JsonProperty("environmentVariables")
    public List<Object> getEnvironmentVariables() {
        return environmentVariables;
    }

    /**
     * 
     * @param environmentVariables
     *     The environmentVariables
     */
    @JsonProperty("environmentVariables")
    public void setEnvironmentVariables(List<Object> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    /**
     * 
     * @return
     *     The arguments
     */
    @JsonProperty("arguments")
    public List<Object> getArguments() {
        return arguments;
    }

    /**
     * 
     * @param arguments
     *     The arguments
     */
    @JsonProperty("arguments")
    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    /**
     * 
     * @return
     *     The highPriority
     */
    @JsonProperty("highPriority")
    public Boolean getHighPriority() {
        return highPriority;
    }

    /**
     * 
     * @param highPriority
     *     The highPriority
     */
    @JsonProperty("highPriority")
    public void setHighPriority(Boolean highPriority) {
        this.highPriority = highPriority;
    }

    /**
     * 
     * @return
     *     The runAsUser
     */
    @JsonProperty("runAsUser")
    public String getRunAsUser() {
        return runAsUser;
    }

    /**
     * 
     * @param runAsUser
     *     The runAsUser
     */
    @JsonProperty("runAsUser")
    public void setRunAsUser(String runAsUser) {
        this.runAsUser = runAsUser;
    }

    /**
     * 
     * @return
     *     The container
     */
    @JsonProperty("container")
    public ChronosContainer getContainer() {
        return container;
    }

    /**
     * 
     * @param container
     *     The container
     */
    @JsonProperty("container")
    public void setContainer(ChronosContainer container) {
        this.container = container;
    }

    /**
     * 
     * @return
     *     The constraints
     */
    @JsonProperty("constraints")
    public List<Object> getConstraints() {
        return constraints;
    }

    /**
     * 
     * @param constraints
     *     The constraints
     */
    @JsonProperty("constraints")
    public void setConstraints(List<Object> constraints) {
        this.constraints = constraints;
    }

    /**
     * 
     * @return
     *     The schedule
     */
    @JsonProperty("schedule")
    public String getSchedule() {
        return schedule;
    }

    /**
     * 
     * @param schedule
     *     The schedule
     */
    @JsonProperty("schedule")
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * 
     * @return
     *     The scheduleTimeZone
     */
    @JsonProperty("scheduleTimeZone")
    public String getScheduleTimeZone() {
        return scheduleTimeZone;
    }

    /**
     * 
     * @param scheduleTimeZone
     *     The scheduleTimeZone
     */
    @JsonProperty("scheduleTimeZone")
    public void setScheduleTimeZone(String scheduleTimeZone) {
        this.scheduleTimeZone = scheduleTimeZone;
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

