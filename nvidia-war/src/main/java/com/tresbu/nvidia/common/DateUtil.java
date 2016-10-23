package com.tresbu.nvidia.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.tresbu.nvidia.common.logging.NvidiaAppLogger;

public class DateUtil {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(DateUtil.class.getName());

	public static final String YYYY_MM_DD_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String DATE_TIME_PATTERN = "dd-MM-yyyy-HH.mm.ss";

	private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {

		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat();
		}
	};

	public static String timestampToString(Timestamp pTimestamp) {
		String strDate = null;
		if (pTimestamp != null) {
			strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(pTimestamp);
		}
		return strDate;
	}

	public static String format(Date date, String dateFormat) {
		String dateString = null;

		SIMPLE_DATE_FORMATTER.get().applyPattern(dateFormat);
		dateString = SIMPLE_DATE_FORMATTER.get().format(date);

		return dateString;
	}

	public static Timestamp convertStringToTimeStamp(String pTimeStamp) {
		Timestamp timestamp = null;
		try {
			if (pTimeStamp != null && !pTimeStamp.isEmpty()) {
				// String text = "2011-10-02 18:48:05.123";
				LOGGER.debug(pTimeStamp);
				timestamp = Timestamp.valueOf(pTimeStamp);
				LOGGER.debug(timestamp);
			}
		} catch (Exception e) {// this generic but you can control another types
			LOGGER.error(e);
		}
		return timestamp;
	}

	public static Date convertStringToUtilDate(String pDateString) {
		Date utilDate = null;
		try {
			if (pDateString != null && !pDateString.isEmpty()) {
				SimpleDateFormat isoFormat = new SimpleDateFormat(YYYY_MM_DD_DATE_TIME_PATTERN);
				isoFormat.setTimeZone(TimeZone.getTimeZone("UST"));
				utilDate = isoFormat.parse(pDateString);
				LOGGER.debug(utilDate);
			}
		} catch (ParseException e) {
			System.out.println(e);
		}
		return utilDate;
	}
}
