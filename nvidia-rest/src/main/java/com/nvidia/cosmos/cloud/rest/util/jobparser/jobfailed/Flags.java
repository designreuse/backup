
package com.nvidia.cosmos.cloud.rest.util.jobparser.jobfailed;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "appc_simple_discovery_uri_prefix", "appc_store_dir", "authenticatee", "cgroups_cpu_enable_pids_and_tids_count",
		"cgroups_enable_cfs", "cgroups_hierarchy", "cgroups_limit_swap", "cgroups_root", "container_disk_watch_interval", "containerizers",
		"default_role", "disk_watch_interval", "docker", "docker_kill_orphans", "docker_registry", "docker_remove_delay", "docker_socket",
		"docker_stop_timeout", "docker_store_dir", "enforce_container_disk_quota", "executor_registration_timeout", "executor_shutdown_grace_period",
		"fetcher_cache_dir", "fetcher_cache_size", "frameworks_home", "gc_delay", "gc_disk_headroom", "hadoop_home", "help", "hostname",
		"hostname_lookup", "image_provisioner_backend", "initialize_driver_logging", "ip", "isolation", "launcher_dir", "log_dir", "logbufsecs",
		"logging_level", "master", "oversubscribed_resources_interval", "perf_duration", "perf_interval", "port", "qos_correction_interval_min",
		"quiet", "recover", "recovery_timeout", "registration_backoff_factor", "resources", "revocable_cpu_low_priority", "sandbox_directory",
		"strict", "switch_user", "systemd_enable_support", "systemd_runtime_directory", "version", "work_dir" })
public class Flags {

	@JsonProperty("appc_simple_discovery_uri_prefix")
	private String appcSimpleDiscoveryUriPrefix;
	@JsonProperty("appc_store_dir")
	private String appcStoreDir;
	@JsonProperty("authenticatee")
	private String authenticatee;
	@JsonProperty("cgroups_cpu_enable_pids_and_tids_count")
	private String cgroupsCpuEnablePidsAndTidsCount;
	@JsonProperty("cgroups_enable_cfs")
	private String cgroupsEnableCfs;
	@JsonProperty("cgroups_hierarchy")
	private String cgroupsHierarchy;
	@JsonProperty("cgroups_limit_swap")
	private String cgroupsLimitSwap;
	@JsonProperty("cgroups_root")
	private String cgroupsRoot;
	@JsonProperty("container_disk_watch_interval")
	private String containerDiskWatchInterval;
	@JsonProperty("containerizers")
	private String containerizers;
	@JsonProperty("default_role")
	private String defaultRole;
	@JsonProperty("disk_watch_interval")
	private String diskWatchInterval;
	@JsonProperty("docker")
	private String docker;
	@JsonProperty("docker_kill_orphans")
	private String dockerKillOrphans;
	@JsonProperty("docker_registry")
	private String dockerRegistry;
	@JsonProperty("docker_remove_delay")
	private String dockerRemoveDelay;
	@JsonProperty("docker_socket")
	private String dockerSocket;
	@JsonProperty("docker_stop_timeout")
	private String dockerStopTimeout;
	@JsonProperty("docker_store_dir")
	private String dockerStoreDir;
	@JsonProperty("enforce_container_disk_quota")
	private String enforceContainerDiskQuota;
	@JsonProperty("executor_registration_timeout")
	private String executorRegistrationTimeout;
	@JsonProperty("executor_shutdown_grace_period")
	private String executorShutdownGracePeriod;
	@JsonProperty("fetcher_cache_dir")
	private String fetcherCacheDir;
	@JsonProperty("fetcher_cache_size")
	private String fetcherCacheSize;
	@JsonProperty("frameworks_home")
	private String frameworksHome;
	@JsonProperty("gc_delay")
	private String gcDelay;
	@JsonProperty("gc_disk_headroom")
	private String gcDiskHeadroom;
	@JsonProperty("hadoop_home")
	private String hadoopHome;
	@JsonProperty("help")
	private String help;
	@JsonProperty("hostname")
	private String hostname;
	@JsonProperty("hostname_lookup")
	private String hostnameLookup;
	@JsonProperty("image_provisioner_backend")
	private String imageProvisionerBackend;
	@JsonProperty("initialize_driver_logging")
	private String initializeDriverLogging;
	@JsonProperty("ip")
	private String ip;
	@JsonProperty("isolation")
	private String isolation;
	@JsonProperty("launcher_dir")
	private String launcherDir;
	@JsonProperty("log_dir")
	private String logDir;
	@JsonProperty("logbufsecs")
	private String logbufsecs;
	@JsonProperty("logging_level")
	private String loggingLevel;
	@JsonProperty("master")
	private String master;
	@JsonProperty("oversubscribed_resources_interval")
	private String oversubscribedResourcesInterval;
	@JsonProperty("perf_duration")
	private String perfDuration;
	@JsonProperty("perf_interval")
	private String perfInterval;
	@JsonProperty("port")
	private String port;
	@JsonProperty("qos_correction_interval_min")
	private String qosCorrectionIntervalMin;
	@JsonProperty("quiet")
	private String quiet;
	@JsonProperty("recover")
	private String recover;
	@JsonProperty("recovery_timeout")
	private String recoveryTimeout;
	@JsonProperty("registration_backoff_factor")
	private String registrationBackoffFactor;
	@JsonProperty("resources")
	private String resources;
	@JsonProperty("revocable_cpu_low_priority")
	private String revocableCpuLowPriority;
	@JsonProperty("sandbox_directory")
	private String sandboxDirectory;
	@JsonProperty("strict")
	private String strict;
	@JsonProperty("switch_user")
	private String switchUser;
	@JsonProperty("systemd_enable_support")
	private String systemdEnableSupport;
	@JsonProperty("systemd_runtime_directory")
	private String systemdRuntimeDirectory;
	@JsonProperty("version")
	private String version;
	@JsonProperty("work_dir")
	private String workDir;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The appcSimpleDiscoveryUriPrefix
	 */
	@JsonProperty("appc_simple_discovery_uri_prefix")
	public String getAppcSimpleDiscoveryUriPrefix() {
		return appcSimpleDiscoveryUriPrefix;
	}

