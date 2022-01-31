package com.zee.zee5app.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import javax.naming.InvalidNameException;

import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
@Setter
@Getter
@ToString
public class Register implements Comparable<Register>{

	public Register(String id, String firstName, String lastName, String email, String password) throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
	}

	@Setter(value = AccessLevel.NONE)
	private String id; // Should have a minimum length of 6
	
	@Setter(value = AccessLevel.NONE)
	private String firstName;
	
	@Setter(value = AccessLevel.NONE)
	private String lastName;
	
	@Setter(value = AccessLevel.NONE)
	private String email;
	
	@Setter(value = AccessLevel.NONE)
	private String password;
	
	@Setter(value = AccessLevel.NONE)
	private BigDecimal contactNumber;
	
	
	public void setId(String id) throws InvalidIdLengthException {
		
		if(id.length() < 6) {
			// Should raise the InavlidIdLengthException
			// Previously, exceptions were raised/created by JVM
			// User defined exceptions must be raised by us
			throw new InvalidIdLengthException("ID length is less than 6");
		}
		this.id = id;
	}
	
	public void setFirstName(String firstName) throws InvalidNameException {
		
		if(firstName == null || firstName == "" || firstName.length() < 2) {
			throw new InvalidNameException("First Name is not valid");
		}
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) throws InvalidNameException {
		
		if(lastName == null || lastName == "" || lastName.length() < 2) {
			throw new InvalidNameException("Last Name is not valid");
		}
		this.lastName = lastName;
	}
	
	public void setEmail(String email) throws InvalidEmailException {
		
		if(email == null || email == "" || !(email.contains("@")) || !(email.contains("."))) {
			throw new InvalidEmailException("Email not vaid");
		}
		this.email = email;
	}
	
	public void setPassword(String password) throws InvalidPasswordException {
		
		if(password == null || password == "" || password.length() < 3) {
			throw new InvalidPasswordException("Password not valid");
		}
		this.password = password;
	}
	
	
	public void setContactNumber(BigDecimal contactNumber) throws InvalidContactNumberException{
		
		if(contactNumber.toString() == null || contactNumber.toString() == "" || contactNumber.toString().length() != 10) {
			throw new InvalidContactNumberException("Contact Number not Valid");
		}
		this.contactNumber = contactNumber;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}
	
	
	@Override
	public int compareTo(Register o) {
		
		//return this.id.compareTo(o.getId());
		
		// In reverse order
		return o.id.compareTo(this.getId());
	}
	
	// private stuff will be accessible only inside the class
	// To access the private variables, make use of setter and getter methods
	// setter: used to set the value for a particular field
	// getter: used to get/return the value of a specific field
	
	
	public Register() {
		// EDC
		// It is better to introduce EDC or PC, when any customization is required/
		// during the initialization of the object
	}

	
}
