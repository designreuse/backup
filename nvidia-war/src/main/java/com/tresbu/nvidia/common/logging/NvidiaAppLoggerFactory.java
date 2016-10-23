package com.tresbu.nvidia.common.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class NvidiaAppLoggerFactory implements LoggerFactory {

	public Logger makeNewLoggerInstance(String name) {
		return new NvidiaAppLogger(name);
	}
}
