package com.tresbu.nvidia.common.logging;

import static com.tresbu.nvidia.common.AppConstant.CHARACTER_HYPHEN;
import static com.tresbu.nvidia.common.DateUtil.DATE_TIME_PATTERN;
import static com.tresbu.nvidia.common.DateUtil.format;

import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;

public class NvidiaRollingFileAppender extends DailyRollingFileAppender {
	private static final Logger LOGGER = NvidiaAppLogger.getLogger(NvidiaRollingFileAppender.class.getName());

	public static final String RANDOM_VALUE_PLACEHOLDER = "%rnd";

	private static final Calendar CALENDAR = Calendar.getInstance();
	private static final Random RANDOM = new Random();

	public void setFile(String fileName) {

		try {

			if (fileName.indexOf(RANDOM_VALUE_PLACEHOLDER) >= 0) {
				fileName = fileName.replaceAll(RANDOM_VALUE_PLACEHOLDER,
						format(CALENDAR.getTime(), DATE_TIME_PATTERN) + CHARACTER_HYPHEN + Math.abs(RANDOM.nextInt()));
			}

//			if (fileName.indexOf(CHARACTER_PERCENTAGE + PROPERTY_PROCESS_NAME_KEY) >= 0) {
//				String clientType;
//				clientType = NVIDIA + CHARACTER_HYPHEN + Thread.currentThread().getName();
//				if (clientType != null)
//					fileName = fileName.replaceAll(CHARACTER_PERCENTAGE + PROPERTY_PROCESS_NAME_KEY, clientType);
//			}

			LOGGER.info("Log file name generated: " + fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.setFile(fileName);
	}

}