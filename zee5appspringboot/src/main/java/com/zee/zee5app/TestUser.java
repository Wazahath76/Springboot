//package com.zee.zee5app;

//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import com.zee.zee5app.dto.Register;
//import com.zee.zee5app.exception.AlreadyExistsException;
//import com.zee.zee5app.exception.IdNotFoundException;
//import com.zee.zee5app.exception.InvalidIdLengthException;
//import com.zee.zee5app.exception.InvalidNameException;
//import com.zee.zee5app.exception.InvalidPasswordException;
//import com.zee.zee5app.repository.UserRepository;
//import com.zee.zee5app.service.UserService;
//
//@SpringBootApplication
//public class TestUser {
//	public static void main(String[] args) {
//		
//	
//	ApplicationContext applicationContext = TestUser;
//	
//	System.out.println("\n\t\t\t\t\tTHIS IS FOR REGISTER\n");
//	
//	UserRepository userrepository  = applicationContext.getBean(UserRepository.class);
//
//	UserService service = applicationContext.getBean(UserService.class);
//	Register register;
//	
//	try {
//	
//	register = new Register("ab00122", "riya", "sharma", "riy222@gmail.com", "Ji2ed3443", new BigDecimal("9813973123"));
//	System.out.println(service.addUser(register));
//
//		register = new Register("ab00123", "rohan", "mehra", "rh32@gmail.com", "Ft5gfd", new BigDecimal("8631973123"));
//		System.out.println(service.addUser(register));
//	
//	
//	register = new Register("ab00124", "rajiv", "gupta", "rfw2@gmail.com", "G4edfr", new BigDecimal("981397372"));
//	System.out.println(service.addUser(register));
//	} catch (AlreadyExistsException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	System.out.println(userrepository.existsByEmailAndContactNumber("rh32@gmail.com", new BigDecimal("8631973123")));
//
//	
//	Optional<Register> register1 = null;
//
//	try {
//		register1 = service.getUserById("ab00122");
//		if (register1 != null) {
//			System.out.println("Record found");
//			System.out.println(register1.get());
//		} else {
//			System.out.println("record is not found");
//		}
//	} catch (IdNotFoundException | InvalidIdLengthException | InvalidEmailException | InvalidPasswordException| InvalidNameException e2) {
//		// TODO Auto-generated catch block
//		e2.printStackTrace();
//	}
//	
//
//	Optional<List<Register>> optional1;
//	try {
//		optional1 = service.getAllUserDetails();
//		if (optional1.isEmpty()) {
//			System.out.println("there are no records");
//		} else {
//			optional1.get().forEach(e -> System.out.println(e));
//		}
//	} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException
//			| InvalidPasswordException e2) {
//		// TODO Auto-generated catch block
//		e2.printStackTrace();
//	}
//
//	
//
//	try {
//		service.deleteUserById("ab00122");
//	} catch (IdNotFoundException e2) {
//		// TODO Auto-generated catch block
//		e2.printStackTrace();
//	}
//
//	try {
//		for (Register register2 : service.getAllUsers()) {
//			if (register2 != null)
//				System.out.println(register2);
//		}
//	} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException
//			| InvalidPasswordException e2) {
//		// TODO Auto-generated catch block
//		e2.printStackTrace();
//	}
//	}
//
//}
