package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class SeriesRepositoryImpl implements SeriesRepository {

	@Autowired
	DataSource dataSource;
	
	
	@Override
	public String addSeries(Series series) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertStatement = "INSERT INTO series"
				+ "(seriesid, seriesname, agelimit, seriescast, seriesgenre, serieslength, seriestrailer, releasedate, serieslanguage, noepisodes)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, series.getSeriesId());
			preparedStatement.setString(2, series.getSeriesName());
			preparedStatement.setInt(3, series.getAgeLimit());
			preparedStatement.setString(4, series.getSeriesCast());
			preparedStatement.setString(5, series.getSeriesGenre());
			preparedStatement.setFloat(6, series.getSeriesLength());
			preparedStatement.setString(7, series.getSeriesTrailer());
			preparedStatement.setString(8, series.getSeriesReleaseDate());
			preparedStatement.setString(9, series.getSeriesLanguage());
			preparedStatement.setInt(10, series.getNumberOfEpisodes());
			
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
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "SELECT * FROM series WHERE seriesid = ?";
		
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
				
				Series series = new Series();
				series.setSeriesId(resultSet.getString("seriesid"));
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setAgeLimit(resultSet.getInt("agelimit"));
				series.setSeriesCast(resultSet.getString("seriescast"));
				series.setSeriesGenre(resultSet.getString("seriesgenre"));
				series.setSeriesLength(resultSet.getFloat("serieslength"));
				series.setSeriesReleaseDate(resultSet.getString("seriestrailer"));
				series.setSeriesReleaseDate(resultSet.getString("releasedate"));
				series.setSeriesLanguage(resultSet.getString("serieslanguage"));
				series.setNumberOfEpisodes(resultSet.getInt("noepisodes"));
				
				return Optional.of(series);
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	
	@Override
	public Optional<ArrayList<Series>> getAllSeries() throws InvalidIdLengthException, InvalidNameException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Series> arrayList = new ArrayList<Series>();
		String selectStatement = "SELECT * FROM series";
		
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
				
				Series series = new Series();
				series.setSeriesId(resultSet.getString("seriesid"));
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setAgeLimit(resultSet.getInt("agelimit"));
				series.setSeriesCast(resultSet.getString("seriescast"));
				series.setSeriesGenre(resultSet.getString("seriesgenre"));
				series.setSeriesLength(resultSet.getFloat("serieslength"));
				series.setSeriesReleaseDate(resultSet.getString("seriestrailer"));
				series.setSeriesReleaseDate(resultSet.getString("releasedate"));
				series.setSeriesLanguage(resultSet.getString("serieslanguage"));
				series.setNumberOfEpisodes(resultSet.getInt("noepisodes"));
				arrayList.add(series);
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
	public String modifySeries(String id, Series series1) throws IdNotFoundException {
		
		Connection connection = null;;
		PreparedStatement preparedStatement = null;
		String updateStatement = "UPDATE series"
				+ "SET seriesname = ?, agelimit=?, seriescast=?, seriesgenre=?, serieslength=?, seriestrailer=?, releasedate=?, serieslanguage=?, noepisodes=?"
				+ "WHERE seriesid = ?";
				
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			
			//preparedStatement.setString(1, series1.getSeriesId());
			preparedStatement.setString(1, series1.getSeriesName());
			preparedStatement.setInt(2, series1.getAgeLimit());
			preparedStatement.setString(3, series1.getSeriesCast());
			preparedStatement.setString(4, series1.getSeriesGenre());
			preparedStatement.setFloat(5, series1.getSeriesLength());
			preparedStatement.setString(6, series1.getSeriesTrailer());
			preparedStatement.setString(7, series1.getSeriesReleaseDate());
			preparedStatement.setString(8, series1.getSeriesLanguage());
			preparedStatement.setInt(9, series1.getNumberOfEpisodes());
			preparedStatement.setString(10, id);
			
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
	public String deleteSeries(String id) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteStatement = "DELETE FROM series WHERE seriesid = ?";
		
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
