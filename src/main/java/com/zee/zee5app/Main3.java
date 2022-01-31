package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.LoginServiceImpl;
import com.zee.zee5app.service.impl.SubscriptionServiceImpl;
import com.zee.zee5app.service.impl.UserServiceImpl;

public class Main3 {

	public static void main(String[] args) {
		
//		Register registerNew;
//		try {
//			registerNew = new Register("sid000777", "Sid", "Rao", "sidrao@gmail.com", "12345");
//			registerNew.setContactNumber(new BigDecimal("8123333693"));
//			
//			UserService serviceNew = UserServiceImpl.getInstance();
//			
//			String result = serviceNew.addUser(registerNew);
//			System.out.println(result);
//		} 
//		catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException
//				| InvalidPasswordException | InvalidContactNumberException | IOException e5) {
//			// TODO Auto-generated catch block
//			e5.printStackTrace();
//		}
		
//		try {
//			
//			UserService serviceNew = UserServiceImpl.getInstance();
//
//			Optional<Register> temp = serviceNew.getUserById("sid000001");
//
//			System.out.println(temp.get());
//		} 
//		catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException
//				| InvalidPasswordException | InvalidContactNumberException | IOException | IdNotFoundException e5) {
//			// TODO Auto-generated catch block
//			e5.printStackTrace();
//		}
		
//		try {
//			
//			UserService serviceNew = UserServiceImpl.getInstance();
//			
//			//String result = serviceNew.addUser(registerNew);
//			//Optional<Register> temp = serviceNew.getUserById("sid000001");
//			Optional<List<Register>> optional = serviceNew.getAllUserDetails();
//			
//			if(optional.isEmpty()) {
//				System.out.println("There are no records");
//			}
//			else {
//				optional.get().forEach(e->System.out.println(e));
//			}
//
//		} 
//		catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException
//				| InvalidPasswordException | InvalidContactNumberException | IOException e5) {
//			// TODO Auto-generated catch block
//			e5.printStackTrace();
//		}

//		try {
//			LoginService loginservice = LoginServiceImpl.getInstance();
//			String result = loginservice.changeRole("sidrao@gmail.com", ROLE.ROLE_ADMIN);
//			System.out.println(result);
//		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		// ********************************SUBSCRIPTION*****************************
		
//		Subscription subscription;
//		try {
//			subscription = new Subscription("sub0055", "2022-01-25", "2023-01-25", "DEBIT", "ACTIVE", "YEARLY", "skr040999", true, 499f);
//			
//			SubscriptionService service = SubscriptionServiceImpl.getInstance();
//			
//			String result = service.addSubscription(subscription);
//			System.out.println(result);
//		} 
//		catch (InvalidAmountException | InvalidIdLengthException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			SubscriptionService service = SubscriptionServiceImpl.getInstance();
			
			Optional<Subscription> optional = service.getSubscriptionById("sub0055");
			
			System.out.println(optional.get());
		} 
		catch (IOException | IdNotFoundException | InvalidIdLengthException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
