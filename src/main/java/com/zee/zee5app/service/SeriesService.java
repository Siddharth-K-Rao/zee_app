package com.zee.zee5app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface SeriesService {
	
	public String addSeries(Series series1);
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException;
	public Optional<ArrayList<Series>> getAllSeries() throws InvalidNameException, InvalidIdLengthException;
	public String modifySeries(String id, Series series1) throws IdNotFoundException;
	public String deleteSeries(String id) throws IdNotFoundException;
}
