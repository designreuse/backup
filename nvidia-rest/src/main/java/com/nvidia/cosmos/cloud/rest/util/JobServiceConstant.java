package com.nvidia.cosmos.cloud.rest.util;

public class JobServiceConstant {

	// Job Status constants
	public static final String JOB_POST_SUCCESS = " posted successfully";
	public static final String JOB_IS_ALREADY_EXIST = " already exists";
	public static final String SERVICE_IS_UNREACHABLE = " Service Is Unreachable";
	public static final String JOB_BAD_REQUEST = " Bad Request";
	public static final String JOB_DELETE_SUCCESS = " Deleted successfully";
	public static final String JOB_NOT_DELETE_SUCCESS = " Not deleted successfully";

	// Marathon Job Endpoint constants
	public static final String MARATHON_V2_APPS = "/v2/apps";
	public static final String BEARER = "Bearer ";
	public static final String AUTHORIZATION = "Authorization";
	public static final String PRIVATE = "private";

	// Chronos Job Endpoint constants
	public static final String CHRONOS_SCHEDULER_GRAPH_CSV = "/scheduler/graph/csv";
	public static final String CHRONOS_SCHEDULER_JOB = "/scheduler/job/";
	public static final String CHRONOS_SCHEDULER_JOBS = "/scheduler/jobs";
	public static final String CHRONOS_SCHEDULER_ISO8601 = "/scheduler/iso8601";

	public static final String CONTAINER_JSON_LABEL = "/containers/json?label=";
	public static final String MESOS_SLAVE_STATE="/mesos-slave/state.json";
	public static final String MESOS_SLAVE_FILES_READ_PATH_PREFIX="/mesos-slave/files/read.json?path=";
	public static final String MESOS_SLAVE_SUFFIX="/stderr&offset=0";

	// Docker Repository URL Constant
	public static final String V1_REPOSITORY = "/v1/repository";
	public static final String V1_REPOSITORY_NAMESPACE = "/v1/repository?namespace=";

	// Jobs Character Constant
	public static final String CHARACTER_PERCENTAGE = "%";
	public static final String CHARACTER_HYPHEN = "-";
	public static final String CHARACTER_NEWLINE = "\n";
	public static final String CHARACTER_COMMA = ",";
	public static final String CHARACTER_DOUBLE_HYPON="--";
	public static final String CHARACTER_HASH = "#";
	public static final String CHARACTER_SQUARE_BRACKET_OPEN = "[";
	public static final String CHARACTER_SQUARE_BRACKET_CLOSE = "]";
	public static final String CHARACTER_EQUALS = "=";
	public static final String CHARACTER_DOT = ".";
	public static final String CHARACTER_EMPTY = "";
	public static final String CHARACTER_BACK_SLASH = "\\";
	public static final String CHARACTER_FRONT_SLASH = "//";
	public static final String CHARACTER_SINGLE_QUOTE = "'";
	public static final String CHARACTER_UNDERSCORE = "_";
	public static final String CHARACTER_COLON = ":";
	public static final String CHARACTER_AT = "@";
	public static final String CHARACTER_DOUBLE_QUOTE = "\"";
	public static final String UTF8 = "UTF8";
	public static final String CONTENT_TYPE = "content-type";
	public static final String CHARACTER_SINGLE_FRONT_SLASH = "/";

	// Web socket Server URL constants
	public static final String WS_RESTAPI_SERVER_URL = "websocket.retsapi.server.url";

	// Web socket server response constant
	public static final String WEB_SOCKET_APPLIANCE_IS_DOWN = "Connection to the appliance is down";
	public static final String WEB_SOCKET_APPLIANCE_IS_DOWN_EXCLAMATION = "Connection to the appliance is down!";
	public static final String SERIAL_NUMBER_IS_NOT_AUTHORIZED_TO_CONNECT = "Serial number is not authorized to connect";
	public static final String APPLIANCE_UNREACHABLE = "Appliance unreachable";

	//Register Message
	public static final String REGISTRATION_FAILED = "Registration failed, please contact Administrator";
	
	//Quay and Graphana message
	public static final String QUEY_FAILED = "Registration failed, please contact administrator";
	public static final String GRAPHANA_FAILED = "Registration failed, please contact administrator";
	