	/**
	 * 
	 * @param appcSimpleDiscoveryUriPrefix
	 *            The appc_simple_discovery_uri_prefix
	 */
	@JsonProperty("appc_simple_discovery_uri_prefix")
	public void setAppcSimpleDiscoveryUriPrefix(String appcSimpleDiscoveryUriPrefix) {
		this.appcSimpleDiscoveryUriPrefix = appcSimpleDiscoveryUriPrefix;
	}

	/**
	 * 
	 * @return The appcStoreDir
	 */
	@JsonProperty("appc_store_dir")
	public String getAppcStoreDir() {
		return appcStoreDir;
	}

	/**
	 * 
	 * @param appcStoreDir
	 *            The appc_store_dir
	 */
	@JsonProperty("appc_store_dir")
	public void setAppcStoreDir(String appcStoreDir) {
		this.appcStoreDir = appcStoreDir;
	}

	/**
	 * 
	 * @return The authenticatee
	 */
	@JsonProperty("authenticatee")
	public String getAuthenticatee() {
		return authenticatee;
	}

	/**
	 * 
	 * @param authenticatee
	 *            The authenticatee
	 */
	@JsonProperty("authenticatee")
	public void setAuthenticatee(String authenticatee) {
		this.authenticatee = authenticatee;
	}

	/**
	 * 
	 * @return The cgroupsCpuEnablePidsAndTidsCount
	 */
	@JsonProperty("cgroups_cpu_enable_pids_and_tids_count")
	public String getCgroupsCpuEnablePidsAndTidsCount() {
		return cgroupsCpuEnablePidsAndTidsCount;
	}

	/**
	 * 
	 * @param cgroupsCpuEnablePidsAndTidsCount
	 *            The cgroups_cpu_enable_pids_and_tids_count
	 */
	@JsonProperty("cgroups_cpu_enable_pids_and_tids_count")
	public void setCgroupsCpuEnablePidsAndTidsCount(String cgroupsCpuEnablePidsAndTidsCount) {
		this.cgroupsCpuEnablePidsAndTidsCount = cgroupsCpuEnablePidsAndTidsCount;
	}

	/**
	 * 
	 * @return The cgroupsEnableCfs
	 */
	@JsonProperty("cgroups_enable_cfs")
	public String getCgroupsEnableCfs() {
		return cgroupsEnableCfs;
	}

	/**
	 * 
	 * @param cgroupsEnableCfs
	 *            The cgroups_enable_cfs
	 */
	@JsonProperty("cgroups_enable_cfs")
	public void setCgroupsEnableCfs(String cgroupsEnableCfs) {
		this.cgroupsEnableCfs = cgroupsEnableCfs;
	}

