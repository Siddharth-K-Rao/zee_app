package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidContactNumberException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.MovieServiceImpl;
import com.zee.zee5app.service.impl.SeriesServiceImpl;
import com.zee.zee5app.service.impl.SubscriptionServiceImpl;
import com.zee.zee5app.service.impl.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		
		// Trying it out with parameterized constructor		
		
			Register registerNew;
			try {
				registerNew = new Register("sid000001", "Sid", "Rao", "skr@gmail.com", "12345");
				
				registerNew.setContactNumber(new BigDecimal("8123333693"));
				
				UserService serviceNew = UserServiceImpl.getInstance();
				
				//String result = serviceNew.addUser(registerNew);
				//Optional<Register> temp = serviceNew.getUserById("sid000001");
				Optional<List<Register>> optional = serviceNew.getAllUserDetails();
				
				if(optional.isEmpty()) {
					System.out.println("There are no records");
				}
				else {
					optional.get().forEach(e->System.out.println(e));
				}
			} 
			catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException
					| InvalidPasswordException | InvalidContactNumberException | IOException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}		
		
		
		Register register = new Register();
		// Register: class name
		// register: reference that will refer to the object
		// new: keyword used to create the object
		// Register(): constructor ===> DC ===> IDC
		// new Register(): object
		

		try {
			register.setId("skr001");
			register.setFirstName("Siddharth");
			register.setLastName("K Rao");
			register.setEmail("siddharth.rao@zee.com");
			register.setPassword("sid1234");
			register.setContactNumber(new BigDecimal("8123333693"));	
		} 
		catch (InvalidIdLengthException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		catch (InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (InvalidEmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (InvalidPasswordException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (InvalidContactNumberException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Printing the reference, will always call the toString() method
		System.out.println(register);
		System.out.println(register.toString());
		System.out.println(register.getEmail());
		
		/***
		// Login object
		Login login = new Login();
		
		login.setUserName("siddharth");
		login.setPassword("1234");
		
		System.out.println(login.getUserName());
		System.out.println(login.getPassword());
		
		***/
		
		// userService object
		// Main consuming the service
		//UserService2 service = UserService2.getInstance();
		
		
		// This is changed to make sure multiple implementations work
		UserService service = null;
		try {
			service = UserServiceImpl.getInstance();
		} 
		catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		for(int i=1; i<=20; i++) {
			
			Register register2 = new Register();
			
				try {
					register2.setId("skr000" + i);
					register2.setFirstName("sid" + i);
					register2.setLastName("rao" + i);
					register2.setPassword("sid");
					register2.setContactNumber(new BigDecimal("8123333693"));
					register2.setEmail("siddrao49@gmail.com");
				} 
				catch (InvalidIdLengthException | InvalidNameException | InvalidPasswordException | InvalidEmailException | InvalidContactNumberException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			String result = service.addUser(register2);
			System.out.println(result);
		}
		
		
		// Checking if the User exists with the ID
		Optional<Register> register2 = null;
		try {
			register2 = service.getUserById("skr001");
			if(register2.isPresent()) {
				System.out.println("getUserById: " + register2.get());
			}
			else {
				System.out.println("ID Not Present");
			}
		} 
		catch (IdNotFoundException | InvalidNameException | InvalidIdLengthException | InvalidEmailException | InvalidContactNumberException | InvalidPasswordException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// Getting the user details
//		try {
//			service.getAllUserDetails().forEach(e->System.out.println(e));
//		} 
//		catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException | InvalidContactNumberException
//				| InvalidPasswordException e4) {
//			// TODO Auto-generated catch block
//			e4.printStackTrace();
//		}
		
		
		// Updating the user details
		Register register3 = new Register();
		try {
			register3.setId("skr005");
			register3.setFirstName("Siddharth");
			register3.setLastName("K Rao");
			register3.setPassword("12345678");
			register3.setContactNumber(new BigDecimal("8123333693"));
		} 
		catch (InvalidIdLengthException e) {
			e.printStackTrace();
			//System.out.println("Id length less than 6");
		}
		catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InvalidContactNumberException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Updating user details
		String result = null;
		try {
			result = service.updateUser(register3.getId(), register3);
		} catch (IdNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(result);
		
		
		// Deleting a user
		String deleteUserCheck = null;
		try {
			deleteUserCheck = service.deleteUserById("skr002");
		} 
		catch (IdNotFoundException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(deleteUserCheck + "\n\n\n");
		
		
		/*** SUBSCRIPTION ***/
		System.out.println("************SUBSCRIPTION************\n");
		SubscriptionService subservice = null;
		try {
			subservice = SubscriptionServiceImpl.getInstance();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		for(int i=0; i<=5; i++) {
			
			Subscription subscription = new Subscription();
			try {
				subscription.setSubId("sub00" + i);
				subscription.setAmount(1000f);
			} 
			catch (InvalidIdLengthException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			catch (InvalidAmountException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			subscription.setType("Premium_" + i);
			subscription.setDateOfPurchase((i+1)+"/01/"+"2022");
			subscription.setStatus("Active");
			//subscription.setPackCountry("India");
			subscription.setPaymentMode("UPI");
			subscription.setAutoRenewal(true);
			subscription.setExpiryDate((i+1)+"/01/"+"2023"); 

			
			String result1 = subservice.addSubscription(subscription);
			System.out.println(result1);
		}
		
		// Getting subscription details for a particular subId
		Optional<Subscription> subscription1 = null;
		try {
			subscription1 = subservice.getSubscriptionById("sub001");
			if(subscription1.isPresent()) {
				System.out.println("\ngetSubscriptionById: " + subscription1.get() + "\n");
			}
			else {
				System.out.println("ID Not Present\n");
			}
		} 
		catch (IdNotFoundException | InvalidIdLengthException | InvalidAmountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// Getting subscription details
		//subservice.getAllSubscriptions().forEach(e->System.out.println(e));
		
		
		// Updating the subscription details
		Subscription subscription2 = new Subscription();
		try {
			subscription2.setSubId("sub002");
			subscription2.setAmount(399f);
			
		} 
		catch (InvalidIdLengthException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
		catch (InvalidAmountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		subscription2.setType("Premium");
		subscription2.setDateOfPurchase("15"+"/01/"+"2022");
		subscription2.setStatus("Active");
		//subscription2.setPackCountry("India");
		subscription2.setPaymentMode("Credit Card");
		subscription2.setAutoRenewal(false);
		subscription2.setExpiryDate("15"+"/01/"+"2023");
		
		String result2 = null;
		try {
			result2 = subservice.modifySubscription(subscription2.getSubId(), subscription2);
		} 
		catch (IdNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("\n"+ result2 + "\n");
		
		
		// Deleting subscription
		String deleteSubscriptionCheck = null;
		try {
			deleteSubscriptionCheck = subservice.deleteSubscription("sub002");
		} 
		catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(deleteSubscriptionCheck + "\n\n\n");
		
		
		/***Movies***/
		System.out.println("************MOVIES************\n");
		MovieService movieservice = null;
		try {
			movieservice = MovieServiceImpl.getInstance();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		for(int i=0; i<=5; i++) {
			
			Movies movie1 = new Movies();
			
			try {
				movie1.setMovieId("Movie_0" + i);
				movie1.setMovieName("Money Ball");
				movie1.setPrimaryLocation("Los Angeles");
			} 
			catch (InvalidIdLengthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (LocationNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (InvalidNameException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			movie1.setMovieGenre("Drama");
			movie1.setMovieLength(3600f);
			movie1.setMovieLanguage("English");
			String movieCast = "Brad Pitt, Jonah Hill";
			movie1.setMovieCast(movieCast);
			movie1.setAgeLimit(18);
			movie1.setMovieReleaseDate("10/01/2007");
			movie1.setMovieTrailer("https://www.youtube.com/watch?v=-4QPVo0UIzc");
			
			// Add movie details
			String result3 = movieservice.addMovie(movie1);
			System.out.println(result3);
		}
		
		
		// Get movie details by MovieId
		Optional<Movies> movie2 = null;
		try {
			movie2 = movieservice.getMovieById("Movie_02");
			if(movie2.isPresent()) {
				System.out.println("\ngetMovieById: " + movie2.get() + "\n");
			}
			else {
				System.out.println("ID Not Present\n");
			}
		} 
		catch (IdNotFoundException | InvalidNameException | InvalidIdLengthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Get all movie details
		//movieservice.getAllMovies().forEach(e->System.out.println(e));
		
		// Modify movie details
		Movies updateDetails = new Movies();
		try {
			updateDetails.setMovieName("Money Ball");
			updateDetails.setMovieId("Movie_01");
			updateDetails.setPrimaryLocation("Atlanta");
		} 
		catch (InvalidIdLengthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (LocationNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		updateDetails.setMovieGenre("Drama");
		updateDetails.setMovieLength(3600f);
		updateDetails.setMovieLanguage("English");
		String movieCastUpdate = "Brad Pitt, Jonah Hill, Chris Pratt";
		updateDetails.setMovieCast(movieCastUpdate);
		updateDetails.setAgeLimit(18);
		updateDetails.setMovieReleaseDate("10/01/2007");
		updateDetails.setMovieTrailer("https://www.youtube.com/watch?v=-4QPVo0UIzc");
		
		String result4 = null;
		try {
			result4 = movieservice.modifyMovie(updateDetails.getMovieId(), updateDetails);
		} 
		catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("\n" + result4 + "\n");
		
		
		// Delete movie details
		String deleteMovieCheck = null;
		try {
			deleteMovieCheck = movieservice.deleteMovie("Movie_01");
		} 
		catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(deleteMovieCheck + "\n\n\n");
		
		
		/***Series***/
		System.out.println("************SERIES************\n");
		SeriesService seriesservice = null;
		try {
			seriesservice = SeriesServiceImpl.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Adding series details
		for(int i=0; i<=5; i++) {
			
			Series show1 = new Series();
			try {
				show1.setSeriesId("Series_0" + i);
				show1.setSeriesName("The Office");
			} 
			catch (InvalidIdLengthException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
			catch (InvalidNameException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			show1.setSeriesGenre("Comedy");
			show1.setSeriesLanguage("English");
			show1.setAgeLimit(18);
			show1.setNumberOfEpisodes(24);
			show1.setSeriesReleaseDate("24/01/2007");
			show1.setSeriesTrailer("https://www.youtube.com/watch?v=cRpbuYnHWQY");
			show1.setSeriesLength(2700f);
			String seriesCast = "Steve Carell, Rainn Wilson, John Kraisinski";
			show1.setSeriesCast(seriesCast);
			
			String result5 = seriesservice.addSeries(show1);
			System.out.println(result5);
		}
		
		
		// Get series details by id
		Optional<Series> show3 = null;
		try {
			show3 = seriesservice.getSeriesById("Series_02");
			if(show3.isPresent()) {
				
				System.out.println("\ngetSeriesById: " + show3.get() + "\n");
			}
			else {
				System.out.println("ID Not Present");
			}
		} 
		catch (IdNotFoundException | InvalidNameException | InvalidIdLengthException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		// Get all the series details
		//seriesservice.getAllSeries().forEach(e->System.out.println(e));
		
		
		// Modify series details
		Series updateShowDetails = new Series();
		try {
			updateShowDetails.setSeriesId("Series_01");
			updateShowDetails.setSeriesName("The Office");
		} 
		catch (InvalidIdLengthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		updateShowDetails.setSeriesGenre("Comedy");
		updateShowDetails.setSeriesLanguage("English");
		updateShowDetails.setAgeLimit(18);
		updateShowDetails.setNumberOfEpisodes(18);
		updateShowDetails.setSeriesReleaseDate("24/01/2007");
		String seriesCastUpdate = "Steve Carell, Rainn Wilson, John Kraisinski, Jenna Fischer";
		updateShowDetails.setSeriesCast(seriesCastUpdate);
		updateShowDetails.setSeriesLength(1800f);
		updateShowDetails.setSeriesTrailer("https://www.youtube.com/watch?v=cRpbuYnHWQY");
		
		String result6 = null;
		try {
			result6 = seriesservice.modifySeries(updateShowDetails.getSeriesId(), updateShowDetails);
		} 
		catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("\n" + result6);
		
		
		// Delete series details
		String deleteShowCheck = null;
		try {
			deleteShowCheck = seriesservice.deleteSeries("Series_01");
		} 
		catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("\n" + deleteShowCheck);
		
	}
}
