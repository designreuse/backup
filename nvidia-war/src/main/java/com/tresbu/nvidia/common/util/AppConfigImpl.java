package com.tresbu.nvidia.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfigImpl implements AppConfigIntrface {

	@Override
	public Properties getProperties() throws Exception {

		Properties properties = new Properties();
		String propFileName = "config.properties";

		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file " + propFileName + "not found in classpath...");
			}
		} catch (IOException e) {
			throw e;
		}
		return properties;
	}
}
