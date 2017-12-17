package com.ebay.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Commons {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(Commons.class);
	
	// Application specific properties
	//Admin Portal
	public static String PRODUCTION_URL;
	
	public Commons() {
		String propFilePath = "C:/gittigidiyor.properties";
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(propFilePath));
			logger.error(properties.toString());
			//Admin Portal
			PRODUCTION_URL = properties.getProperty("production_gittigidiyor_url"); 

		} catch (IOException e) {
			logger.error("Unable to read from ".concat(propFilePath));
			e.printStackTrace();
		}
	}
	
}
