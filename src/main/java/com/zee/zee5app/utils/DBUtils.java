package com.zee.zee5app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class DBUtils {

	// This class is used to get the DB connection
	// and to close the DB connection
	static Properties properties = null;
	//private static DBUtils dbutils = null;
	Connection connection = null;
	
	public DBUtils() throws IOException{
		properties = loadProperties();
	}

		
	public Connection getConnection() {
		
		// To establish connection with the database
		try {
			
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection
						(properties.getProperty("jdbc.url"), 
						properties.getProperty("jdbc.username"), 
						properties.getProperty("jdbc.password"));
				connection.setAutoCommit(false);
			}
			//System.out.println(properties);
			//return connection;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	
	public void closeConnection(Connection connection) {
		
		try {
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private Properties loadProperties() throws IOException {
		
		// Read the properties file
		InputStream inputStream = 
				DBUtils.class.getClassLoader()
				.getResourceAsStream("application.properties");
		
		Properties properties = new Properties();
		properties.load(inputStream); // This will read the properties internally
		
		return properties;
	}

	
}
