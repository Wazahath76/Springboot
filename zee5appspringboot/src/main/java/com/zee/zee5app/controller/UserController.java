package com.zee.zee5app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.service.UserService;

@RestController 
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody Register register) throws AlreadyExistsException {
	
			Register result =userService.addUser(register);
			System.out.println(result);
			return ResponseEntity.status(201).body(result);
			
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) throws IdNotFoundException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException, InvalidNameException{
		Register register=userService.getUserById(id);
		return ResponseEntity.ok(register);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllUserDetails() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException {
		Optional <List<Register>> optional =userService.getAllUserDetails();
		
		if(optional.isEmpty()) {
			Map<String,String> map = new HashMap<>();
			map.put("message", "no record found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			
		}
		return ResponseEntity.ok(optional.get());

	}
	
	

}