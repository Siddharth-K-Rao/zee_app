package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface MovieService {
	
	public String addMovie(Movies movie);
	public Optional<Movies> getMovieById(String id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException;
	public Optional<List<Movies>> getAllMovies() throws InvalidNameException, InvalidIdLengthException;
	public String modifyMovie(String id, Movies movie) throws IdNotFoundException;
	public String deleteMovie(String id) throws IdNotFoundException;
}