	/**
	 * 
	 * @return The cgroupsHierarchy
	 */
	@JsonProperty("cgroups_hierarchy")
	public String getCgroupsHierarchy() {
		return cgroupsHierarchy;
	}

	/**
	 * 
	 * @param cgroupsHierarchy
	 *            The cgroups_hierarchy
	 */
	@JsonProperty("cgroups_hierarchy")
	public void setCgroupsHierarchy(String cgroupsHierarchy) {
		this.cgroupsHierarchy = cgroupsHierarchy;
	}

	/**
	 * 
	 * @return The cgroupsLimitSwap
	 */
	@JsonProperty("cgroups_limit_swap")
	public String getCgroupsLimitSwap() {
		return cgroupsLimitSwap;
	}

	/**
	 * 
	 * @param cgroupsLimitSwap
	 *            The cgroups_limit_swap
	 */
	@JsonProperty("cgroups_limit_swap")
	public void setCgroupsLimitSwap(String cgroupsLimitSwap) {
		this.cgroupsLimitSwap = cgroupsLimitSwap;
	}

	/**
	 * 
	 * @return The cgroupsRoot
	 */
	@JsonProperty("cgroups_root")
	public String getCgroupsRoot() {
		return cgroupsRoot;
	}

	/**
	 * 
	 * @param cgroupsRoot
	 *            The cgroups_root
	 */
	@JsonProperty("cgroups_root")
	public void setCgroupsRoot(String cgroupsRoot) {
		this.cgroupsRoot = cgroupsRoot;
	}

	/**
	 * 
	 * @return The containerDiskWatchInterval
	 */
	@JsonProperty("container_disk_watch_interval")
	public String getContainerDiskWatchInterval() {
		return containerDiskWatchInterval;
	}

	/**
	 * 
	 * @param containerDiskWatchInterval
	 *            The container_disk_watch_interval
	 */
	@JsonProperty("container_disk_watch_interval")
	public void setContainerDiskWatchInterval(String containerDiskWatchInterval) {
		this.containerDiskWatchInterval = containerDiskWatchInterval;
	}

	/**
	 * 
	 * @return The containerizers
	 */
	@JsonProperty("containerizers")
	public String getContainerizers() {
		return containerizers;
	}

	/**
	 * 
	 * @param containerizers
	 *            The containerizers
	 */
	@JsonProperty("containerizers")
	public void setContainerizers(String containerizers) {
		this.containerizers = containerizers;
	}

	/**
	 * 
	 * @return The defaultRole
	 */
	@JsonProperty("default_role")
	public String getDefaultRole() {
		return defaultRole;
	}

	/**
	 * 
	 * @param defaultRole
	 *            The default_role
	 */
	@JsonProperty("default_role")
	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	/**
	 * 
	 * @return The diskWatchInterval
	 */
	@JsonProperty("disk_watch_interval")
	public String getDiskWatchInterval() {
		return diskWatchInterval;
	}

	/**
	 * 
	 * @param diskWatchInterval
	 *            The disk_watch_interval
	 */
	@JsonProperty("disk_watch_interval")
	public void setDiskWatchInterval(String diskWatchInterval) {
		this.diskWatchInterval = diskWatchInterval;
	}

	/**
	 * 
	 * @return The docker
	 */
	@JsonProperty("docker")
	public String getDocker() {
		return docker;
	}

	/**
	 * 
	 * @param docker
	 *            The docker
	 */
	@JsonProperty("docker")
	public void setDocker(String docker) {
		this.docker = docker;
	}

	/**
	 * 
	 * @return The dockerKillOrphans
	 */
	@JsonProperty("docker_kill_orphans")
	public String getDockerKillOrphans() {
		return dockerKillOrphans;
	}

	/**
	 * 
	 * @param dockerKillOrphans
	 *            The docker_kill_orphans
	 */
	@JsonProperty("docker_kill_orphans")
	public void setDockerKillOrphans(String dockerKillOrphans) {
		this.dockerKillOrphans = dockerKillOrphans;
	}

	/**
	 * 
	 * @return The dockerRegistry
	 */
	@JsonProperty("docker_registry")
	public String getDockerRegistry() {
		return dockerRegistry;
	}

