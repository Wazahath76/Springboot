package com.zee.zee5app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.EROLE;
import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.RoleService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.Impl.MovieServiceImpl;
import com.zee.zee5app.service.Impl.SeriesServiceImpl;
import com.zee.zee5app.service.Impl.SubscriptionServiceImpl;
import com.zee.zee5app.utils.Fileutils;

@SpringBootApplication
public class Zee5appspringbootApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class,
				args);

//		System.out.println("\n\t\t\t\t\tTHIS IS FOR REGISTER\n");
//		
//		UserRepository userrepository  = applicationContext.getBean(UserRepository.class);
//        
//		RoleService roleService = applicationContext.getBean(RoleService.class);
//      RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
//		
//		Role role = new Role();
//		role.setRoleName(EROLE.ROLE_ADMIN);
//		Role role2 = new Role();
//		role2.setRoleName(EROLE.ROLE_USER);
//	    roleService.addRole(role2);
//	    roleService.addRole(role);
//		Set<Role> roles = new HashSet<>();
//	
//		UserService service = applicationContext.getBean(UserService.class);
//		Register register1 = null,register2 = null, register3 = null, register4 = null;
//	
//
//		try {
//		
//		register1 = new Register("ab00131", "riya", "sharma", "riy231@gmail.com", "Ji2ed3443", new BigDecimal("9813973123"), null,null,null);;
//		roles.add(roleRepository.findById(1).get()); // this add in user_roles_table
//		roles.add(roleRepository.findById(2).get());
//		register1.setRoles(roles);// this add in user_roles_table
//		System.out.println(service.addUser(register1));
//		
//		register2= new Register("ab00123", "rohan", "mehra", "rh32@gmail.com", "Ft5gfd", new BigDecimal("8631973123"),null,null,null);
//		register2.setRoles(roles);
//		System.out.println(service.addUser(register2));
//
//		register3 = new Register("ab00124", "rajiv", "gupta", "rfw2@gmail.com", "G4edfr", new BigDecimal("981397372"),null,null,null);
//		register3.setRoles(roles);
//		System.out.println(service.addUser(register3));
//		
//		register4 = new Register("ab0122", "aman", "gupta", "am2w@gmail.com", "Dint24f", new BigDecimal("981396811"),null,null,null);
//		register4.setRoles(roles);
//		System.out.println(service.addUser(register4));
//		} catch (AlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//
//		System.out.println(userrepository.existsByEmailAndContactNumber("rh32@gmail.com", new BigDecimal("8631973123")));
//
//		
//		Optional<Register> register5 = null;
//
//		try {
//			register5 = service.getUserById("ab0122");
//			if (register5 != null) {
//				System.out.println("Record found");
//				System.out.println(register5.get());
//			} else {
//				System.out.println("record is not found");
//			}
//		} catch (IdNotFoundException | InvalidIdLengthException | InvalidEmailException | InvalidPasswordException
//				| InvalidNameException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//
//		Optional<List<Register>> optional1;
//		try {
//			optional1 = service.getAllUserDetails();
//			if (optional1.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional1.get().forEach(e -> System.out.println(e));
//			}
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException
//				| InvalidPasswordException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		try {
//			service.deleteUserById("ab0122");
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		try {
//			for (Register register6 : service.getAllUsers()) {
//				if (register6 != null)
//					System.out.println(register6);
//			}
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException
//				| InvalidPasswordException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}

//	System.out.println("\n\t\t\t\t\tTHIS IS FOR LOGIN\n");
//	
//	LoginService service5  = applicationContext.getBean(LoginService.class);
//	
//	System.out.println(service5.changeRole("tanya21@gmail.com", ROLE.ROLE_ADMIN));
//
//		System.out.println("\n\t\t\t\t\tTHIS IS FOR SUBSCRIPTION\n");
//
//		SubscriptionService service2 = applicationContext.getBean(SubscriptionService.class);
//		Subscription subscription;
//
//		
//		try {
//			subscription = new Subscription("sub009", "2019-04-13", 2599.0f, "credit", "2023-05-03", "active", "annual",
//					"false", register1);
//			String result= service2.addSubscription(subscription);
//			System.out.println(result);
//			
//			subscription = new Subscription("sub0010", "2017-04-13", 3000.0f, "credit", "2019-05-03", "active", "annual",
//					"true", register2);
//			String result3 = service2.addSubscription(subscription);
//			System.out.println(result3);
//
//			subscription = new Subscription("sub0011", "2016-04-13", 4580.0f, "netbanking", "2017-05-03", "inactive",
//					"monthly", "false", register3);
//			String result4 = service2.addSubscription(subscription);
//			System.out.println(result4);
//			
//			subscription = new Subscription("sub012", "2018-04-13", 4000.0f, "debit", "2022-05-03", "active", "annual",
//					"false", register4);
//			String result19= service2.addSubscription(subscription);
//			System.out.println(result19);
//		} catch (InvalidAmountException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		try {
//			service.deleteUserById("ab0122");
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		Optional<Subscription> subscription1 = null;
//
//		try {
//			subscription1 = service2.getSubscriptionById("sub009");
//			System.out.println(subscription1.get());
//		} catch (IdNotFoundException | InvalidIdLengthException | InvalidAmountException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//
//		Optional<List<Subscription>> optional;
//		try {
//			optional = service2.getAllSubscription();
//			if (optional.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional.get().forEach(e -> System.out.println(e));
//			}
//		} catch (InvalidIdLengthException | InvalidAmountException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		
//
//		try {
//			service2.deleteSubscription("sub012");
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		Optional<List<Subscription>> optional8;
//		try {
//			optional8 = service2.getAllSubscription();
//			if (optional8.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional8.get().forEach(e -> System.out.println(e));
//			}
//		} catch (InvalidIdLengthException | InvalidAmountException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		
//
//		System.out.println("\n\t\t\t\t\tTHIS IS FOR MOVIES\n");
//
//		MovieService service3 = applicationContext.getBean(MovieService.class);
//		Movie movie = new Movie();
//	
//		movie.setAgeLimit(18);
//		movie.setCast("abc,def");
//		movie.setGenre("action");
//		movie.setId("mov0018");
//		movie.setLanguage("tamil");
//		movie.setLength(120);
//		movie.setMovieName("wanted12");
//		movie.setReleaseDate("2021-12-12");
//		
//		Fileutils fileutils = applicationContext.getBean(Fileutils.class);
//		
//     //File file = new File();
//		String source = "C:\\Users\\rajiv.gupta\\Downloads\\video1.mp4";
//	    String destination = "C:\\Users\\rajiv.gupta\\Downloads\\movies\\movieStore\\"+file.getName();
//	    
//	    movie.setTrailer(destination);
//	    
//	    String result = service3.addMovie(movie);
//	    if(result.equals("record added in movie")) {
//	    	file = new File(source);
//	    	byte[] data;
//	   
//	    	try {
//				data = fileutils.readFile(file);
//				fileutils.writeFile(data, destination);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    	
//	    }
//		
//		
//		//this is for blob
//		FileInputStream fileInputStream = null;
//		FileOutputStream fileOutputStream = null;
//
//		try {
//			//input the file
//			File file = new File("C:\\Users\\rajiv.gupta\\Downloads\\video1.mp4");
//			fileInputStream = new FileInputStream(file);
//			long fileSize = file.length();
//			byte[] allBytes = new byte[(int) fileSize];
//			
//			fileInputStream.read(allBytes);
//			movie.setTrailer("C:\\Users\\rajiv.gupta\\Downloads\\movies\\movieStore\\"+file.getName());
//			
//			String result = service3.addMovie(movie);
//			//output the file and store it in movieStore
//			if(result.equals("record added in movie")) {
//				fileOutputStream = new FileOutputStream("C:\\Users\\rajiv.gupta\\Downloads\\movies\\movieStore\\"+file.getName());
//				fileOutputStream.write(allBytes);
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				fileInputStream.close();
//				fileOutputStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		String result = service3.addMovie(movie);
//		
//		//we did this to check if its working properly or not
//		FileOutputStream fileOutputStream = null;
//		try {
//			Optional<Movie> optional = service3.getMovieById("mov0012");
//			
//			if(optional.isEmpty()) {
//				System.out.println("movie not found");
//			}
//			else {
//				//we should print the info and copy the file to movies folder with name wanted12
//				
//				Movie movie = optional.get();
//				fileOutputStream = new FileOutputStream("C:\\Users\\rajiv.gupta\\Downloads\\movies\\read\\wanted12.mp4");
//				fileOutputStream.write(movie.getTrailer());
//			}
//		} catch (NameNotFoundException | IdNotFoundException | InvalidIdLengthException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				fileOutputStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		MovieRepository movierepository  = applicationContext.getBean(MovieRepository.class);
//		System.out.println(movierepository.findByMovieNameAndLanguage("martian","english"));
//		System.out.println(movierepository.findByMovieName("martian"));
//		System.out.println(movierepository.findByMovieNameAndReleaseDate("martian","2016-12-17"));
//		System.out.println(movierepository.findByCast("xyz, rty, atb"));
//
//		movie = new Movie("mov009", "matrix", "hrk, srk, atb", 150, "2021-12-19", null, "english", "15", "action");
//		String result13 = service3.addMovie(movie);
//		System.out.println(result13);
//
//		movie = new Movie("mov0010", "3 idoits", "aamir, kareena, atb", 134, "2021-12-16", null, "hindi", "15",
//				"comedy");
//		String result5 = service3.addMovie(movie);
//		System.out.println(result5);
//
//		movie = new Movie("mov0011", "martian", "xyz, rty, atb", 121, "2016-12-17", null, "english", "12", "sci-fi");
//		String result6 = service3.addMovie(movie);
//		System.out.println(result6);
//
//		
//		
//		Optional<Movie> movie1 = null;
//
//		try {
//			movie1 = service3.getMovieById("mov009");
//			System.out.println(movie1.get());
//		} catch (NameNotFoundException | IdNotFoundException | InvalidIdLengthException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//
//		Optional<List<Movie>> optional2;
//		try {
//			optional2 = service3.getAllMovie();
//			if (optional2.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional2.get().forEach(e -> System.out.println(e));
//			}
//		} catch (NameNotFoundException | InvalidIdLengthException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		
//
//		try {
//			service3.deleteMovie("mov009");
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		Optional<List<Movie>> optional3;
//		try {
//			optional3 = service3.getAllMovie();
//			if (optional3.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional3.get().forEach(e -> System.out.println(e));
//			}
//		} catch (NameNotFoundException | InvalidIdLengthException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		
//
//		System.out.println("\n\t\t\t\tTHIS IS FOR SERIES\n");
//
//		SeriesService service4 = applicationContext.getBean(SeriesService.class);
//		Series series;
//		
//		Series series1 = new Series("ser009", "dexter", "joy, ross", "2014-02-25", null, "english", "17", "thriller", 59, null);
//		String result1 = service4.addSeries(series1);
//		System.out.println(result1);
//
//		Series series2 = new Series("ser010", "sunflower", "ret, dfef, deae", "2013-02-25", null, "hindi", "12", "thriller", 12, null);
//		String result7 = service4.addSeries(series2);
//		System.out.println(result7);
//
//		Series series3 = new Series("ser011", "mirzapur", "joy, ross", "2015-02-25", null, "hindi", "15", "suspense, action", 32, null);
//		String result8 = service4.addSeries(series3);
//		System.out.println(result8);
//		
//		Series series8 = new Series("ser012", "lot", "rty, urd", "2013-02-21", null, "english", "14", "suspense", 120, null);
//		String result18 = service4.addSeries(series8);
//		System.out.println(result18);
//		
//		
//		
//		SeriesRepository seriesrepository  = applicationContext.getBean(SeriesRepository.class);
//		System.out.println(seriesrepository.findBySeriesNameAndLanguage("sunflower","hindi"));
//
//		Optional<Series> series4 = null;
//
//		try {
//			series4 = service4.getSeriesById("ser009");
//			System.out.println(series4.get());
//		} catch (NameNotFoundException | IdNotFoundException | InvalidIdLengthException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//
//		Optional<List<Series>> optional4;
//		try {
//			optional4 = service4.getAllSeries();
//			if (optional4.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional4.get().forEach(e -> System.out.println(e));
//			}
//		} catch (NameNotFoundException | InvalidIdLengthException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		
//
//		try {
//			service4.deleteSeries("ser012");
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		Optional<List<Series>> optional5;
//		try {
//			optional5 = service4.getAllSeries();
//			if (optional5.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional5.get().forEach(e -> System.out.println(e));
//			}
//
//		} catch (NameNotFoundException | InvalidIdLengthException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		
//		System.out.println("\n\t\t\t\t\tTHIS IS FOR EPISODES\n");
//
//		EpisodeService service5 = applicationContext.getBean(EpisodeService.class);
//		Episodes episodes;
//
//		episodes = new Episodes("epi001", "black1", 35, series1);
//		String result9 = service5.addEpisode(episodes);
//		System.out.println(result9);
//
//		episodes = new Episodes("epi002", "black2", 27, series1);
//		String result10 = service5.addEpisode(episodes);
//		System.out.println(result10);
//
//		episodes = new Episodes("epi003", "black3", 31, series1);
//		String result11 = service5.addEpisode(episodes);
//		System.out.println(result11);
//
//		episodes = new Episodes("epi004", "red12", 38, series2);
//		String result12 = service5.addEpisode(episodes);
//		System.out.println(result12);
//
//		episodes = new Episodes("epi005", "red13", 45, series2);
//		String result15 = service5.addEpisode(episodes);
//		System.out.println(result15);
//
//		episodes = new Episodes("epi006", "violet1", 23, series3);
//		String result14 = service5.addEpisode(episodes);
//		System.out.println(result14);
//		
//		try {
//			service4.deleteSeries("ser011");
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		Optional<Episodes> episodes1 = null;
//
//		try {
//			episodes1 = service5.getEpisodeById("epi003");
//			System.out.println(episodes1.get());
//		} catch (NameNotFoundException | IdNotFoundException | InvalidIdLengthException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//
//		Optional<List<Episodes>> optional6;
//		try {
//			optional6 = service5.getAllEpisode();
//			if (optional6.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional6.get().forEach(e -> System.out.println(e));
//			}
//		} catch (InvalidIdLengthException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		
//
//		try {
//			service5.deleteEpisode("epi003");
//		} catch (IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		Optional<List<Episodes>> optional7;
//		try {
//			optional7 = service5.getAllEpisode();
//			if (optional7.isEmpty()) {
//				System.out.println("there are no records");
//			} else {
//				optional7.get().forEach(e -> System.out.println(e));
//			}
//
//		} catch (InvalidIdLengthException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		applicationContext.close();

	}

}
