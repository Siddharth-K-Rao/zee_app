package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;

public interface UserService {

	public String addUser(Register register);
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException;
	public String updateUser(String id, Register register) throws IdNotFoundException;
	public String deleteUserById(String id) throws IdNotFoundException;
	public Register[] getAllUsers();
	public Optional<List<Register>> getAllUserDetails() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidContactNumberException, InvalidPasswordException;
}
