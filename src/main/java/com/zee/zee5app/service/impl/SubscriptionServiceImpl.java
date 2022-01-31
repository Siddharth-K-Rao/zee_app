package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	private SubscriptionRepository subRepository = null;

	
	@Override
	public String addSubscription(Subscription subscription) {
		return this.subRepository.addSubscription(subscription);
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException {
		return this.subRepository.getSubscriptionById(id);
	}

	@Override
	public Optional<List<Subscription>> getAllSubscriptions() throws InvalidIdLengthException, InvalidAmountException {
		return this.subRepository.getAllSubscriptions();
	}

	@Override
	public String modifySubscription(String id, Subscription subscription) throws IdNotFoundException {
		return this.subRepository.modifySubscription(id, subscription);
	}

	@Override
	public String deleteSubscription(String id) throws IdNotFoundException {
		return this.subRepository.deleteSubscription(id);
	}

}
