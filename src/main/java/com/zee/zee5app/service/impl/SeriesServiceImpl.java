package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService {

	
	private SeriesRepository seriesRepository = null;
	
	
	@Override
	public String addSeries(Series series1) {
		return this.seriesRepository.addSeries(series1);
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
		return this.seriesRepository.getSeriesById(id);
	}

	@Override
	public Optional<ArrayList<Series>> getAllSeries() throws InvalidNameException, InvalidIdLengthException {
		return this.seriesRepository.getAllSeries();
	}

	@Override
	public String modifySeries(String id, Series series1) throws IdNotFoundException {
		return this.seriesRepository.modifySeries(id, series1);
	}

	@Override
	public String deleteSeries(String id) throws IdNotFoundException {
		return this.seriesRepository.deleteSeries(id);
	}

}
