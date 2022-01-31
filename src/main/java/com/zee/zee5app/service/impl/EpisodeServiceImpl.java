package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.repository.impl.EpisodeRepositoryImpl;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	private EpisodeRepository episodeRepository = null;
	
	
	@Override
	public String addEpisodes(Episodes episode) {
		return this.episodeRepository.addEpisodes(episode);
	}

	
	@Override
	public String deleteEpisodes(String id) {
		return this.episodeRepository.deleteEpisodes(id);
	}

	
	@Override
	public String modifyEpisodeDetails(String id, Episodes episode) {
		return this.episodeRepository.modifyEpisodeDetails(id, episode);
	}



	@Override
	public Optional<Episodes> getEpisodeDetailsById(String id) throws InvalidNameException, InvalidIdLengthException {
		return this.episodeRepository.getEpisodeDetailsById(id);
	}


	@Override
	public Optional<List<Episodes>> getAllEpisodeDetails(String id) throws InvalidNameException, InvalidIdLengthException {
		return this.episodeRepository.getAllEpisodeDetails(id);
	}

}
