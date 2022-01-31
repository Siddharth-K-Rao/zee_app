package com.zee.zee5app.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration // It is used to mark on config class/classes
// Here, we hold all commonly required objects
@ComponentScan("com.zee.zee5app")
@PropertySource("classpath:application.properties") // It is responsible for reading the property files

public class Config {
 
	@Autowired // Will bring the already created objects based on the name(reference name) / type
	Environment environment; // This impl object is prepared by spring
	// Do we need to inject the already created object? Yes ===> DI ===> IoC
	// Config: DB related, reading prop file, commonly required beans(passwordEncoder)
	
	@Bean // For providing the singleton object
	// If we will not specify the bean name, then it will take / consider the method name as bean name
	public DataSource dataSource() {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setInitialSize(5);
		//basicDataSource.setDefaultAutoCommit(false);
		
		return basicDataSource;
	}
}
