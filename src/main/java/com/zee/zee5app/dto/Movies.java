package com.zee.zee5app.dto;

import java.math.BigInteger;

import javax.naming.InvalidNameException;

import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.LocationNotFoundException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Movies implements Comparable<Movies>{
	
	public Movies(String movieId, String movieName, String movieGenre, String movieReleaseDate, String movieLanguage, String movieCast, float movieLength, int agelimit, String movieTrailer) throws InvalidIdLengthException, InvalidNameException {
		
		super();
		this.setMovieId(movieId);
		this.setMovieName(movieName);
		this.setMovieGenre(movieGenre);
		this.setMovieReleaseDate(movieReleaseDate);
		this.setMovieLanguage(movieLanguage);
		this.setMovieCast(movieCast);
		this.setMovieLength(movieLength);
		this.setAgeLimit(agelimit);
		this.setMovieTrailer(movieTrailer);
	}
	
	@Setter(value = AccessLevel.NONE)
	private String movieId;
	
	@Setter(value = AccessLevel.NONE)
	private String movieName;
	
	private String movieGenre;
	private String movieReleaseDate;
	private String movieLanguage;
	private String movieCast;
	private float movieLength;
	private int ageLimit;
	private String movieTrailer;
	
	@Setter(value = AccessLevel.NONE)
	private String primaryLocation;
	
	
	public void setMovieId(String movieId) throws InvalidIdLengthException{
		this.movieId = movieId;
	}
	
	public void setMovieName(String movieName) throws InvalidNameException{
		this.movieName = movieName;
	}
	
	public void setPrimaryLocation(String primaryLocation) throws LocationNotFoundException {
		
		if(primaryLocation.length() <= 2 || primaryLocation == null || primaryLocation == "") {
			
			throw new LocationNotFoundException("Location Not Valid");
		}
		this.primaryLocation = primaryLocation;
	}
	
	
	@Override
	public int compareTo(Movies o) {
		
		return this.movieId.compareTo(o.movieId);
	}


}
