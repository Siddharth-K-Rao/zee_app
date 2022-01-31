package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

	@Autowired
	DataSource dataSource;
	
	@Override
	public String addSubscription(Subscription subscription) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertStatement = "INSERT INTO subscription"
				+ "(subId, dateOfPurchase, expiryDate, amount, paymentMode, status, type, autoRenewal, regid)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, subscription.getSubId());
			preparedStatement.setString(2, subscription.getDateOfPurchase());
			preparedStatement.setString(3, subscription.getExpiryDate());
			preparedStatement.setFloat(4, subscription.getAmount());
			preparedStatement.setString(5, subscription.getPaymentMode());
			preparedStatement.setString(6, subscription.getStatus());
			preparedStatement.setString(7, subscription.getType());
			preparedStatement.setBoolean(8, subscription.isAutoRenewal());
			preparedStatement.setString(9, subscription.getRegId());
			
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
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "SELECT * FROM subscription WHERE subId = ?";
		
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
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				Subscription subscription = new Subscription();
				subscription.setSubId(resultSet.getString("subId"));
				subscription.setDateOfPurchase(resultSet.getString("dateOfPurchase"));
				subscription.setExpiryDate(resultSet.getString("expiryDate"));
				subscription.setAmount(resultSet.getFloat("amount"));
				subscription.setPaymentMode(resultSet.getString("paymentMode"));
				subscription.setStatus(resultSet.getString("status"));
				subscription.setType(resultSet.getString("type"));
				subscription.setAutoRenewal(resultSet.getBoolean("autoRenewal"));
				subscription.setRegId(resultSet.getString("regid"));
				
				return Optional.of(subscription);
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Optional.empty();
	}

	
	@Override
	public Optional<List<Subscription>> getAllSubscriptions() throws InvalidIdLengthException, InvalidAmountException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Subscription> arrayList = new ArrayList<Subscription>();
		String selectStatement = "SELECT * FROM subscription";
		
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
				
				Subscription subscription = new Subscription();
				subscription.setSubId(resultSet.getString("subId"));
				subscription.setDateOfPurchase(resultSet.getString("dateOfPurchase"));
				subscription.setExpiryDate(resultSet.getString("expiryDate"));
				subscription.setAmount(resultSet.getFloat("amount"));
				subscription.setPaymentMode(resultSet.getString("paymentMode"));
				subscription.setStatus(resultSet.getString("status"));
				subscription.setType(resultSet.getString("type"));
				subscription.setAutoRenewal(resultSet.getBoolean("autoRenewal"));
				subscription.setRegId(resultSet.getString("regid"));
				arrayList.add(subscription);
			}
			return Optional.ofNullable(arrayList);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Optional.empty();
	}

	
	@Override
	public String modifySubscription(String id, Subscription subscription) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateStatement = "UPDATE subscription"
				+ "SET dateOfPurchase=?, expiryDate=?, amount=?, paymentMode=?, status=?, type=?, autoRenewal=?"
				+ "WHERE subId=?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			
			//preparedStatement.setString(1, subscription.getSubId());
			preparedStatement.setString(1, subscription.getDateOfPurchase());
			preparedStatement.setString(2, subscription.getExpiryDate());
			preparedStatement.setFloat(3, subscription.getAmount());
			preparedStatement.setString(4, subscription.getPaymentMode());
			preparedStatement.setString(5, subscription.getStatus());
			preparedStatement.setString(6, subscription.getType());
			preparedStatement.setBoolean(7, subscription.isAutoRenewal());
			preparedStatement.setString(8, id);
			
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
	public String deleteSubscription(String id) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteStatement = "DELETE FROM subscription WHERE subId = ?";
		
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
