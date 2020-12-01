package com.autopractice.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.autopractice.constants.IConstants;

public class ConfigReader {
	
	Properties prop = null;

	public void readConfig() {
		File file = new File(IConstants.propFilePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getEnvironment() {
		return prop.getProperty("env");
	}
	
	public String getBrowser() {
		return prop.getProperty("browser");
	}
	
	public String getPlatform() {
		return prop.getProperty("platform");
	}
	
	public String getURL() {
		return prop.getProperty("url");
	}
	
	public String getStoreURL() {
		return prop.getProperty("storeURL");
	}

}
