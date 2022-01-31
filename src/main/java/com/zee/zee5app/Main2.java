package com.zee.zee5app;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;

public class Main2 {

	public static void main(String[] args) {
		
		try {
			Register register = new Register("skr001", "sid", "rao", "skr@gmail.com", "12345");
			
			System.out.println(register);
			System.out.println(register.toString());
			System.out.println("Hash Code: " + register.hashCode());
			
			Register register2 = new Register("skr001", "sid", "rao", "skr@gmail.com", "12345");
			System.out.println(register2);
			System.out.println(register2.toString());
			System.out.println("Hash Code: " + register2.hashCode());
			
			System.out.println(register);
			System.out.println(register.toString());
			System.out.println("Hash Code: " + register.hashCode());
			
			System.out.println("\nequals method call: " + register.equals(register2));
		} 
		catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException | InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