	/**
	 * 
	 * @param dockerRegistry
	 *            The docker_registry
	 */
	@JsonProperty("docker_registry")
	public void setDockerRegistry(String dockerRegistry) {
		this.dockerRegistry = dockerRegistry;
	}

	/**
	 * 
	 * @return The dockerRemoveDelay
	 */
	@JsonProperty("docker_remove_delay")
	public String getDockerRemoveDelay() {
		return dockerRemoveDelay;
	}

	/**
	 * 
	 * @param dockerRemoveDelay
	 *            The docker_remove_delay
	 */
	@JsonProperty("docker_remove_delay")
	public void setDockerRemoveDelay(String dockerRemoveDelay) {
		this.dockerRemoveDelay = dockerRemoveDelay;
	}

	/**
	 * 
	 * @return The dockerSocket
	 */
	@JsonProperty("docker_socket")
	public String getDockerSocket() {
		return dockerSocket;
	}

	/**
	 * 
	 * @param dockerSocket
	 *            The docker_socket
	 */
	@JsonProperty("docker_socket")
	public void setDockerSocket(String dockerSocket) {
		this.dockerSocket = dockerSocket;
	}

	/**
	 * 
	 * @return The dockerStopTimeout
	 */
	@JsonProperty("docker_stop_timeout")
	public String getDockerStopTimeout() {
		return dockerStopTimeout;
	}

	/**
	 * 
	 * @param dockerStopTimeout
	 *            The docker_stop_timeout
	 */
	@JsonProperty("docker_stop_timeout")
	public void setDockerStopTimeout(String dockerStopTimeout) {
		this.dockerStopTimeout = dockerStopTimeout;
	}

	/**
	 * 
	 * @return The dockerStoreDir
	 */
	@JsonProperty("docker_store_dir")
	public String getDockerStoreDir() {
		return dockerStoreDir;
	}

	/**
	 * 
	 * @param dockerStoreDir
	 *            The docker_store_dir
	 */
	@JsonProperty("docker_store_dir")
	public void setDockerStoreDir(String dockerStoreDir) {
		this.dockerStoreDir = dockerStoreDir;
	}

	/**
	 * 
	 * @return The enforceContainerDiskQuota
	 */
	@JsonProperty("enforce_container_disk_quota")
	public String getEnforceContainerDiskQuota() {
		return enforceContainerDiskQuota;
	}

	/**
	 * 
	 * @param enforceContainerDiskQuota
	 *            The enforce_container_disk_quota
	 */
	@JsonProperty("enforce_container_disk_quota")
	public void setEnforceContainerDiskQuota(String enforceContainerDiskQuota) {
		this.enforceContainerDiskQuota = enforceContainerDiskQuota;
	}

	/**
	 * 
	 * @return The executorRegistrationTimeout
	 */
	@JsonProperty("executor_registration_timeout")
	public String getExecutorRegistrationTimeout() {
		return executorRegistrationTimeout;
	}

	/**
	 * 
	 * @param executorRegistrationTimeout
	 *            The executor_registration_timeout
	 */
	@JsonProperty("executor_registration_timeout")
	public void setExecutorRegistrationTimeout(String executorRegistrationTimeout) {
		this.executorRegistrationTimeout = executorRegistrationTimeout;
	}

	/**
	 * 
	 * @return The executorShutdownGracePeriod
	 */
	@JsonProperty("executor_shutdown_grace_period")
	public String getExecutorShutdownGracePeriod() {
		return executorShutdownGracePeriod;
	}

	/**
	 * 
	 * @param executorShutdownGracePeriod
	 *            The executor_shutdown_grace_period
	 */
	@JsonProperty("executor_shutdown_grace_period")
	public void setExecutorShutdownGracePeriod(String executorShutdownGracePeriod) {
		this.executorShutdownGracePeriod = executorShutdownGracePeriod;
	}

	/**
	 * 
	 * @return The fetcherCacheDir
	 */
	@JsonProperty("fetcher_cache_dir")
	public String getFetcherCacheDir() {
		return fetcherCacheDir;
	}

	/**
	 * 
	 * @param fetcherCacheDir
	 *            The fetcher_cache_dir
	 */
	@JsonProperty("fetcher_cache_dir")
	public void setFetcherCacheDir(String fetcherCacheDir) {
		this.fetcherCacheDir = fetcherCacheDir;
	}

