package com.zee.zee5app.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.zee.zee5app.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

// to provide the token
@Component

public class JwtUtils {
	
	private static final Logger Logger = LoggerFactory.getLogger(JwtUtils.class);
	
	//read jwtSecret
	@Value("${zee5app.app.jwtSecret}")
	
	private String jwtSecret;
	// read exp value 
	@Value("${zee5app.app.jwtExpirationMs}")
	private int jwtExpiryValue;
	
	//generate token 
	
	public String generateToken(Authentication authentication) {
		// username 
		//issued @ when token is generated
		//expiry
		//encryption strategy 
		
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
				
		return Jwts.builder().setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+jwtExpiryValue))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String authToken) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(authToken)
				.getBody().getSubject();
	}
	
	//validate the token 
	
	public boolean validateJwtToken(String authToken) {
	    try {
	        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	        return true;
	      } catch (SignatureException e) {
	        Logger.error("Invalid JWT signature: {}", e.getMessage());
	      } catch (MalformedJwtException e) {
	        Logger.error("Invalid JWT token: {}", e.getMessage());
	      } catch (ExpiredJwtException e) {
	        Logger.error("JWT token is expired: {}", e.getMessage());
	      } catch (UnsupportedJwtException e) {
	        Logger.error("JWT token is unsupported: {}", e.getMessage());
	      } catch (IllegalArgumentException e) {
	        Logger.error("JWT claims string is empty: {}", e.getMessage());
	      }

	      return false;
	}
}
