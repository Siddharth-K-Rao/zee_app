package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

@Repository // It will create the singleton object for us
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private LoginRepository loginRepository;
	
	
	
	@Override
	public String addUser(Register register) {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// User details should be stored in DB
		String insertStatement = "INSERT INTO register"
				+ " (regid, firstname, lastname, email, contactnumber, password)"
				+ " VALUES(?,?,?,?,?,?)";
		
		// We will concatenate values in values specification
		// we will use ?
		// Here, we will provide the values against '?' (placeholder)
		// In both these situations, data will be provided at the run time
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			// Need to provide values against the place holders
			preparedStatement.setString(1, register.getId());
			preparedStatement.setString(2, register.getFirstName());
			preparedStatement.setString(3, register.getLastName());
			preparedStatement.setString(4, register.getEmail());
			preparedStatement.setBigDecimal(5, register.getContactNumber());
			
			String salt = PasswordUtils.getSalt(30);
			String encryptedPassword = PasswordUtils.generateSecurePassword(register.getPassword(), salt);
			preparedStatement.setString(6, encryptedPassword);
		
			// Username: emailid
			// regid: regid
			// password: password
			// We store it in login table, since we get all these details during the registration
			
			int result = preparedStatement.executeUpdate(); // Number of rows affected by the DML Statement
			// Return type of executeUpdate is int
			// 1: one row is inserted
			
			if(result > 0) {
				//connection.commit();
				Login login = new Login();
				login.setUserName(register.getEmail());
				login.setPassword(encryptedPassword);
				login.setRegId(register.getId());
				login.setRole(ROLE.ROLE_USER);
				String result2 = loginRepository.addCredentials(login);
				
				if(result2.equals("Success")) {
					//connection.commit();
					return "Success";
				}
				else {
					connection.rollback();
					return "Fail";
				}
			}
			else {
				connection.rollback();
				return "Fail";
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "Fail";
		}
		
	}


	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selectStatement = "SELECT * FROM register WHERE regid = ?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);
			
			// To retrieve the data
			// RS will hold the complete result
			resultSet = preparedStatement.executeQuery();
			
			// RS object is a single one which is holding all the records (Need to traverse) like collections
			if (resultSet.next()) {
				// next method is used for traversing the RS
				// Initially, RS will be placed just above the first record
				// When called, will retrieve the first record and will later refer to the 2nd one
				Register register = new Register();
				register.setId(resultSet.getString("regid"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setContactNumber(resultSet.getBigDecimal("contactnumber"));
				register.setPassword(resultSet.getString("password"));
				
				return Optional.of(register);
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}


	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateStatement = "UPDATE register"
				+ "SET firstname=?, lastname=?, email=?, contactnumber=?, password=?"
				+ "WHERE regid=?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			
			//preparedStatement.setString(1,  register.getId());
			preparedStatement.setString(1, register.getFirstName());
			preparedStatement.setString(2, register.getLastName());
			preparedStatement.setString(3, register.getEmail());
			preparedStatement.setBigDecimal(4, register.getContactNumber());
			preparedStatement.setString(5, register.getPassword());
			preparedStatement.setString(6, id);
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				connection.commit();
				return "Success";
			}
			else {
				connection.rollback();
				return "Fail";
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "Fail";
		}
		
	}


	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteStatement = "DELETE FROM register WHERE regid = ?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, id);
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				connection.commit();
				return "Success";
			}
			else {
				connection.rollback();
				return "Fail";
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			try {
				connection.rollback();
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "Fail";
		}
		
	}


	@Override
	public Register[] getAllUsers() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException {
		
		Optional<List<Register>> optional = getAllUserDetails();
		if(optional.isEmpty()) {
			return null;
		}
		else {
			List<Register> list = optional.get();
			Register[] registers = new Register[list.size()];
			return list.toArray(registers);
		}
	}


	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Register> arrayList = new ArrayList<Register>();
		
		String selectStatement = "SELECT * FROM register";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
			
				Register register = new Register();
				register.setId(resultSet.getString("regid"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setContactNumber(resultSet.getBigDecimal("contactnumber"));
				register.setPassword(resultSet.getString("password"));
				arrayList.add(register);
			}
			return Optional.ofNullable(arrayList);
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}

		return Optional.empty();
	}
	

}