	// Job Json
	public static final String VOLUME = "volume";
	public static final String VOLUME_DRIVER = "volume-driver";
	public static final String USER = "user";
	public static final String DEVICE = "device";
	public static final String VOLUMES_FROM = "volumes-from";
	public static final String NFS = "nfs";
	public static final String KEY = "key";
	public static final String VALUE = "value";
	public static final String NVIDIA_VOLUMES= "nvidia-volumes";
	public static final String DEV_NVIDIACTL="/dev/nvidiactl";
	public static final String DEV_NVIDIA_UVM="/dev/nvidia-uvm";
	public static final String DEV_NVIDIA="/dev/nvidia";
	public static final String NFS_VOLUME="NFSVolume";
	public static final String CONTAINER_PORT="ContainerPort";
	public static final String LOCAL_VOLUME="LocalVolume";
	public static final String HOST_PORT="HostPort";
	public static final String DOCKER="DOCKER";
	public static final String UNIQUE="UNIQUE";
	public static final String HOSTNAME="hostname";
	public static final String TCP="tcp";
        public static final String SERIAL_NUMBER="serial_number";
        public static final String OWNER_NAME="ownerName";
        public static final String CREDS="creds";
	public static final String SECURITY_OPT="security-opt";
	public static final String NO_PRIVILEGES="no-new-privileges";
	
	
	// Repository Message Constant
	public static final String REPOSITORY_ALREADY_EXISTS = " repository already exists";
	public static final String REPOSITORY_SUCCESSFULLY_CREATION = " repository successfully created";
	public static final String REPOSITORY_SUCCESSFULLY_DELETED = " repository successfully deleted";
	public static final String REPOSITORY_NOT_SUCCESSFULLY_DELETED = " repository not successfully deleted";
	public static final String REPOSITORY_NOT_SUCCESSFULLY_CREATION = " repository not successfully created";
	public static final String INTERNAL_ERROR_PLEASE_TRY_AGAIN_LATER = "Internal error, please try again later";
	public static final String NVIDIA = "nvidia";

	// Job Web socket server Request Param constant
	public enum WebsocketServerRequest {
		SERIAL_NUMBER("serial_number"), COMMAND("command"), URI("uri"), OPERATION("operation"), PAYLOAD("payload"), FORWARD_DATA("forward_data"),USERCREDS("usercreds");

		private String name;

		WebsocketServerRequest(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	// Job Web socket server Request Method constant
	public enum WebsocketRequestMethod {
		POST("post"), GET("get"), DELETE("delete"), PUT("put");

		private String name;

		WebsocketRequestMethod(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	// Chronos Job Graph Csv Response Method constant
	public enum ChronosGraphCsvResponseMethod {

		NODE("node"), FRESH("fresh"), IDLE("idle"), SUCCESS("success"), QUEUED("queued"), RUNNING("running"), FAILURE("failure");

		private String name;

		ChronosGraphCsvResponseMethod(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	// Job Api type constant if it Marathon JOb or Chronos Job
	public enum JobApiType {
		MARATHON("marathon"), CHRONOS("chronos");

		private String name;

		JobApiType(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	// Pass prefix and suffix to below method and it will and return back one
	// final string
	public static String formatString(String prefix, String suffix) {
		String finalString = prefix != null && !prefix.isEmpty() ? prefix + suffix : suffix;
		return finalString;
	}

	public enum MarathonJobResponseStatus {
		FAILED_TO_LAUNCH("Failed"), IN_QUEUE("Queued"), LAUNCHED("Running"), WAITING("Queued");

		private String name;

		MarathonJobResponseStatus(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum ChronosJobResponseStatus {
		FINISHED("Finished");

		private String name;

		ChronosJobResponseStatus(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum JobStatus {
		YES("yes"), NO("no");

		private String name;

		JobStatus(String pName) {
			name = pName;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	// Job Json Constant
	public static final String LABEL = "label";
	// public static final String NAMES = "Names";
	public static final String ID = "Id";
	public static final String LABELS="Labels";
	public static final String EULAACCEPTED = "yes";
	public static final String EULAUPDATED = "EULA updated Succesfully";
	
	public static final String TAR_GZ=".tar.gz";
	public static final String DOCKERCFG="dockercfg";
	public static final String BRIDGE="BRIDGE";
	public static final String R0_P="R0//P";
}
