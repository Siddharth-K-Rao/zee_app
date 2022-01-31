package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	DataSource dataSource;
	
	
	@Override
	public String addCredentials(Login login) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// User details should be stored in DB
		String insertStatement = "INSERT INTO login"
				+ " (username, password, regId, role)"
				+ " values(?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setString(3, login.getRegId());
			preparedStatement.setString(4, login.getRole().toString());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				//connection.commit();
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
	public String deleteCredentials(String userName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteStatement = "DELETE FROM login WHERE username = ?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, userName);
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				return "Success";
			}
			else {
				return "Fail";
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return "Fail";
		}

	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String changeRole(String userName, ROLE role) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateStatement = "UPDATE login SET ROLE = ? WHERE username = ?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			
			preparedStatement.setString(1, role.toString());
			preparedStatement.setString(2, userName);
			
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

}