	/**
	 * 
	 * @return The fetcherCacheSize
	 */
	@JsonProperty("fetcher_cache_size")
	public String getFetcherCacheSize() {
		return fetcherCacheSize;
	}

	/**
	 * 
	 * @param fetcherCacheSize
	 *            The fetcher_cache_size
	 */
	@JsonProperty("fetcher_cache_size")
	public void setFetcherCacheSize(String fetcherCacheSize) {
		this.fetcherCacheSize = fetcherCacheSize;
	}

	/**
	 * 
	 * @return The frameworksHome
	 */
	@JsonProperty("frameworks_home")
	public String getFrameworksHome() {
		return frameworksHome;
	}

	/**
	 * 
	 * @param frameworksHome
	 *            The frameworks_home
	 */
	@JsonProperty("frameworks_home")
	public void setFrameworksHome(String frameworksHome) {
		this.frameworksHome = frameworksHome;
	}

	/**
	 * 
	 * @return The gcDelay
	 */
	@JsonProperty("gc_delay")
	public String getGcDelay() {
		return gcDelay;
	}

	/**
	 * 
	 * @param gcDelay
	 *            The gc_delay
	 */
	@JsonProperty("gc_delay")
	public void setGcDelay(String gcDelay) {
		this.gcDelay = gcDelay;
	}

	/**
	 * 
	 * @return The gcDiskHeadroom
	 */
	@JsonProperty("gc_disk_headroom")
	public String getGcDiskHeadroom() {
		return gcDiskHeadroom;
	}

	/**
	 * 
	 * @param gcDiskHeadroom
	 *            The gc_disk_headroom
	 */
	@JsonProperty("gc_disk_headroom")
	public void setGcDiskHeadroom(String gcDiskHeadroom) {
		this.gcDiskHeadroom = gcDiskHeadroom;
	}

	/**
	 * 
	 * @return The hadoopHome
	 */
	@JsonProperty("hadoop_home")
	public String getHadoopHome() {
		return hadoopHome;
	}

	/**
	 * 
	 * @param hadoopHome
	 *            The hadoop_home
	 */
	@JsonProperty("hadoop_home")
	public void setHadoopHome(String hadoopHome) {
		this.hadoopHome = hadoopHome;
	}

	/**
	 * 
	 * @return The help
	 */
	@JsonProperty("help")
	public String getHelp() {
		return help;
	}

