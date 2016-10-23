package com.tresbu.nvidia.common.util;

import static com.tresbu.nvidia.common.AppConstant.CHARACTER_COMMA;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_EMPTY;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_EQUALS;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_SQUARE_BRACKET_CLOSE;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_SQUARE_BRACKET_OPEN;
import static com.tresbu.nvidia.common.AppConstant.NVIDIA_PROCESS_NAME;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.tresbu.nvidia.common.ReflectionHelper;
import com.tresbu.nvidia.common.exception.NvidiaException;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;

public class NvidiaCoreUtil {

	public static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaCoreUtil.class.getName());

	public static String buildToString(Object dataObject, Map<String, Method> toStringProperties) {

		StringBuilder keyValuePairString = new StringBuilder();

		keyValuePairString.append(dataObject.getClass().getSimpleName()).append(CHARACTER_SQUARE_BRACKET_OPEN);

		try {
			Map<String, Object> values = ReflectionHelper.invokeReadMethod(dataObject, toStringProperties);

			for (Entry<String, Object> entry : values.entrySet()) {
				keyValuePairString.append(entry.getKey() + CHARACTER_EQUALS + entry.getValue()).append(CHARACTER_COMMA);
			}

			int lastComma = keyValuePairString.lastIndexOf(CHARACTER_COMMA);
			keyValuePairString.replace(lastComma, lastComma + 2, CHARACTER_EMPTY);

		} catch (IllegalArgumentException e) {
			LOGGER.fatal(e);
			throw new RuntimeException(e);
		} catch (NvidiaException e) {
			LOGGER.fatal(e);
			throw new RuntimeException(e);
		}

		keyValuePairString.append(CHARACTER_SQUARE_BRACKET_CLOSE);

		return keyValuePairString.toString();
	}

	// By default App process name is null it will take process type is empty .
	public static String getProcessType(String pAppProcessName) {

		String processType = "";
		if (pAppProcessName != null && !pAppProcessName.isEmpty()) {
			if (pAppProcessName.equals(NVIDIA_PROCESS_NAME) == true) {
				processType = "NVIDIA";
			}
		}

		return processType;
	}
}
