package com.wipro.jcb.livelink.app.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.jcb.livelink.app.auth.entity.UserCredential;
import com.wipro.jcb.livelink.app.auth.repo.UserCredentialrepository;

@Service
public class AuthService {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserCredentialrepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String saveUser(UserCredential user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		return "User Added";
	}
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