	/**
	 * 
	 * @param help
	 *            The help
	 */
	@JsonProperty("help")
	public void setHelp(String help) {
		this.help = help;
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
	 * @return The hostnameLookup
	 */
	@JsonProperty("hostname_lookup")
	public String getHostnameLookup() {
		return hostnameLookup;
	}

	/**
	 * 
	 * @param hostnameLookup
	 *            The hostname_lookup
	 */
	@JsonProperty("hostname_lookup")
	public void setHostnameLookup(String hostnameLookup) {
		this.hostnameLookup = hostnameLookup;
	}

	/**
	 * 
	 * @return The imageProvisionerBackend
	 */
	@JsonProperty("image_provisioner_backend")
	public String getImageProvisionerBackend() {
		return imageProvisionerBackend;
	}

	/**
	 * 
	 * @param imageProvisionerBackend
	 *            The image_provisioner_backend
	 */
	@JsonProperty("image_provisioner_backend")
	public void setImageProvisionerBackend(String imageProvisionerBackend) {
		this.imageProvisionerBackend = imageProvisionerBackend;
	}

	/**
	 * 
	 * @return The initializeDriverLogging
	 */
	@JsonProperty("initialize_driver_logging")
	public String getInitializeDriverLogging() {
		return initializeDriverLogging;
	}

	/**
	 * 
	 * @param initializeDriverLogging
	 *            The initialize_driver_logging
	 */
	@JsonProperty("initialize_driver_logging")
	public void setInitializeDriverLogging(String initializeDriverLogging) {
		this.initializeDriverLogging = initializeDriverLogging;
	}

	/**
	 * 
	 * @return The ip
	 */
	@JsonProperty("ip")
	public String getIp() {
		return ip;
	}

	/**
	 * 
	 * @param ip
	 *            The ip
	 */
	@JsonProperty("ip")
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @return The isolation
	 */
	@JsonProperty("isolation")
	public String getIsolation() {
		return isolation;
	}

	/**
	 * 
	 * @param isolation
	 *            The isolation
	 */
	@JsonProperty("isolation")
	public void setIsolation(String isolation) {
		this.isolation = isolation;
	}

	/**
	 * 
	 * @return The launcherDir
	 */
	@JsonProperty("launcher_dir")
	public String getLauncherDir() {
		return launcherDir;
	}

	/**
	 * 
	 * @param launcherDir
	 *            The launcher_dir
	 */
	@JsonProperty("launcher_dir")
	public void setLauncherDir(String launcherDir) {
		this.launcherDir = launcherDir;
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
	 * @return The logbufsecs
	 */
	@JsonProperty("logbufsecs")
	public String getLogbufsecs() {
		return logbufsecs;
	}

	/**
	 * 
	 * @param logbufsecs
	 *            The logbufsecs
	 */
	@JsonProperty("logbufsecs")
	public void setLogbufsecs(String logbufsecs) {
		this.logbufsecs = logbufsecs;
	}

	/**
	 * 
	 * @return The loggingLevel
	 */
	@JsonProperty("logging_level")
	public String getLoggingLevel() {
		return loggingLevel;
	}

	/**
	 * 
	 * @param loggingLevel
	 *            The logging_level
	 */
	@JsonProperty("logging_level")
	public void setLoggingLevel(String loggingLevel) {
		this.loggingLevel = loggingLevel;
	}

	/**
	 * 
	 * @return The master
	 */
	@JsonProperty("master")
	public String getMaster() {
		return master;
	}

	/**
	 * 
	 * @param master
	 *            The master
	 */
	@JsonProperty("master")
	public void setMaster(String master) {
		this.master = master;
	}

	/**
	 * 
	 * @return The oversubscribedResourcesInterval
	 */
	@JsonProperty("oversubscribed_resources_interval")
	public String getOversubscribedResourcesInterval() {
		return oversubscribedResourcesInterval;
	}

	/**
	 * 
	 * @param oversubscribedResourcesInterval
	 *            The oversubscribed_resources_interval
	 */
	@JsonProperty("oversubscribed_resources_interval")
	public void setOversubscribedResourcesInterval(String oversubscribedResourcesInterval) {
		this.oversubscribedResourcesInterval = oversubscribedResourcesInterval;
	}

	/**
	 * 
	 * @return The perfDuration
	 */
	@JsonProperty("perf_duration")
	public String getPerfDuration() {
		return perfDuration;
	}

	/**
	 * 
	 * @param perfDuration
	 *            The perf_duration
	 */
	@JsonProperty("perf_duration")
	public void setPerfDuration(String perfDuration) {
		this.perfDuration = perfDuration;
	}

	/**
	 * 
	 * @return The perfInterval
	 */
	@JsonProperty("perf_interval")
	public String getPerfInterval() {
		return perfInterval;
	}

	/**
	 * 
	 * @param perfInterval
	 *            The perf_interval
	 */
	@JsonProperty("perf_interval")
	public void setPerfInterval(String perfInterval) {
		this.perfInterval = perfInterval;
	}

	/**
	 * 
	 * @return The port
	 */
	@JsonProperty("port")
	public String getPort() {
		return port;
	}

	/**
	 * 
	 * @param port
	 *            The port
	 */
	@JsonProperty("port")
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * 
	 * @return The qosCorrectionIntervalMin
	 */
	@JsonProperty("qos_correction_interval_min")
	public String getQosCorrectionIntervalMin() {
		return qosCorrectionIntervalMin;
	}

	/**
	 * 
	 * @param qosCorrectionIntervalMin
	 *            The qos_correction_interval_min
	 */
	@JsonProperty("qos_correction_interval_min")
	public void setQosCorrectionIntervalMin(String qosCorrectionIntervalMin) {
		this.qosCorrectionIntervalMin = qosCorrectionIntervalMin;
	}

	/**
	 * 
	 * @return The quiet
	 */
	@JsonProperty("quiet")
	public String getQuiet() {
		return quiet;
	}

	/**
	 * 
	 * @param quiet
	 *            The quiet
	 */
	@JsonProperty("quiet")
	public void setQuiet(String quiet) {
		this.quiet = quiet;
	}

	/**
	 * 
	 * @return The recover
	 */
	@JsonProperty("recover")
	public String getRecover() {
		return recover;
	}

	/**
	 * 
	 * @param recover
	 *            The recover
	 */
	@JsonProperty("recover")
	public void setRecover(String recover) {
		this.recover = recover;
	}

	/**
	 * 
	 * @return The recoveryTimeout
	 */
	@JsonProperty("recovery_timeout")
	public String getRecoveryTimeout() {
		return recoveryTimeout;
	}

	/**
	 * 
	 * @param recoveryTimeout
	 *            The recovery_timeout
	 */
	@JsonProperty("recovery_timeout")
	public void setRecoveryTimeout(String recoveryTimeout) {
		this.recoveryTimeout = recoveryTimeout;
	}

	/**
	 * 
	 * @return The registrationBackoffFactor
	 */
	@JsonProperty("registration_backoff_factor")
	public String getRegistrationBackoffFactor() {
		return registrationBackoffFactor;
	}

	/**
	 * 
	 * @param registrationBackoffFactor
	 *            The registration_backoff_factor
	 */
	@JsonProperty("registration_backoff_factor")
	public void setRegistrationBackoffFactor(String registrationBackoffFactor) {
		this.registrationBackoffFactor = registrationBackoffFactor;
	}

	/**
	 * 
	 * @return The resources
	 */
	@JsonProperty("resources")
	public String getResources() {
		return resources;
	}

	/**
	 * 
	 * @param resources
	 *            The resources
	 */
	@JsonProperty("resources")
	public void setResources(String resources) {
		this.resources = resources;
	}

	/**
	 * 
	 * @return The revocableCpuLowPriority
	 */
	@JsonProperty("revocable_cpu_low_priority")
	public String getRevocableCpuLowPriority() {
		return revocableCpuLowPriority;
	}

	/**
	 * 
	 * @param revocableCpuLowPriority
	 *            The revocable_cpu_low_priority
	 */
	@JsonProperty("revocable_cpu_low_priority")
	public void setRevocableCpuLowPriority(String revocableCpuLowPriority) {
		this.revocableCpuLowPriority = revocableCpuLowPriority;
	}

	/**
	 * 
	 * @return The sandboxDirectory
	 */
	@JsonProperty("sandbox_directory")
	public String getSandboxDirectory() {
		return sandboxDirectory;
	}

	/**
	 * 
	 * @param sandboxDirectory
	 *            The sandbox_directory
	 */
	@JsonProperty("sandbox_directory")
	public void setSandboxDirectory(String sandboxDirectory) {
		this.sandboxDirectory = sandboxDirectory;
	}

	/**
	 * 
	 * @return The strict
	 */
	@JsonProperty("strict")
	public String getStrict() {
		return strict;
	}

	/**
	 * 
	 * @param strict
	 *            The strict
	 */
	@JsonProperty("strict")
	public void setStrict(String strict) {
		this.strict = strict;
	}

	/**
	 * 
	 * @return The switchUser
	 */
	@JsonProperty("switch_user")
	public String getSwitchUser() {
		return switchUser;
	}

	/**
	 * 
	 * @param switchUser
	 *            The switch_user
	 */
	@JsonProperty("switch_user")
	public void setSwitchUser(String switchUser) {
		this.switchUser = switchUser;
	}

	/**
	 * 
	 * @return The systemdEnableSupport
	 */
	@JsonProperty("systemd_enable_support")
	public String getSystemdEnableSupport() {
		return systemdEnableSupport;
	}

	/**
	 * 
	 * @param systemdEnableSupport
	 *            The systemd_enable_support
	 */
	@JsonProperty("systemd_enable_support")
	public void setSystemdEnableSupport(String systemdEnableSupport) {
		this.systemdEnableSupport = systemdEnableSupport;
	}

	/**
	 * 
	 * @return The systemdRuntimeDirectory
	 */
	@JsonProperty("systemd_runtime_directory")
	public String getSystemdRuntimeDirectory() {
		return systemdRuntimeDirectory;
	}

	/**
	 * 
	 * @param systemdRuntimeDirectory
	 *            The systemd_runtime_directory
	 */
	@JsonProperty("systemd_runtime_directory")
	public void setSystemdRuntimeDirectory(String systemdRuntimeDirectory) {
		this.systemdRuntimeDirectory = systemdRuntimeDirectory;
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

	/**
	 * 
	 * @return The workDir
	 */
	@JsonProperty("work_dir")
	public String getWorkDir() {
		return workDir;
	}

	/**
	 * 
	 * @param workDir
	 *            The work_dir
	 */
	@JsonProperty("work_dir")
	public void setWorkDir(String workDir) {
		this.workDir = workDir;
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
