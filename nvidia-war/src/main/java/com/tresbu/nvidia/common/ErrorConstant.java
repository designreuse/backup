package com.tresbu.nvidia.common;

public interface ErrorConstant {

	public static enum NVIDIA_ERROR_TYPES {
		USER("User"), SYSTEM("System"), WARNING("Warning"), INFO("Info");

		private final String type;

		NVIDIA_ERROR_TYPES(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	public interface NvidiaErrorDefinition {
		public String getDescription(String descriptionDetail);

		public String getSummary();

		public String getType();
	}

	public static enum NVIDIA_COMMON_ERROR {
		ERROR_UNABLE_TO_CREATE(NVIDIA_ERROR_TYPES.SYSTEM,
				"Unable to create the instance: %s",
				"Unable to create the instance: %s"), ERROR_UNABLE_TO_CONNECT_RESOURCE(
				NVIDIA_ERROR_TYPES.SYSTEM, "Unable to connect to resource: %s",
				"Unable to connect to resource: %s");

		private final NVIDIA_ERROR_TYPES errorType;
		private final String description;
		private final String summary;

		NVIDIA_COMMON_ERROR(NVIDIA_ERROR_TYPES errorType, String summary,
				String description) {
			this.errorType = errorType;
			this.description = description;
			this.summary = summary;
		}

		public String getDescription(String descriptionDetail) {
			return String.format(description, descriptionDetail);
		}

		public String getSummary(String summaryDetail) {
			return String.format(summary, summaryDetail);
		}

		public String getType() {
			return errorType.getType();
		}

	}

	// Error messages
	public static final String ERROR_UNABLE_TO_CONNECT_RESOURCE = "Unable to connect to resource. ";
	public static final String ERROR_FILE_NOT_FOUND = "Unable to locate/read the file. ";
	public static final String ERROR_UNABLE_TO_CLOSE_RESOURCE = "Unable to close the resource. ";
	public static final String ERROR_UNABLE_TO_LOAD_APPLICATION_CONFIGURATION = "Unable to load application configuration. ";
	public static final String ERROR_UNABLE_TO_LOCATE_APP_HOME = "Unable to locate application home. ";
	public static final String ERROR_UNABLE_TO_INIT_APP_CONFIG = "Unable to initialise the application configuration. ";
	public static final String ERROR_VALUE_NOT_SET = "The indicated field/property is mandatory and does not have any value set. ";

	public static final String ERROR_PROPERTY_CONFIG_MISSING = "Please specify the required property configurations. ";
	// Error messages
	public static final String ERROR_MESSAGE_PROPERTY_NOT_CONFIGURED = "Property not configured.";
}
