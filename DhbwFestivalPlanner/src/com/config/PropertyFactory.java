package com.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {

	public static final String DB_PROP = "database.properties";
	public static final String HIB_PROP = "hibernate.properties";
	
	public Properties getDbProperties() throws IOException {
		return getProperties(DB_PROP);
	}
	
	public Properties getHibernateProperties() throws IOException{
		return getProperties(HIB_PROP);
	}
	
	private Properties getProperties(String path) throws IOException{
		Properties prop = new Properties();

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(path);
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + path
					+ "' not found in the classpath");
		}
		prop.load(inputStream);

		return prop;
	}
}
