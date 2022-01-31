package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.protocol.x.ReusableOutputStream;
import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class EpisodeRepositoryImpl implements EpisodeRepository {

	@Autowired
	DataSource dataSource;
	
	@Override
	public String addEpisodes(Episodes episode) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertStatement = "INSERT INTO episodes"
				+ "(episodeid, seriesid, episodename, episodelength, location, trailer)"
				+ "VALUES(?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, episode.getEpisodeId());
			preparedStatement.setString(2, episode.getSeriesId());
			preparedStatement.setString(3, episode.getEpisodename());
			preparedStatement.setFloat(4, episode.getEpisodeLength());
			preparedStatement.setString(5, episode.getLocation());
			preparedStatement.setString(6, episode.getTrailer());
			
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
	public String deleteEpisodes(String id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteStatement = "DELETE FROM episodes WHERE episodeid = ?";
		
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

	@Override
	public String modifyEpisodeDetails(String id, Episodes episode) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateStatement = "UPDATE episodes"
				+ "SET episodename=?, episodelength=?, location=?, trailer=?"
				+ "WHERE episodeid = ?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			
			preparedStatement.setString(1, episode.getEpisodename());
			preparedStatement.setFloat(2, episode.getEpisodeLength());
			preparedStatement.setString(3, episode.getLocation());
			preparedStatement.setString(4, episode.getTrailer());
			
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
	public Optional<Episodes> getEpisodeDetailsById(String id) throws InvalidIdLengthException, InvalidNameException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "SELECT * FROM episodes WHERE episodeid = ?";
		
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
				
				Episodes episode = new Episodes();
				episode.setEpisodeId(resultSet.getString("episodeid"));
				episode.setSeriesId(resultSet.getString("seriesid"));
				episode.setEpisodename(resultSet.getString("episodename"));
				episode.setEpisodeLength(resultSet.getFloat("episodelength"));
				episode.setLocation(resultSet.getString("location"));
				episode.setTrailer(resultSet.getString("trailer"));
				
				return Optional.of(episode);
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return Optional.empty();
	}



	@Override
	public Optional<List<Episodes>> getAllEpisodeDetails(String id) throws InvalidIdLengthException, InvalidNameException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Episodes> arrayList = new ArrayList<Episodes>();
		String selectStatement = "SELECT * FROM episodes WHERE episodeid = ?";
		
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
				
				Episodes episode = new Episodes();
				episode.setEpisodeId(resultSet.getString("episodeid"));
				episode.setSeriesId(resultSet.getString("seriesid"));
				episode.setEpisodename(resultSet.getString("episodename"));
				episode.setEpisodeLength(resultSet.getFloat("episodelength"));
				episode.setLocation(resultSet.getString("location"));
				episode.setTrailer(resultSet.getString("trailer"));
				arrayList.add(episode);
				return Optional.ofNullable(arrayList);
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return Optional.empty();
	}

}
