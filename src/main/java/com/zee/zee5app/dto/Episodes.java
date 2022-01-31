package com.zee.zee5app.dto;

import javax.naming.InvalidNameException;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor


public class Episodes {

	public Episodes(String episodeId, String seriesId, String episodeName, float episodeLength, String location, String trailer) throws InvalidNameException, InvalidIdLengthException {
		
		super();
		this.setEpisodeId(episodeId);
		this.setSeriesId(seriesId);
		this.setEpisodename(episodeName);
		this.setEpisodeLength(episodeLength);
		this.setLocation(location);
		this.setTrailer(trailer);
	}
	



	@Setter(value = AccessLevel.NONE)
	private String episodeId;
	
	private String seriesId;
	
	@Setter(value = AccessLevel.NONE)
	private String episodename;
	private float episodeLength;
	private String location;
	private String trailer;
	
	public void setEpisodeId(String episodeId2) throws InvalidIdLengthException{
		this.episodeId = episodeId2;
	}
	
	

	public void setEpisodename(String episodeName2) throws InvalidNameException{
		this.episodename = episodeName2;
		
	}

}
