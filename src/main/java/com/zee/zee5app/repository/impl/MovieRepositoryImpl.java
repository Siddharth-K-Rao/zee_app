package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	@Autowired
	DataSource dataSource;
	

	@Override
	public String addMovie(Movies movie) {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertStatement = "INSERT INTO movies"
				+ "(movieid, moviename, agelimit, moviecast, moviegenre, movielength, movietrailer, releasedate, language)"
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
			
			preparedStatement.setString(1, movie.getMovieId());
			preparedStatement.setString(2, movie.getMovieName());
			preparedStatement.setInt(3, movie.getAgeLimit());
			preparedStatement.setString(4, movie.getMovieCast());
			preparedStatement.setString(5, movie.getMovieGenre());
			preparedStatement.setFloat(6, movie.getMovieLength());
			preparedStatement.setString(7, movie.getMovieTrailer());
			preparedStatement.setString(8, movie.getMovieReleaseDate());
			preparedStatement.setString(9, movie.getMovieLanguage());
			
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
	public Optional<Movies> getMovieById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selectStatement = "SELECT * FROM movies WHERE movieid = ?";
		
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
				Movies movie = new Movies();
				movie.setMovieId(resultSet.getString("movieid"));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setAgeLimit(resultSet.getInt("agelimit"));
				movie.setMovieCast(resultSet.getString("moviecast"));
				movie.setMovieGenre(resultSet.getString("moviegenre"));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setMovieTrailer(resultSet.getString("movietrailer"));
				movie.setMovieReleaseDate(resultSet.getString("releasedate"));
				movie.setMovieLanguage(resultSet.getString("language"));
				
				return Optional.of(movie);
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return Optional.empty();
	}

	@Override
	public Optional<List<Movies>> getAllMovies() throws InvalidIdLengthException, InvalidNameException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Movies> arrayList = new ArrayList<Movies>();
		String selectStatement = "SELECT * FROM movies";
		
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
				
				Movies movie = new Movies();
				movie.setMovieId(resultSet.getString("movieid"));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setAgeLimit(resultSet.getInt("agelimit"));
				movie.setMovieCast(resultSet.getString("moviecast"));
				movie.setMovieGenre(resultSet.getString("moviegenre"));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setMovieTrailer(resultSet.getString("movietrailer"));
				movie.setMovieReleaseDate(resultSet.getString("releasedate"));
				movie.setMovieLanguage(resultSet.getString("language"));
				arrayList.add(movie);
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
	public String modifyMovie(String id, Movies movie) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateStatement = "UPDATE movies"
				+ "SET moviename=?, agelimit=?, moviecast=?, moviegenre=?, movielength=?, movietrailer=?, releasedate=?, language=?"
				+ "WHERE movieid = ?";
		
		try {
			connection = dataSource.getConnection();
		} 
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			
			//preparedStatement.setString(1, movie.getMovieId());
			preparedStatement.setString(1, movie.getMovieName());
			preparedStatement.setInt(2, movie.getAgeLimit());
			preparedStatement.setString(3, movie.getMovieCast());
			preparedStatement.setString(4, movie.getMovieGenre());
			preparedStatement.setFloat(5, movie.getMovieLength());
			preparedStatement.setString(6, movie.getMovieTrailer());
			preparedStatement.setString(7, movie.getMovieReleaseDate());
			preparedStatement.setString(8, movie.getMovieLanguage());
			preparedStatement.setString(9, id);
			
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
	public String deleteMovie(String id) throws IdNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteStatement = "DELETE FROM movies WHERE movieid = ?";
		
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
