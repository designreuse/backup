package com.tresbu.nvidia.common.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class NvidiaAppLogger extends Logger {

	private static final NvidiaAppLoggerFactory NVIDIA_LOGGER_FACTORY = new NvidiaAppLoggerFactory();

	protected NvidiaAppLogger(String name) {
		super(name);
	}

	public void debug(Object message) {
		if (isDebugEnabled())
			super.debug(message);
	}

	public void error(Object message) {
		Object error = message;

		if (message instanceof Exception) {
			Exception e = (Exception) message;
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			error = sw.toString();
		}

		super.error(error);
	}

	public void fatal(Object message) {
		super.fatal(message);
	}

	public void info(Object message) {
		if (isInfoEnabled())
			super.info(message);
	}

	public static Logger getLogger(String name) {
		return Logger.getLogger(name, NVIDIA_LOGGER_FACTORY);
	}

}
