package com.zee.zee5app.dto;

import javax.naming.InvalidNameException;

import com.zee.zee5app.exception.InvalidIdLengthException;

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

public class Series implements Comparable<Series>{
	
	public Series(String seriesId, String seriesName, String seriesGenre, String seriesReleaseDate, String seriesLanguage, String seriesCast, int numberOfEpisodes, int ageLimit, String seriesTrailer, float seriesLength) throws InvalidIdLengthException, InvalidNameException {
		
		super();
		this.setSeriesId(seriesId);
		this.setSeriesName(seriesName);
		this.setSeriesGenre(seriesGenre);
		this.setSeriesReleaseDate(seriesReleaseDate);
		this.setSeriesLanguage(seriesLanguage);
		this.setSeriesCast(seriesCast);
		this.setNumberOfEpisodes(numberOfEpisodes);
		this.setAgeLimit(ageLimit);
		this.setSeriesTrailer(seriesTrailer);
		this.setSeriesLength(seriesLength);
		
	}
	
	@Setter(value = AccessLevel.NONE)
	private String seriesId;
	
	@Setter(value = AccessLevel.NONE)
	private String seriesName;
	private String seriesGenre;
	private String seriesReleaseDate;
	private String seriesLanguage;
	private String seriesCast;
	private int numberOfEpisodes;
	private int ageLimit;
	private String seriesTrailer;
	private float seriesLength;
	
	
	public void setSeriesId(String seriesId) throws InvalidIdLengthException {
		this.seriesId = seriesId;
	}
	
	public void setSeriesName(String seriesName) throws InvalidNameException{
		this.seriesName = seriesName;
	}

	@Override
	public int compareTo(Series o) {
		
		return this.seriesId.compareTo(o.seriesId);
	}
	
	
}
