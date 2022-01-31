/**
 * 
 */
package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;


@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository = null;
	
	
	@Override
	public String addMovie(Movies movie) {
		return this.movieRepository.addMovie(movie);
	}

	@Override
	public Optional<Movies> getMovieById(String id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
		return this.movieRepository.getMovieById(id);
	}

	@Override
	public Optional<List<Movies>> getAllMovies() throws InvalidNameException, InvalidIdLengthException {
		return this.movieRepository.getAllMovies();
	}

	@Override
	public String modifyMovie(String id, Movies movie) throws IdNotFoundException {
		return this.movieRepository.modifyMovie(id, movie);
	}

	@Override
	public String deleteMovie(String id) throws IdNotFoundException {
		return this.movieRepository.deleteMovie(id);
	}

}
