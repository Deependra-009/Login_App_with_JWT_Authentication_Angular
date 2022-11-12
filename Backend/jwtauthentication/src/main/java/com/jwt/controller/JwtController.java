package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtilHelper;
import com.jwt.modal.JwtRequest;
import com.jwt.modal.JwtResponse;
import com.jwt.service.CustomUserDetailsService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtilHelper jwtutil;
	
	@GetMapping("/welcome1")
	public String home() {
		return "this is a private page";
	}
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtrequest) throws Exception{
		System.out.println("hello");
		System.out.println(jwtrequest.getPassword());
		try {
			
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtrequest.getUsername(), 
							jwtrequest.getPassword()));
			
			
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userdetails = this.customUserDetailsService.loadUserByUsername(jwtrequest.getUsername());
		
		String generateToken = this.jwtutil.generateToken(userdetails);
		System.out.println(generateToken);
		
		return ResponseEntity.ok(new JwtResponse(generateToken));
		
	}
	
	@GetMapping("/getuser")
	public String getUser() {
		return "{\"name\":\"Deepu\"}";
	}
	
	@GetMapping("/getdeepu")
	public String get() {
		return "deepu";
	}

}
