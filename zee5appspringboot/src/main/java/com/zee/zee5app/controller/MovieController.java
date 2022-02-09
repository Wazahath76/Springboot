package com.zee.zee5app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
//	@PostMapping("/addMovie")
//	//used ? so we can return any type
//	public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
//		
//		//Movie result = movieService.addMovie(movie);
//		//return ResponseEntity.status(201).body(result);
//	}
}
