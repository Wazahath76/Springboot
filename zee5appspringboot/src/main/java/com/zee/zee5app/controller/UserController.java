package com.zee.zee5app.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.EROLE;
import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.payload.request.LoginRequest;
import com.zee.zee5app.payload.request.SignupRequest;
import com.zee.zee5app.payload.response.MessageResponse;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.security.jwt.JwtUtils;
import com.zee.zee5app.service.UserService;

@RestController //combination of @ResponseBody and @Controller
//REST API: Response wherever we have to share the response that method must be marked with @ResponseBody
//1000 methods ---> @ResponseBody 1000 times so o avoid this we use @RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
//	@PostMapping("/signin")
//	
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest))
//	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
		
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error:Email is already in use!"));
			
		}
		
		// user's account
		
		User user = new User(signupRequest.getUsername(),
				signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()),
				signupRequest.getFirstName(),
				signupRequest.getLastName());
		
		
		// retrieving the roles details
		
		Set<String> strRoles = signupRequest.getRole();

		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(
							()->new RuntimeException("Error:role not found"));
		}
		
		
		else {
			strRoles.forEach(e->{
				switch (e) {
				
				case "admin":
					Role roleAdmin = roleRepository.findByRoleName
					(EROLE.ROLE_ADMIN)
							.orElseThrow(
									()->new RuntimeException("Error:role not found"));
					
					roles.add(roleAdmin);
					break;
					
				case "mod":
					Role roleMod = roleRepository.findByRoleName
					(EROLE.ROLE_MODERATOR)
							.orElseThrow(
									()->new RuntimeException("Error:role not found"));
					
					roles.add(roleMod);
					break;
					
					default:
						Role userRole = roleRepository.findByRoleName
						(EROLE.ROLE_USER)
								.orElseThrow(
										()->new RuntimeException("Error:role not found"));
						
						roles.add(userRole);
						
						
		
				}
			});
			
			
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.status(201).body(new MessageResponse("user created successfully"));
		
	}
//	//add user info into register table
//	//info will be shared by the client in terms of JSON object
//	//now we need to read that JSON object
//	//this JSON object is available in requestbody
//	//we need to read that requestbody content
//	//transform JSON obj to java object ---> this will be done by @RequestBody
//	
//	//we need to inform when this method should be used so we should specify the endpoint
//	@PostMapping("/addUser")
//	//used ? so we can return any type
//	public ResponseEntity<?> addUser(@Valid @RequestBody User register) throws AlreadyExistsException {
//		
//		//1. It should store the received info in database
//		User result = userService.addUser(register);
//		System.out.println(result);
//		return ResponseEntity.status(201).body(result);
//		
//		}
//	//retrieve single record
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getUserById(@PathVariable("id") String id) throws IdNotFoundException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException, InvalidNameException{
//		User result = userService.getUserById(id);
//		return ResponseEntity.ok(result);	
//		
//	}
//	
//	//retrieve all records
//	@GetMapping("/all")
//	//@PreAuthorize
//	
//	public ResponseEntity<?> getAllUserDetails() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException{
//		Optional<List<User>> optional = userService.getAllUserDetails();
//		if(optional.isEmpty()) {
//			Map<String, String> map = new HashMap<>();
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("no record found"));
//		}
//		return ResponseEntity.ok(optional.get());	
//		
//	}
//	
//	//2. validation
//			//3. return the crispy info to the client
//			//4. a. customization in error response
//			//4. b. declaration of custom exception

}