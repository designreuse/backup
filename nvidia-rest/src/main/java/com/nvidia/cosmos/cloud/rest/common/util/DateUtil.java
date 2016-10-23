package com.nvidia.cosmos.cloud.rest.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

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
				logger.debug(pTimeStamp);
				timestamp = Timestamp.valueOf(pTimeStamp);
			}
		} catch (Exception e) {// this generic but you can control another types
			logger.error(e.getMessage());
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
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return utilDate;
	}

	public static GregorianCalendar getGregorianCalendar(Date inputDate) {
		GregorianCalendar gregorianCalendar = null;
		if (inputDate != null) {
			gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(inputDate);
		}
		return gregorianCalendar;
	}
}
