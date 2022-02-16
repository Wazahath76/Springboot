package com.zee.zee5app.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//write the custom jpa method // we will not write any definition just only signature
	//its pre-defined in jpa
	//Boolean existsByEmail(String email);
	//Boolean existsByContactNumber(BigDecimal contactNumber);
	//Boolean existsByEmailAndContactNumber(String email,BigDecimal contactNumber);
	
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username );
	Boolean existsByEmail(String email);
	
}
