package com.zee.zee5app;

import java.math.BigDecimal;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;

public class MainSpring {

	public static void main(String[] args) {
		
		// Do we need to establish/create a spring environment
		// This will kick start the spring application
		// application context
		// Here, we have to initialize application context container
		// Java Based Configuration
		
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		
		System.out.println(userRepository);
		UserRepository userRepository2 = applicationContext.getBean(UserRepository.class);
		
		System.out.println(userRepository2);
		
		System.out.println(userRepository.hashCode());
		System.out.println(userRepository2.hashCode());
		System.out.println(userRepository.equals(userRepository2));
		
		DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
		System.out.println(dataSource != null);
		
		
		Register registerNew = null;
		try {
			registerNew = new Register("sid990904", "Sid", "Rao", "siddrao@gmail.com", "12345");
			registerNew.setContactNumber(new BigDecimal("8123333693"));
			System.out.println(userRepository.addUser(registerNew));
		} 
		catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException | InvalidPasswordException | InvalidContactNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		applicationContext.close();
	}

}
