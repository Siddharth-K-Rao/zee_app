package com.zee.zee5app.service.impl;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	// Bring the userRepository object
	// using the getInstance method
	// are we expecting that repo object we should get it form spring ?
	
	private UserRepository userRepository = null;
	//private static UserServiceImpl service = null;
	
	
	
	@Override
	public String addUser(Register register) {
		return this.userRepository.addUser(register);
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException {
		return this.userRepository.getUserById(id);
	}

	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		return this.userRepository.updateUser(id, register);
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		return this.userRepository.deleteUserById(id);
	}

	@Override
	public Register[] getAllUsers() {
		return null;
	}

	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUserDetails();
	}

	

}
