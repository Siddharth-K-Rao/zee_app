package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface EpisodeRepository {

	public String addEpisodes(Episodes episode);
	public String deleteEpisodes(String id);
	public String modifyEpisodeDetails(String id, Episodes episode);
	public Optional<Episodes> getEpisodeDetailsById(String id) throws InvalidIdLengthException, InvalidNameException;
	public Optional<List<Episodes>> getAllEpisodeDetails(String id) throws InvalidIdLengthException, InvalidNameException;
}
